package api.repository;

import api.domain.Sprint;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class SprintRepositoryImpl implements SprintRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Sprint save(Sprint sprint) {
        entityManager.persist(sprint);
        return sprint;
    }

    @Override
    public Sprint findById(Long id) {
        return entityManager.find(Sprint.class, id);
    }

    @Override
    public List<Sprint> findAll() {
        return entityManager.createQuery("SELECT p FROM Sprint p", Sprint.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        final Sprint sprint = entityManager.find(Sprint.class, id);
        if (sprint != null) {
            entityManager.remove(sprint);
        }
    }

    @Override
    public Sprint update(Sprint sprint) {
        return entityManager.merge(sprint);
    }

    @Override
    public List<Sprint> findAllSprintsForProject(Long id) {
        return entityManager
                .createQuery("SELECT s FROM Sprint s WHERE s.projectId = :id")
                .setParameter("id", id)
                .getResultList();
    }

}
