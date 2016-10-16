package eg.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseOperations<T> {
    
    List<T> getAll()throws SQLException;
    void add(T obj) throws SQLException;
    boolean deleteById(int id) throws SQLException;
    T getById(int id) throws SQLException;
}
