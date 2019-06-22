package api.repository;

import api.domain.Sprint;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SprintRepository extends GenericRepository<Sprint, Long> {
    List<Sprint> findAllSprintsForProject(Long id);

}
