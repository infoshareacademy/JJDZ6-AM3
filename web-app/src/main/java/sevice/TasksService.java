package sevice;

import dao.TasksRepositoryDaoImpl;
import domain.Task;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class TasksService {
    @Inject
    TasksRepositoryDaoImpl tasksRepositoryDao;

    public List<Task> getTasks() {
        return tasksRepositoryDao.getTasks();
    }
}
