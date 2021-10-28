package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.model.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.model.PetTypeEntity;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.repository.EmployeeSkillRepository;
import com.udacity.jdnd.course3.critter.repository.PetTypeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Launches the Spring application. Unmodified from starter code.
 */
@SpringBootApplication
public class CritterApplication implements CommandLineRunner {
	@Autowired
	EmployeeSkillRepository employeeSkillRepository;
	@Autowired
	PetTypeRepository petTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CritterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Arrays.stream(EmployeeSkill.values()).forEach(
				skill -> {
					EmployeeSkillEntity skillEntity = new EmployeeSkillEntity();
					skillEntity.setName(skill.name());
					employeeSkillRepository.save(skillEntity);
				}
		);
		Arrays.stream(PetType.values()).forEach(
				petType -> {
					PetTypeEntity petTypeEntity = new PetTypeEntity();
					petTypeEntity.setName(petType.name());
					petTypeRepository.save(petTypeEntity);
				}
		);
	}
}
