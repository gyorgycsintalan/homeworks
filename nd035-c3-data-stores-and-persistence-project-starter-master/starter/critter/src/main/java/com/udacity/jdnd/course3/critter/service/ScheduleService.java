package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Optional<Schedule> getScheduleById(long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAllByPetId(long petId) {
        return scheduleRepository.findByPetsId(petId);
    }

    public List<Schedule> getAllByEmployeeId(long id) {
        return scheduleRepository.findByEmployeesId(id);
    }

    public List<Schedule> getAllByOwnerId(long id) {
        return scheduleRepository.findByOwnerId(id);
    }
}
