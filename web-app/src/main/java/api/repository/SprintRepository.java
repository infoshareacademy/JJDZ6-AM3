package api.repository;

import api.domain.Sprint;

import javax.ejb.Local;

@Local
public interface SprintRepository extends GenericRepository<Sprint, String> {
}
