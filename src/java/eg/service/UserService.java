/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.service;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.exceptions.IncorrectInput;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author noname
 */
public interface UserService {

    public void addUser(String name, String login, String password, String access) throws SQLException, IncorrectInput;
    public void addCandidate(String name, String access) throws SQLException, IncorrectInput;
    public void deleteCandidate(int candidateId) throws SQLException;
    public List<User> getUsers() throws SQLException, ListIsEmpty;
    public User getUserById(int userId) throws SQLException, UserNotFound, AccessDenied;
    public List<User> getCandidates() throws SQLException, ListIsEmpty;
    public User getCandidateById(int userId) throws SQLException, CandidateNotFound, AccessDenied;
    public User getCandidateByName(String name) throws SQLException, CandidateNotFound, AccessDenied;
    public List<User> getAdmins() throws SQLException, ListIsEmpty;
    public User getAdminById(int userId) throws SQLException ,UserNotFound, AccessDenied;
    public void deleteByName(String name) throws SQLException, UserNotFound;
    public User enter(String login, String password) throws SQLException, UserNotFound, ListIsEmpty;
    public void vote(int candidateId, int votionId, int userId) throws SQLException,AccessDenied,CandidateNotFound,HistoryNotFound,UserNotFound,VotingNotFound;
    public void deleteById(int id)throws SQLException, UserNotFound;
    public List<User> getAll()throws SQLException, ListIsEmpty;
    public List<User> getAllCandidates()throws SQLException, ListIsEmpty;
    public User getById(int id) throws SQLException,UserNotFound;
    public String[] getCandidateNameArray(List<User> list) throws SQLException;
    public String[] getUserArray() throws SQLException, ListIsEmpty;
    public String[] getCandidateArray() throws SQLException, ListIsEmpty;
}
