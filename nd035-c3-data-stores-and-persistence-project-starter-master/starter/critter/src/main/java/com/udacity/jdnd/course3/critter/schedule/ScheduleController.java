package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.EmployeeSkillService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;
    private EmployeeService employeeService;
    private PetService petService;
    private EmployeeSkillService employeeSkillService;

    public ScheduleController(ScheduleService scheduleService,
                              EmployeeService employeeService,
                              PetService petService,
                              EmployeeSkillService employeeSkillService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
        this.employeeSkillService = employeeSkillService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToScheduleDTO(scheduleService.saveSchedule(convertScheduleDTOToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return schedules == null ? null : schedules.stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getAllByPetId(petId);
        return schedules == null ? null : schedules.stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getAllByEmployeeId(employeeId);
        return schedules == null ? null : schedules.stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getAllByOwnerId(customerId);
        return schedules == null ? null : schedules.stream()
                .map(schedule -> convertScheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();

        schedule.setId(scheduleDTO.getId());
        schedule.setDate_when(scheduleDTO.getDate());

        schedule = scheduleService.saveSchedule(schedule);

        Set<Employee> employees= null;
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        if (employeeIds != null) {
            employees = scheduleDTO.getEmployeeIds().stream()
                    .map(employeeId -> {
                        Optional<Employee> o = employeeService.getEmployeeById(employeeId);
                        if (!o.isPresent()) throw new EntityNotFoundException("Specified Employee not found!");
                        return o.get();
                    })
                    .collect(Collectors.toSet());
        }
        schedule.setEmployees(employees);

        Set<Pet> pets = null;
        List<Long> petIds = scheduleDTO.getPetIds();
        if (petIds != null) {
            pets = scheduleDTO.getPetIds().stream()
                    .map(petId -> {
                        Optional<Pet> o = petService.getPetById(petId);
                        if (!o.isPresent()) throw new EntityNotFoundException("Specified pet not found!");
                        return o.get();
                    })
                    .collect(Collectors.toSet());
        };
        schedule.setPets(pets);

        Set<EmployeeSkillEntity> employeeSkills = null;
        Set<EmployeeSkill> skills = scheduleDTO.getActivities();
        if (skills != null) {
            employeeSkills = scheduleDTO.getActivities().stream()
                    .map(activity -> {
                        Optional<EmployeeSkillEntity> o = employeeSkillService.getEmployeeSkillById(activity.name());
                        if (!o.isPresent()) throw new EntityNotFoundException("Specified skill not fount!");
                        return o.get();
                    })
                    .collect(Collectors.toSet());
        };
        schedule.setActivities(employeeSkills);

        return scheduleService.saveSchedule(schedule);
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDate(schedule.getDate_when());

        List<Long> employeeIds = null;
        Set<Employee> employees = schedule.getEmployees();
        if (employees != null) {
            employeeIds = schedule.getEmployees().stream()
                                    .map(employee -> employee.getId())
                                    .collect(Collectors.toList());
        }
        scheduleDTO.setEmployeeIds(employeeIds);

        List<Long> petIds = null;
        Set<Pet> pets = schedule.getPets();
        if (pets != null) {
            petIds = schedule.getPets().stream()
                                .map(pet -> pet.getId())
                                .collect(Collectors.toList());
        }
        scheduleDTO.setPetIds(petIds);

        Set<EmployeeSkill> activities = null;
        Set<EmployeeSkillEntity> skillEntities = schedule.getActivities();
        if (skillEntities != null) {
            activities = schedule.getActivities().stream()
                                    .map(skill -> EmployeeSkill.valueOf(skill.getName()))
                                    .collect(Collectors.toSet());
        }
        scheduleDTO.setActivities(activities);
        return scheduleDTO;
    }
}
