package etaskify.TaskManagement.solution.web;

import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.model.Task;
import etaskify.TaskManagement.solution.service.ManagementService;
import etaskify.TaskManagement.solution.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
	private final ManagementService managementService;

	private final TaskService taskService;

	public MainController(ManagementService managementService, TaskService taskService) {
		this.managementService = managementService;
		this.taskService = taskService;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model) {
		List<Employee> employeeList = managementService.getAllEmployees();
		List<Task> taskList = taskService.getAllTasks();
		model.addAttribute("employees", employeeList);
		model.addAttribute("tasks", taskList);
		System.out.println(employeeList.toString());
		System.out.println(taskList.toString());
		System.out.println();

		return "index";
	}


}
