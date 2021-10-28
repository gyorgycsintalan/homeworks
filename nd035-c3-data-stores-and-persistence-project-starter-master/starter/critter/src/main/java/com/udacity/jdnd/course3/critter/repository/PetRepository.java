package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select p from Pet p join fetch p.type t where p.owner.id = :owner_id")
    List<Pet> findByOwner(@Param("owner_id") long owner_id);

    List<Pet> findByOwnerId(long id);
}
