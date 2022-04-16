package etaskify.TaskManagement.solution.service;

import etaskify.TaskManagement.solution.Exception.ResourceNotFoundException;
import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.repository.EmployeeRepository;
import etaskify.TaskManagement.solution.repository.UserRepository;
import etaskify.TaskManagement.solution.web.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ManagementServiceImpl implements ManagementService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ManagementServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getFirstName(),
                employeeDto.getLastName(), employeeDto.getEmail(),
                passwordEncoder.encode(employeeDto.getPassword()));

        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Contact", "Id", id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() -> {
            return new ResourceNotFoundException("Contact", "Id", employee.getId());
        });
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }


    @Override
    public void deleteEmployee(long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            System.out.println("No such employee");
        }
    }
}
