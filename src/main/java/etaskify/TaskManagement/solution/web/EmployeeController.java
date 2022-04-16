package etaskify.TaskManagement.solution.web;


import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.service.ManagementService;
import etaskify.TaskManagement.solution.service.TaskService;
import etaskify.TaskManagement.solution.web.dto.EmployeeDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    private final ManagementService managementService;

    private final TaskService taskService;

    public EmployeeController(ManagementService managementService, TaskService taskService) {
        this.managementService = managementService;
        this.taskService = taskService;
    }

    @GetMapping("/addEmployeeForm")
    public String addNewEmployee(Model model) {
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employeeDto", employeeDto);
        return "addemployeeform";
    }

    @PostMapping("/save")
    public String addEmployee(@ModelAttribute("employeeDto") EmployeeDto employeeDto) {
        managementService.saveEmployee(employeeDto);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editFormPage(@PathVariable("id") long id, Model model) {
        Employee employee = new Employee();
        System.out.println("temp id is " + employee.getId());
        model.addAttribute("employee", employee);
        model.addAttribute("currentId", id);
        return "employeeeditform";
    }

    @PostMapping("/save-edit")
    public String editSaveEmployee(@ModelAttribute("employee") Employee employee) {
        System.out.println(employee);
        managementService.updateEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        managementService.deleteEmployee(id);
        return "redirect:/";
    }
}
