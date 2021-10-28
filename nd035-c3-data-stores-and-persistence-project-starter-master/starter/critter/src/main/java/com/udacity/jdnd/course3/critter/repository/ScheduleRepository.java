package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPetsId(Long id);

    List<Schedule> findByEmployeesId(Long id);

    @Query("select s from Schedule s join s.pets p where p.owner.id = :owner_id")
    List<Schedule> findByOwnerId(@Param("owner_id") long owner_id);
}
