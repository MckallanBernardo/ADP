package za.ac.cput.service;

import java.util.List;

public interface IService <T, ID> {
    T create(T entity);
    T read(String id);
    T update(T entity);
    void delete(String id);
    List<T> getAll();
    T findById(String id);
}
