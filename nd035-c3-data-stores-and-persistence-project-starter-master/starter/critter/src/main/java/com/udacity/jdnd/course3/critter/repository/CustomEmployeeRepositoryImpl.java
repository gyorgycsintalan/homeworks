package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//experiment with composite repositories
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getEmployeesBySkillAndDate(String skillName, LocalDate date) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        Join<Employee, Schedule> schedule = root.join(Employee_.SCHEDULES, JoinType.LEFT);
        Predicate predIsnull = cb.isNull(schedule);
        Predicate predNotequal = cb.notEqual(schedule.get(Schedule_.date_when), date);

        cq.where(cb.or(predNotequal, predIsnull));

        TypedQuery<Employee> tq = entityManager.createQuery(cq);
        List<Employee> result = tq.getResultList();

        return result;
    }
}
