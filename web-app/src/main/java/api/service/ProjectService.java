package api.service;

import api.domain.Project;
import api.repository.ProjectRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProjectService {

    @Inject
    ProjectRepository projectRepository;

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
        return projectRepository.update(project);
    }
}
