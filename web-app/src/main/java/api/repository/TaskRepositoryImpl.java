package api.repository;

import api.domain.Task;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Task save(Task task) {
        entityManager.persist(task);
        return task;
    }

    @Override
    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();

    }

    @Override
    public void delete(Long id) {
        final Task task = entityManager.find(Task.class, id);
        if (task != null) {
            entityManager.remove(task);
        }
    }

    @Override
    public Task update(Task task) {
        return entityManager.merge(task);
    }

}
