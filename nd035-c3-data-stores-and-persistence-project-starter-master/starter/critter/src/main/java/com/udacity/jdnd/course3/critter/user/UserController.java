package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkillEntity;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.EmployeeSkillService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private CustomerService customerService;
    private PetService petService;
    private EmployeeSkillService employeeSkillService;
    private EmployeeService employeeService;

    public UserController(CustomerService customerService,
                          PetService petService,
                          EmployeeSkillService employeeSkillService,
                          EmployeeService employeeService) {
        this.customerService = customerService;
        this.petService = petService;
        this.employeeSkillService = employeeSkillService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return convertCustomerToCustomerDTO(
                customerService.saveCustomer(convertCustomerDTOToCustomer(customerDTO))
        );
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customer -> convertCustomerToCustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Optional<Customer> o = customerService.getCustomerByPetId(petId);
        if (!o.isPresent()) return null;
        return convertCustomerToCustomerDTO(o.get());
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return convertEmployeeToEmployeeDTO(employeeService.saveEmployee(convertEmployeeDTOToEmployee(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Optional<Employee> o = employeeService.getEmployeeById(employeeId);
        if (!o.isPresent()) return null;
        return convertEmployeeToEmployeeDTO(o.get());
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        if (!employeeService.setEmployeeAvailability(daysAvailable, employeeId))
            throw new EntityNotFoundException("Specified employee not found!");
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        Set<String> skillnames = employeeDTO.getSkills().stream()
                                    .map(skill -> skill.name())
                                    .collect(Collectors.toSet());
        //using criteria api
/*        return employeeService.getEmployeesBySkillAndDate(skillnames, employeeDTO.getDate()).stream()
                .map(employee -> convertEmployeeToEmployeeDTO(employee))
                .collect(Collectors.toList());*/

        //using derived query
        return employeeService.getEmployeesBySkillAndDateDerived(skillnames, employeeDTO.getDate()).stream()
                .map(employee -> convertEmployeeToEmployeeDTO(employee))
                .collect(Collectors.toList());
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());

        customer = customerService.saveCustomer(customer);

        if (customerDTO.getPetIds() != null) {
            List<Pet> pets= customerDTO.getPetIds().stream()
                            .map(id -> {
                                Optional<Pet> o = petService.getPetById(id);
                                if (!o.isPresent()) throw new EntityNotFoundException("Specified pet not found!");
                                return o.get();
                            })
                            .filter(p -> p != null)
                            .collect(Collectors.toList());
            customer.setPets(pets);
        }

        return customerService.saveCustomer(customer);
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
        customer.getPets();

        List<Long> petIds = null;
        if (customer.getPets() != null) {
            petIds = customer.getPets().stream()
                        .map(pet -> pet.getId())
                        .collect(Collectors.toList());
        }
        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        Set<EmployeeSkill> skills = null;
        if (employee.getSkills() != null) {
            skills = employee.getSkills().stream()
                .map(skill -> EmployeeSkill.valueOf(skill.getName()))
                .filter(skill -> skill != null)
                .collect(Collectors.toSet());
        }

        employeeDTO.setSkills(skills);
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());

        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());

        employee = employeeService.saveEmployee(employee);

        if (employeeDTO.getSkills() != null) {
            Set<EmployeeSkillEntity> skills = employeeDTO.getSkills().stream()
                .map(skillEnum -> {
                    String skillName = skillEnum.name();
                    Optional<EmployeeSkillEntity> o = employeeSkillService.getEmployeeSkillById(skillName);
                    if (!o.isPresent()) throw new EntityNotFoundException("Specified skill not found!");
                    return o.get();
                })
                .filter(skillEntity -> skillEntity != null)
                .collect(Collectors.toSet());
            employee.setSkills(skills);
        }

        employee.setDaysAvailable(employeeDTO.getDaysAvailable());

        return employeeService.saveEmployee(employee);
    }

}
