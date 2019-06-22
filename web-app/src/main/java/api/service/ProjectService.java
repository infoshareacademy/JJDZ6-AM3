package api.service;

import api.domain.Project;
import api.domain.Sprint;
import api.domain.Task;
import api.domain.User;
import api.repository.ProjectRepository;
import api.repository.SprintRepository;
import api.repository.TaskRepository;
import api.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProjectService {

    @Inject
    ProjectRepository projectRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    SprintRepository sprintRepository;
    @Inject
    TaskRepository taskRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    public void delete(Long id) {
        projectRepository.delete(id);
    }

    public Project update(Project project) {
        Project projectEntity = projectRepository.findById(project.getId());
        projectEntity.setName(project.getName());
        return projectRepository.update(projectEntity);
    }

    public void addUserToProject(Long id, User user) {
        Project projectEntity = projectRepository.findById(id);
        User userEntity = userRepository.findById(user.getId());
        projectEntity.addUser(userEntity);
        projectRepository.update(projectEntity);

    }

    public void addTaskToProject(Long id, Task task) {
        Project projectEntity = projectRepository.findById(id);
        Task taskEntity = taskRepository.save(task);
        projectEntity.addTaskToBacklog(taskEntity);
        projectRepository.update(projectEntity);
    }

    public List<Sprint> getAllSprintsForProject(Long id) {
        return sprintRepository.findAllSprintsForProject(id);
    }

    public List<Task> getBacklog(Long id) {
        return taskRepository.getAllTasksForBacklog(id);
    }
}
