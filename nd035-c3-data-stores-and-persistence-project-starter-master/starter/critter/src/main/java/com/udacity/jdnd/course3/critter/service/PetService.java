package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet p) {
        return petRepository.save(p);
    }

    public void removePet(Pet pet) {
        petRepository.delete(pet);
    }

    public Optional<Pet> getPetById(long id) {
        return petRepository.findById(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(long owner_id) {
        return petRepository.findByOwner(owner_id);
        //return petRepository.findByOwnerId(owner_id);
    }

}
