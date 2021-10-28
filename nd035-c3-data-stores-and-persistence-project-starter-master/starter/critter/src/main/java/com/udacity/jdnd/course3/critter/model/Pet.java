package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthDate;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Customer owner;

    @OneToOne
    @JoinColumn(name = "type_name")
    private PetTypeEntity type;

    @ManyToMany(mappedBy = "pets", fetch = FetchType.LAZY)
    private Set<Schedule> schedules = new HashSet<Schedule>();

    public void addScheduleBidirectional(Schedule schedule) {
        schedules.add(schedule);
        schedule.getPets().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
        owner.getPets().add(this);
    }

    public PetTypeEntity getType() {
        return type;
    }

    public void setType(PetTypeEntity type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        schedules.forEach(schedule -> addScheduleBidirectional(schedule));
    }

    public void clearSchedules() {
        schedules= null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return getId().equals(pet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
