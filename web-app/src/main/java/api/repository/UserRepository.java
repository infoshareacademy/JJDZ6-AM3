package api.repository;

import api.domain.User;

import javax.ejb.Local;

@Local
public interface UserRepository extends GenericRepository<User,String> {
}
