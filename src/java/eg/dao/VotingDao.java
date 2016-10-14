package eg.dao;

import eg.bo.BaseOperations;
import eg.models.History;
import eg.models.User;
import eg.models.Votion;
import java.sql.SQLException;
import java.util.List;

public interface VotingDao extends BaseOperations<Votion> {
    
    public void addCandidateToVotion(History v) throws SQLException;
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException;
    public void addCandidateList(Votion votion, List<User> list) throws SQLException;
    public Votion getByName(String name) throws SQLException;
    public List<History> getVotionWithCandidatesList(int votionId) throws SQLException;
    public void deleteCandidateById(int id) throws SQLException;

}
