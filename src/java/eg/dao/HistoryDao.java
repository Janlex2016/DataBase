/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.dao;

import eg.models.History;
import java.sql.SQLException;

public interface HistoryDao extends BaseOperations<History> {
    
    public boolean isThereVotingAndUserId(int votingId, int userId) throws SQLException;
    public int numberOfVoicesInVoting(int candidateId, int votingId) throws SQLException;


}
