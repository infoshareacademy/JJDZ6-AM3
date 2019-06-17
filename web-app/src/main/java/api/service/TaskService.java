package api.service;

import api.domain.Task;
import api.domain.User;
import api.repository.TaskRepository;
import api.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TaskService {

    @Inject
    TaskRepository taskRepository;
    @Inject
    UserRepository userRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(setAssignee(task));
    }

    public Task findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public void delete(Long id) {
        taskRepository.delete(id);
    }

    public Task update(Task task) {
        return taskRepository.update(setAssignee(task));
    }

    private Task setAssignee(Task task) {
        if (task.getAssignee() != null) {
            User assignee = userRepository.findById(task.getAssignee().getId());
            task.setAssignee(assignee);
        }
        return task;
    }

}
