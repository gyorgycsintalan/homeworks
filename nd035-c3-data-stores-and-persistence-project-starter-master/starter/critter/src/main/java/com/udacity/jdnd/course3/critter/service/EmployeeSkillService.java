package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EmployeeSkillService {
    private EmployeeSkillRepository employeeSkillRepository;

    public EmployeeSkillService(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    public Optional<EmployeeSkillEntity> getEmployeeSkillById(String id) {
        return employeeSkillRepository.findById(id);
    }
}
