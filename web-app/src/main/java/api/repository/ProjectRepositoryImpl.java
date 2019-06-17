package api.repository;

import api.domain.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjectRepositoryImpl implements ProjectRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Project save(Project project) {
        entityManager.persist(project);
        return project;
    }

    @Override
    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> findAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        final Project project = entityManager.find(Project.class, id);
        if (project != null) {
            entityManager.remove(project);
        }
    }

    @Override
    public Project update(Project project) {
        return entityManager.merge(project);
    }

}
