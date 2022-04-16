package etaskify.TaskManagement.solution.service;


import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.model.Task;
import etaskify.TaskManagement.solution.web.dto.TaskDto;

import java.util.List;

public interface TaskService {
    Task saveTask(TaskDto taskDto);

    Task updateTask(Task task);

    void deleteTask(long id);

    Task addEmployeeToTask(long employeeid, long taskid);

    Task removeEmployeeFromTask(long employeeid, long taskid);

    List<Task> getAllTasks();

    List<Employee> getTaskEmployees(long id);
}

