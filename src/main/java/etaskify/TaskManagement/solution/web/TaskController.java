package etaskify.TaskManagement.solution.web;


import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.model.Task;
import etaskify.TaskManagement.solution.service.ManagementService;
import etaskify.TaskManagement.solution.service.TaskService;
import etaskify.TaskManagement.solution.web.dto.TaskDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {

    private final ManagementService managementService;

    private final TaskService taskService;

    public TaskController(ManagementService managementService, TaskService taskService) {
        this.managementService = managementService;
        this.taskService = taskService;
    }


    @GetMapping("/addTaskForm")
    public String addNewTask(Model model) {
        TaskDto taskDto = new TaskDto();
        model.addAttribute("taskDto", taskDto);
        return "addtaskform";
    }

    @PostMapping("/savetask")
    public String addTask(@ModelAttribute("employeeDto") TaskDto taskDto) {
        taskService.saveTask(taskDto);
        return "redirect:/";
    }

    @GetMapping("/delete-task/{taskid}")
    public String deleteTask(@PathVariable("taskid") long taskid) {
        taskService.deleteTask(taskid);
        return "redirect:/";
    }


    @GetMapping("/edit-task/{taskid}")
    public String editTask(@PathVariable("taskid") long taskid, Model model) {
        Task task = new Task();
        System.out.println("temp id is " + task.getId());
        model.addAttribute("task", task);
        model.addAttribute("currentId", taskid);
        return "taskeditform";
    }

    @PostMapping("/task-save-edit")
    public String editTaskSave(@ModelAttribute("employee") Task task) {
        System.out.println(task);
        taskService.updateTask(task);
        return "redirect:/";
    }

    @GetMapping("/add-employee-totask-form/{id}")
    public String addEmployeeToTaskForm(@PathVariable("id") long id, Model model) {
        Task task = new Task();
        List<Employee> employeeList = managementService.getAllEmployees();
        model.addAttribute("employees", employeeList);
        model.addAttribute("task", task);
        model.addAttribute("currentId", id);
        return "addemployeetotaskform";
    }

    @GetMapping("/add-employee-totask/{employeeid}/{currentId}")
    public String addEmployeeToTask(@PathVariable("employeeid") long employeeid, @PathVariable("currentId") long currentid) {
        taskService.addEmployeeToTask(employeeid, currentid);
        return "redirect:/add-employee-totask-form/" + currentid;
    }

    @GetMapping("/viewtaskemployees/{taskid}")
    public String viewTaskEmployees(@PathVariable("taskid") long taskid, Model model) {
        List<Employee> taskEmployeeList = taskService.getTaskEmployees(taskid);
        model.addAttribute("employees", taskEmployeeList);
        model.addAttribute("taskid", taskid);
        return "currenttaskemployees";
    }

    @GetMapping("/taskemployeedelete/{taskid}/{employeeid}")
    public String deleteEmployee(@PathVariable("taskid") long taskid, @PathVariable("employeeid") long employeeid) {
        taskService.removeEmployeeFromTask(employeeid, taskid);
        return "redirect:/viewtaskemployees/" + taskid;
    }
}
