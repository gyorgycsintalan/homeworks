package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class EmployeeSkillEntity {
    @Id
    private String name;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<Employee>();

    public EmployeeSkillEntity() {
    }

    @ManyToMany(mappedBy = "activities", fetch = FetchType.LAZY)
    private Set<Schedule> schedules = new HashSet<Schedule>();

    public void addEmployeeBidirectional(Employee employee) {
        employees.add(employee);
        employee.getSkills().add(this);
    }

    public void addScheduleBidirectional(Schedule schedule) {
        schedules.add(schedule);
        schedule.getActivities().add(this);
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        schedules.forEach(schedule -> addScheduleBidirectional(schedule));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        employees.forEach(employee -> addEmployeeBidirectional(employee));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSkillEntity that = (EmployeeSkillEntity) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
