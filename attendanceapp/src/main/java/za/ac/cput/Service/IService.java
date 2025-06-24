package za.ac.cput.Service;

import java.util.List;

public interface IService {
    <T> T create(T entity);
    <T> T read(String id);
    <T> T update(T entity);
    <T> void delete(String id);
    <T> List<T> getAll();
    <T> T findById(String id);
}
