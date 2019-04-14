package dao;

import domain.Task;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TasksRepositoryDao {

    Task addTask(Task task);

    Task getTaskById(Long id);

    List<Task> getTasks();

}
