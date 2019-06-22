package api.repository;

import api.domain.Task;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TaskRepository extends GenericRepository<Task, Long> {
    List<Task> findByIds(List<Long> taskIds);

    List<Task> getAllTasksForBacklog(Long id);
}
