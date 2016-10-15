/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.dao;

import java.sql.SQLException;
import java.util.List;


public interface BaseOperations<T> {
    
    public List<T> getAll()throws SQLException;
    public void add(T obj) throws SQLException;
    public boolean deleteById(int id) throws SQLException;
    public T getUserById(int id) throws SQLException;
}
