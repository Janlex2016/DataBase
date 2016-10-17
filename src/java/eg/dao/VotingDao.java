package eg.dao;

import eg.models.History;
import eg.models.User;
import eg.models.Voting;
import java.sql.SQLException;
import java.util.List;

public interface VotingDao extends BaseOperations<Voting> {
    
    void addCandidateToVoting(History v) throws SQLException;
    void deleteCandidateFromVoting(int candidateId, int votionId) throws SQLException;
    void addCandidateList(Voting voting, List<User> list) throws SQLException;
    Voting getByName(String name) throws SQLException;
    List<History> getVotingWithCandidatesList(int votionId) throws SQLException;
    void deleteCandidateById(int id) throws SQLException;
    List<History> getResults(int votingId) throws SQLException;
    List<String> getCandidatesDedicatedToVoting(String votingTitle) throws SQLException;

}
