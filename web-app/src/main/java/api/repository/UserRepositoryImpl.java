package api.repository;

import api.domain.User;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        final User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public List<User> findAllUsersForProject(Long id) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.projectId = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<User> search(String search) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        final QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(User.class)
                .get();

        final Query query = qb
                .keyword()
                .wildcard()
                .onField("fullName")
                .matching(search + "*")
                .createQuery();

        return fullTextEntityManager
                .createFullTextQuery(query, User.class)
                .getResultList();
    }
}
