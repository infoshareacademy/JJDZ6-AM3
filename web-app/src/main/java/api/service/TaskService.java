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
        return taskRepository.save(task);
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
        Task taskEntity = taskRepository.findById(task.getId());
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        return taskRepository.update(taskEntity);
    }

    public Task assignUser(Long id, User user) {
        User userEntity = userRepository.findById(user.getId());
        Task taskEntity = taskRepository.findById(id);

        taskEntity.setAssignee(userEntity);

        return taskRepository.update(taskEntity);
    }


}
