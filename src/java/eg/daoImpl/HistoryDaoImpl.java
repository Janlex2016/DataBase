/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.daoImpl;

import eg.connection.ConnectionToDataBase;
import eg.dao.HistoryDao;
import eg.models.History;
import java.sql.SQLException;
import eg.converters.Converter;
import eg.converters.ListConverter;
import java.sql.ResultSet;
import java.util.List;

public class HistoryDaoImpl implements HistoryDao{
    
    @Override
    public void add(History history) throws SQLException{
        String insert = String.format(
                "INSERT INTO HISTORY (id, CANDIDATE_ID, VOTING_ID, USER_ID) VALUES ('%1$s','%2$s', '%3$s', '%4$s');", history.getHistoryId(), history.getCandidateId(),history.getVotionId(),history.getUserId());
        ConnectionToDataBase.getConnection().insert(insert);
    }

    @Override
    public boolean deleteById(int id) throws SQLException{
        ConnectionToDataBase.getConnection().insert("CALL VotingDataBase.DELETE_HISTORY("+id+");");
        return true;
    }

    @Override
    public History getById(int id) throws SQLException {
        String insert = String.format("HISTORY WHERE ID='%1$d'", id);
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom(insert);
        return Converter.convertResultSetToHistory(rs);
    }

    @Override
    public List<History> getAll() throws SQLException {
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom("HISTORY");
        return ListConverter.convertResultSetToHistoryList(rs);
    }

    @Override
    public boolean isThereVotingAndUserId(int votingId, int userId) throws SQLException {
        String insert = String.format("HISTORY WHERE VOTING_ID='%1$d' AND USER_ID='%2$d'", votingId, userId);
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom(insert);
        return rs.next();
    }

    @Override
    public int numberOfVoicesInVoting(int candidateId, int votingId) throws SQLException {
        String input = String.format("HISTORY where CANDIDATE_ID='%1$d' AND VOTING_ID='%2$d'", candidateId, votingId);
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom(input);
        int numberOfVoices = 0;
        while(rs.next()) numberOfVoices++;
        return numberOfVoices;
    }
}
