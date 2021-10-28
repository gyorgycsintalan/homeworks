package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetTypeEntity;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.PetTypeService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private PetService petService;
    private CustomerService customerService;
    private PetTypeService petTypeService;

    public PetController(PetService petService, CustomerService customerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.customerService = customerService;
        this.petTypeService = petTypeService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);

        return convertPetToPetDTO(pet);
    }

    @PostMapping("/{petId}")
    public PetDTO savePetWithId(@RequestBody PetDTO petDTO, @PathVariable long petId) {
        Optional<Pet> o = petService.getPetById(petId);
        if (!o.isPresent()) throw new EntityNotFoundException("Specified pet does not exist!");
        Pet pet = o.get();

        Pet finalPet = pet;
        pet.getSchedules().forEach(schedule -> {
            schedule.getPets().remove(finalPet);
        });
        pet.getOwner().getPets().remove(pet);
        pet.clearSchedules();
        petService.savePet(pet);

        petDTO.setId(petId);
        pet = convertPetDTOToPet(petDTO);

        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Optional<Pet> o = petService.getPetById(petId);
        if (!o.isPresent()) throw new EntityNotFoundException("Specified pet does not exist!");

        return convertPetToPetDTO(o.get());
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return pets == null ? null : pets.stream()
                .map(pet -> convertPetToPetDTO(pet))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwnerId(ownerId);
        return pets == null ? null : pets.stream()
                .map(pet -> convertPetToPetDTO(pet))
                .collect(Collectors.toList());
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();

        petDTO.setId(pet.getId());
        petDTO.setType(PetType.valueOf(pet.getType().getName()));
        petDTO.setName(pet.getName());
        petDTO.setOwnerId(pet.getOwner().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());

        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();

        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());
        pet.setBirthDate(petDTO.getBirthDate());
        pet = petService.savePet(pet);

        Optional o;
        o = petTypeService.getPetTypeById(petDTO.getType().name());
        if (!o.isPresent()) {
            petService.removePet(pet);
            throw new EntityNotFoundException("Invalid pet type specified!");
        }

        pet.setType((PetTypeEntity) o.get());

        o = customerService.getCustomerById(petDTO.getOwnerId());
        if (!o.isPresent()) {
            petService.removePet(pet);
            throw new EntityNotFoundException("Specified owner does not exist!");
        }

        pet.setOwner((Customer) o.get());

        return petService.savePet(pet);
    }

}
