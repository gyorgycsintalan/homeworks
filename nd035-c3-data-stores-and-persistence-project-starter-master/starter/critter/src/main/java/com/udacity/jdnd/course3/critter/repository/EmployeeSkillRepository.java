package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkillEntity, String> {
}
