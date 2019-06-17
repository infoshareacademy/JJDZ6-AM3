package api.repository;

import api.domain.Task;

import javax.ejb.Local;

@Local
public interface TaskRepository extends GenericRepository<Task, Long> {
}
