/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.daoImpl;

import eg.connection.ConnectionToDataBase;
import eg.dao.UserDao;
import eg.models.User;
import eg.converters.Converter;
import eg.converters.ListConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{

    @Override
    public void add(User user) throws SQLException {
        String input = String.format("INSERT INTO USERS (ID, NAME, LOGIN, PASSWORD, ACCESS) VALUES ('%1$d','%2$s', '%3$s', '%4$s', '%5$s');",user.getId(),user.getName(),user.getLogin(),user.getPassword(),String.valueOf(user.getAccess()));
        ConnectionToDataBase.getConnection().insert(input);
//        ConnectionToDataBase.getConnection().insert("INSERT INTO Users (id, name, login, password, access) VALUES ('"+user.getId()+"','"+user.getName()+"', '"+user.getLogin()+"', '"+user.getPassword()+"', '"+String.valueOf(user.getAccess())+"');");
    }

    @Override
    public boolean deleteById(int id) throws SQLException{
        if(getUserById(id)==null) return false;
        String input = String.format("DELETE FROM USERS WHERE ID=%1$d", id);
        ConnectionToDataBase.getConnection().insert(input);
//        ConnectionToDataBase.getConnection().insert("DELETE FROM Users WHERE id='"+id+"'");
        return true;
    }

    @Override
    public boolean deleteCandidateById(int id) throws SQLException{
        ConnectionToDataBase.getConnection().insert("CALL VotingDataBase.DELETE_CANDIDATE("+id+");");
        return true;
    }

    @Override
    public void addCandidate(User user) throws SQLException {
        String input = String.format("INSERT INTO CANDIDATES (NAME, ACCESS) VALUES ('%1$s', '%2$s');",user.getName(),String.valueOf(user.getAccess()));
        ConnectionToDataBase.getConnection().insert(input);

    }

    @Override
    public List<User> getAllCandidates() throws SQLException {
        ResultSet rs = ConnectionToDataBase.getConnection().query("CANDIDATES");
        return ListConverter.convertResultSetToCandidateList(rs);
    }

    @Override
    public boolean deleteByName(String name) throws SQLException{
        
        if(getByName(name)==null) return false;
        String input = String.format("DELETE FROM USERS WHERE name='%1$s", name);
        ConnectionToDataBase.getConnection().insert(input);   
//        ConnectionToDataBase.getConnection().insert("DELETE FROM Users WHERE name='"+name+"'");   
        return true;
    }

    @Override
    public User getUserById(int id) throws SQLException{
        String input = String.format("USERS WHERE ID='%1$d'", id);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Users WHERE id='"+id+"'");
        return Converter.convertResultSetToUser(rs);
    }

    @Override
    public User getCandidateById(int id) throws SQLException{
        String input = String.format("CANDIDATES WHERE ID='%1$d'", id);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Users WHERE id='"+id+"'");
        return Converter.convertResultSetToCandidate(rs);
    }

    @Override
    public User getByName(String name) throws SQLException{
        String input = String.format("USERS WHERE name='%1$s'", name);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Users WHERE name='"+name+"'");
        return Converter.convertResultSetToUser(rs);
    }

    @Override
    public User getCandidateByName(String name) throws SQLException{
        String input = String.format("CANDIDATES WHERE NAME='%1$s'", name);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Users WHERE name='"+name+"'");
        return Converter.convertResultSetToCandidate(rs);
    }
    
    @Override
    public List<User> getAll() throws SQLException {
        ResultSet rs = ConnectionToDataBase.getConnection().query("USERS");
        return ListConverter.convertResultSetToUserList(rs);
    }

    @Override
    public List<User> getUsersByAccess(String access) throws SQLException {
        String input = String.format("USERS WHERE access='%1$s'", access);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Users WHERE access='"+access+"'");
        return ListConverter.convertResultSetToUserList(rs);
    }

    @Override
    public User enter(String login, String password) throws SQLException {
        String input = String.format("USERS WHERE login='%1$s' AND password='%2$s'", login,password);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Users WHERE login='"+login+"' AND password='"+password+"'");
        return Converter.convertResultSetToUser(rs);
    }
}
