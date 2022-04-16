package etaskify.TaskManagement.solution.repository;

import etaskify.TaskManagement.solution.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
