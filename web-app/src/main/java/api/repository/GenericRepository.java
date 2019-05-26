package api.repository;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GenericRepository<T, ID> {

    T save(T entity);

    T findById(ID id);

    List<T> findAll();

    void delete(ID id);

    T update(T entity);
}
