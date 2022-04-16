package etaskify.TaskManagement.solution.service;

import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.web.dto.EmployeeDto;

import java.util.List;

public interface ManagementService {
    Employee saveEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(long id);
}
