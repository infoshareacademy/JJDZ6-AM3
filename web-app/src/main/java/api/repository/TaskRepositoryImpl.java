package api.repository;

import api.domain.Task;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

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

    public List<Task> findByIds(List<Long> ids) {
        return entityManager
                .createQuery("SELECT t FROM Task t WHERE t.id IN :ids")
                .setParameter("ids", ids)
                .getResultList();

    }

    @Override
    public List<Task> getAllTasksForBacklog(Long id) {
        return entityManager
                .createQuery("SELECT t FROM Task t WHERE t.projectId = :id AND t.sprintId IS NULL")
                .setParameter("id", id)
                .getResultList();
    }

    public List<Task> search(String search) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        final QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Task.class)
                .get();

        final Query query = qb
                .keyword()
                .wildcard()
                .onField("title")
                .matching(search + "*")
                .createQuery();

        return fullTextEntityManager
                .createFullTextQuery(query, Task.class)
                .getResultList();
    }

}
