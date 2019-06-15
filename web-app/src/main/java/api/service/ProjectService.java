package api.service;

import api.domain.Project;
import api.repository.ProjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProjectService {

    @Inject
    ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project findById(String id) {
        return projectRepository.findById(id);
    }

    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    public void delete(String id) {
        projectRepository.delete(id);
    }

    public Project update(Project project) {
        return projectRepository.update(project);
    }
}
