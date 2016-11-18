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

public interface UserService {

    void addUser(String name, String login, String password, String access) throws SQLException, IncorrectInput;
    void addCandidate(String name, String access) throws SQLException, IncorrectInput;
    void deleteCandidateById(int candidateId) throws SQLException;
    User getUserById(int userId) throws SQLException, UserNotFound, AccessDenied;
    User getUserByName(String name) throws SQLException,AccessDenied,UserNotFound;
    User getCandidateById(int userId) throws SQLException, CandidateNotFound, AccessDenied;
    User getCandidateByName(String name) throws SQLException, CandidateNotFound, AccessDenied;
    User enter(String login, String password) throws SQLException, UserNotFound, ListIsEmpty;
    void vote(int candidateId, int votingId, int userId) throws SQLException,AccessDenied,CandidateNotFound,HistoryNotFound,UserNotFound,VotingNotFound;
    void deleteById(int id)throws SQLException, UserNotFound;
    List<User> getAll()throws SQLException, ListIsEmpty;
    List<User> getAllCandidates()throws SQLException, ListIsEmpty;
    User getById(int id) throws SQLException,UserNotFound;
    String[] getCandidateNameArray(List<User> list) throws SQLException;
    String[] getUserArray() throws SQLException, ListIsEmpty;
    String[] getCandidateArray() throws SQLException, ListIsEmpty;
}
