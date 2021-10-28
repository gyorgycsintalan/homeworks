package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "schedule_employee",
            joinColumns = {@JoinColumn(name = "fk_schedule")},
            inverseJoinColumns = {@JoinColumn(name = "fk_employee")}
    )
    private Set<Employee> employees = new HashSet<Employee>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "schedule_pet",
            joinColumns = {@JoinColumn(name = "fk_schedule")},
            inverseJoinColumns = {@JoinColumn(name = "fk_pet")}
    )
    private Set<Pet> pets = new HashSet<Pet>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="schedule_activity",
            joinColumns = {@JoinColumn(name = "fk_schedule")},
            inverseJoinColumns = {@JoinColumn(name = "fk_employee_skill")}
    )
    private Set<EmployeeSkillEntity> activities = new HashSet<EmployeeSkillEntity>();

    private LocalDate date_when;

    public void addEmployeeBidirectional(Employee employee) {
        employees.add(employee);
        employee.getSchedules().add(this);
    }

    public void addPetBidirectional(Pet pet) {
        pets.add(pet);
        pet.getSchedules().add(this);
    }

    public void addActivityBidirectional(EmployeeSkillEntity employeeSkillEntity){
        activities.add(employeeSkillEntity);
        employeeSkillEntity.getSchedules().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        employees.forEach(employee -> addEmployeeBidirectional(employee));
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        pets.forEach(pet -> addPetBidirectional(pet));
    }

    public Set<EmployeeSkillEntity> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkillEntity> activities) {
        activities.forEach(activity -> addActivityBidirectional(activity));
    }

    public LocalDate getDate_when() {
        return date_when;
    }

    public void setDate_when(LocalDate date_when) {
        this.date_when = date_when;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getId().equals(schedule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
