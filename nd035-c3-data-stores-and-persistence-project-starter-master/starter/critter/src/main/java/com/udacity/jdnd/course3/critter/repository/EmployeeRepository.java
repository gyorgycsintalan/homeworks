package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, CustomEmployeeRepository {
    @Query("select e from Employee e left join e.schedules s where s.date_when is null or s.date_when <> :date")
    List<Employee> findAvailableByDate(@Param("date") LocalDate date);
}
