package etaskify.TaskManagement.solution.service;


import etaskify.TaskManagement.solution.Exception.ResourceNotFoundException;
import etaskify.TaskManagement.solution.model.Employee;
import etaskify.TaskManagement.solution.model.Task;
import etaskify.TaskManagement.solution.repository.EmployeeRepository;
import etaskify.TaskManagement.solution.repository.TaskRepository;
import etaskify.TaskManagement.solution.web.dto.TaskDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

    private final ManagementService managementService;

    public TaskServiceImpl(TaskRepository taskRepository, EmployeeRepository employeeRepository, ManagementService managementService) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.managementService = managementService;
    }

    @Override
    public Task saveTask(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Task existingTask = taskRepository.findById(task.getId()).orElseThrow(() -> {
            return new ResourceNotFoundException("Task", "Id", task.getId());
        });
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        taskRepository.save(existingTask);
        return existingTask;
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task addEmployeeToTask(long employeeid, long taskid) {
        Task existingTask = taskRepository.findById(taskid).orElseThrow(() -> {
            return new ResourceNotFoundException("Task", "Id", taskid);
        });
        try {
            if (existingTask.getEmployees() == "") {
                String temp = existingTask.getEmployees() + employeeid + ",";
                existingTask.setEmployees(temp);
                System.out.println(existingTask);
            } else {
                String[] idsString = existingTask.getEmployees().split("\\,");
                List<Long> idList = new ArrayList<>();
                for (String s :
                        idsString) {
                    System.out.println("Bs: " + s);

                }
                try {
                    for (int i = 0; i < idsString.length; i++) {
                        idList.add(Long.parseLong(idsString[i]));
                    }
                    idList.add(employeeid);
                    String newEmployeeList = StringUtils.join(idList, ",");
                    existingTask.setEmployees(newEmployeeList);
                } catch (NumberFormatException e) {
                    System.out.println("InvalidFormat");
                    String[] temparray = existingTask.getEmployees().split("\\,");
                    System.out.println(Arrays.toString(temparray));
                }
            }

        } catch (NullPointerException e) {
            String temp = "" + employeeid;
            existingTask.setEmployees(temp);
            System.out.println(existingTask);
        }


        return updateTask(existingTask);
    }

    @Override
    public Task removeEmployeeFromTask(long employeeid, long taskid) {
        Task existingTask = taskRepository.findById(taskid).orElseThrow(() -> {
            return new ResourceNotFoundException("Task", "Id", taskid);
        });
        try {
            String[] idsString = existingTask.getEmployees().split("\\,");
            List<Long> idList = new ArrayList<>();
            for (int i = 0; i < idsString.length; i++) {
                idList.add(Long.valueOf(idsString[i]));
            }
            idList.remove(employeeid);
            String newEmployeeList = StringUtils.join(idList, ",");
            existingTask.setEmployees(newEmployeeList);
        } catch (NullPointerException e) {

        }


        return updateTask(existingTask);
    }


    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Employee> getTaskEmployees(long taskid) {
        Task existingTask = taskRepository.findById(taskid).orElseThrow(() -> {
            return new ResourceNotFoundException("Task", "Id", taskid);
        });
        List<Employee> returnList = new ArrayList<>();
        try {
            existingTask.getEmployees();
            String[] idsString = existingTask.getEmployees().split("\\,");
            List<Long> idList = new ArrayList<>();
            for (int i = 0; i < idsString.length; i++) {
                returnList.add(managementService.getEmployeeById(Long.valueOf(idsString[i])));
            }
        } catch (NullPointerException e) {

        }
//        System.out.println(returnList);
        return returnList;
    }
}
