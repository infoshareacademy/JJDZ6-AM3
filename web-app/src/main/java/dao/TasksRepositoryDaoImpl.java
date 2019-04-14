package dao;

import domain.Task;
import repository.TasksRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class TasksRepositoryDaoImpl implements TasksRepositoryDao {

    @Inject
    private TasksRepository tasksRepository;

    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return tasksRepository.getTasks();
    }
}
