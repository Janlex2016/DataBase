package eg.dao;

import eg.models.History;
import java.sql.SQLException;

public interface HistoryDao extends BaseOperations<History> {
    
    boolean isThereVotingAndUserId(int votingId, int userId) throws SQLException;
    int numberOfVoicesInVoting(int candidateId, int votingId) throws SQLException;


}
