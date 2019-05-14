package api.repository;

import api.domain.User;
import com.google.gson.reflect.TypeToken;
import utils.FileUtils;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
    private static final String DIRECTORY = System.getProperty("user.dir");
    private static final String USERS_JSON_FILE = Paths.get(DIRECTORY + "/Users.json").toString();
    private static final Type collectionType = new TypeToken<List<User>>() {}.getType();
    private List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        this.users.addAll(FileUtils.readListFromJsonFile(USERS_JSON_FILE, collectionType));
    }

    @Override
    public User save(User user) {
        this.users.add(user);
        persist();
        return user;
    }

    @Override
    public User findById(String id) {
        return findAll()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public void delete(String id) {
        this.users.removeIf(user -> user.getId().equals(id));
        persist();
    }

    @Override
    public User update(User user) {
        this.users = findAll().stream()
                .map(entity -> {
                    if (entity.getId().equals(user.getId())) {
                        entity.setFullName(user.getFullName());
                        entity.setEmail(user.getEmail());
                        entity.setRole(user.getRole());
                        return entity;
                    }
                    return entity;
                })
                .collect(Collectors.toList());
        persist();
        return user;
    }

    private void persist() {
        FileUtils.saveListToJsonFile(this.users, USERS_JSON_FILE);
    }
}
