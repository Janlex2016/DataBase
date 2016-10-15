/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.dao;

import eg.models.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao extends BaseOperations<User> {
    
    public boolean deleteByName(String name) throws SQLException;
    public User getByName(String name) throws SQLException;
    public User getCandidateByName(String name) throws SQLException;
    public List<User> getUsersByAccess(String access) throws SQLException;
    public User enter(String login, String password) throws SQLException;
    public boolean deleteCandidateById(int id) throws SQLException;
    void addCandidate(User user) throws SQLException;
    List<User> getAllCandidates()throws SQLException;
    public User getCandidateById(int id) throws SQLException;


}
