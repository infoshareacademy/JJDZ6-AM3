package api.repository;

import api.domain.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserRepository extends GenericRepository<User, Long> {
    List<User> findAllUsersForProject(Long id);

    List<User> search(String query);

    User authenticate(String login, String password);

    void registerUser(User user);
}
