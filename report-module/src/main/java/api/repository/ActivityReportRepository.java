package api.repository;

import api.domain.ActivityEntry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ActivityReportRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void saveUserLastLoginTime(ActivityEntry entry) {
        entityManager.persist(entry);
    }

    public List<ActivityEntry> getActivities() {
        return entityManager.createQuery("SELECT e FROM ActivityEntry e", ActivityEntry.class).getResultList();
    }
}
