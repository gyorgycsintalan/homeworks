package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.PetTypeEntity;
import com.udacity.jdnd.course3.critter.repository.PetTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
public class PetTypeService {
    PetTypeRepository petTypeRepository;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    public Optional<PetTypeEntity> getPetTypeById(String id) {
        return petTypeRepository.findById(id);
    }
}
