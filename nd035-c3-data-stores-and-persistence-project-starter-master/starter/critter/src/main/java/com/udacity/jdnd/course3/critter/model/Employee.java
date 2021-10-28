package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends User {
    @ElementCollection
    private Set<DayOfWeek> daysAvailable = new HashSet<DayOfWeek>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_empskill",
            joinColumns = {@JoinColumn(name = "fk_employee")},
            inverseJoinColumns = {@JoinColumn(name = "fk_skill")}
    )
    private Set<EmployeeSkillEntity> skills = new HashSet<EmployeeSkillEntity>();

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<Schedule> schedules = new HashSet<Schedule>();

    public void addSkillBidirectional(EmployeeSkillEntity employeeSkillEntity) {
        skills.add(employeeSkillEntity);
        employeeSkillEntity.getEmployees().add(this);
    }

    public void addScheduleBidirectional(Schedule schedule) {
        schedules.add(schedule);
        schedule.getEmployees().add(this);
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkillEntity> skills) {
        skills.forEach(skill -> addSkillBidirectional(skill));
    }

    public Set<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        schedules.forEach(schedule -> addScheduleBidirectional(schedule));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
