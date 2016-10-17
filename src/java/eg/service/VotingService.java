package eg.service;

import eg.exceptions.*;
import eg.models.History;
import eg.models.User;
import eg.models.Voting;
import java.sql.SQLException;
import java.util.List;

public interface VotingService {
    
    void addCandidateToVoting(int candidateId, int votingId) throws SQLException,AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound;
    void deleteCandidateFromVoting(int candidateId, int votingId) throws SQLException,AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound;
    void add(String title)throws SQLException,IncorrectInput;
    void addCandidateList(Voting voting, List<User> list) throws SQLException, VotingNotFound;
    List<User> getVotingCandidateList(int votingId) throws SQLException, UserNotFound;
    void deleteById(int id)throws SQLException, VotingNotFound, UserNotFound;
//    void deleteByCandidateId(int id)throws SQLException;
    List<Voting> getAll() throws SQLException, ListIsEmpty;
    Voting getById(int id) throws SQLException, VotingNotFound, UserNotFound;
    Voting getByName(String name) throws SQLException, VotingNotFound, UserNotFound;
//    Voting getCandidateByName(String name) throws SQLException, VotingNotFound, UserNotFound;
    String[] getVotingTitleArray() throws SQLException, ListIsEmpty;
    String[] getVotingTitleArrayWithNull() throws SQLException, ListIsEmpty;
    String[] getVotingArray() throws SQLException, ListIsEmpty;
    List<User> getPossibleCandidateList(int votingId) throws SQLException, UserNotFound;
    String[] getResultArray(int votingId) throws SQLException, CandidateNotFound, AccessDenied, UserNotFound, VotingNotFound;
    String[] getCandidatesDedicatedToVoting(String votingTitle) throws SQLException;
}
