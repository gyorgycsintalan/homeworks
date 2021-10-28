package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(long employee_id) {
        return employeeRepository.findById(employee_id);
    }

    public boolean setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Optional<Employee> o = employeeRepository.findById(employeeId);
        if (!o.isPresent()) return false;

        Employee employee = o.get();
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> getEmployeesBySkillAndDate(Set<String> skillNames, LocalDate date) {
        if (skillNames.isEmpty() || date == null) return null;
        Iterator<String> iteratorSkillnames = skillNames.iterator();
        String fristskillname = iteratorSkillnames.next();
        List<Employee> ret = employeeRepository.getEmployeesBySkillAndDate(fristskillname, date);
        iteratorSkillnames.remove();

        if (ret == null) {System.out.println("isnull");return null;};

        return ret.stream()
                .filter(employee -> {Set<EmployeeSkillEntity> empskills = employee.getSkills();
                                     Set<String> empskillNames = new HashSet<String>();

                                     empskills.forEach(empskill -> empskillNames.add(empskill.getName()));
                                     return empskillNames.containsAll(skillNames); })
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesBySkillAndDateDerived(Set<String> skillNames, LocalDate date) {
        if (skillNames.isEmpty() || date == null) return null;
        List<Employee> employeesAvailableByDate = employeeRepository.findAvailableByDate(date);

        if (employeesAvailableByDate != null) {
            return employeesAvailableByDate.stream()
                    .filter(employee -> {Set<EmployeeSkillEntity> empskills = employee.getSkills();
                                         Set<String> empskillNames = new HashSet<>();

                                         empskills.forEach(empskill -> empskillNames.add(empskill.getName()));
                                         return empskillNames.containsAll(skillNames);
                    })
                    .collect(Collectors.toList());
        }

        return null;
    }

}
