package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CustomEmployeeRepository {
    //public List<Employee> getEmployeesBySkillAndDate(Set<String> skillNames, LocalDate date);
    public List<Employee> getEmployeesBySkillAndDate(String skillName, LocalDate date);
}
