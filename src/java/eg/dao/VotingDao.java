package eg.dao;

import eg.models.History;
import eg.models.User;
import eg.models.Voting;
import java.sql.SQLException;
import java.util.List;

public interface VotingDao extends BaseOperations<Voting> {
    
    public void addCandidateToVoting(History v) throws SQLException;
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException;
    public void addCandidateList(Voting voting, List<User> list) throws SQLException;
    public Voting getByName(String name) throws SQLException;
    public List<History> getVotingWithCandidatesList(int votionId) throws SQLException;
    public void deleteCandidateById(int id) throws SQLException;

}
