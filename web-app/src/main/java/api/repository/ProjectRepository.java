package api.repository;

import api.domain.Project;

import javax.ejb.Local;

@Local
public interface ProjectRepository extends GenericRepository<Project, Long> {
}
