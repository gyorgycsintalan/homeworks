package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.PetTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends JpaRepository<PetTypeEntity, String> {
}
