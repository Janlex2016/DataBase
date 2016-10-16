/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.daoImpl;

import eg.connection.ConnectionToDataBase;
import eg.dao.VotingDao;
import eg.models.Voting;
import eg.converters.Converter;
import eg.converters.ListConverter;
import eg.models.History;
import eg.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotingDaoImpl implements VotingDao {

    @Override
    public void add(Voting voting) throws SQLException {
        //TODO Check id
        String input = String.format("INSERT INTO VOTING (TITLE) VALUES ('%1$s');", voting.getTitle());
        ConnectionToDataBase.getConnection().insert(input);
//        ConnectionToDataBase.getConnection().insert("INSERT INTO Votion (id, title) VALUES ('"+votion.getId()+"','"+votion.getTitle()+"');");
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CALL VotingDataBase.DELETE_VOTING("+id+");"
        );
        return true;
    }

    @Override
    public void addCandidateToVoting(History v) throws SQLException {
        String input = String.format("INSERT INTO VOTING_CANDIDATES (ID, CANDIDATE_ID, VOTING_ID) VALUES ('%1$d','%2$d' , '%3$d');", v.getHistoryId(), v.getCandidateId(), v.getVotionId());
        ConnectionToDataBase.getConnection().insert(input);
//        ConnectionToDataBase.getConnection().insert("INSERT INTO VotionTab (id, CandidateId, VotionId) VALUES ('"+v.getHistoryId()+"','"+v.getCandidateId()+"' , '"+v.getVotionId()+"');");    
    }

    @Override
    public Voting getById(int id) throws SQLException {
        String input = String.format("VOTING WHERE ID='%1$d'", id);
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom("Votion WHERE id='"+id+"'");
        return Converter.convertResultSetToVoting(rs);
    }

    @Override
    public Voting getByName(String name) throws SQLException {
        String input = String.format("VOTING WHERE TITLE='%1$s'", name);
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom("Votion WHERE title='"+name+"'");
        return Converter.convertResultSetToVoting(rs);
    }

    @Override
    public List<Voting> getAll() throws SQLException {
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom("VOTING");
        return ListConverter.convertResultSetToVotionList(rs);
    }

    @Override
    public void addCandidateList(Voting voting, List<User> list) throws SQLException {
        voting.setCandidateIds(list);
    }

    @Override
    public List<History> getVotingWithCandidatesList(int votingId) throws SQLException {
        String input = String.format("VOTING_CANDIDATES WHERE VOTING_ID='%1$d'", votingId);
        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().selectFrom("VotionTab WHERE VotionId='"+votionId+"'");
        return ListConverter.convertResultSetToVotingWithCandidatesList(rs);
    }

    @Override
    public void deleteCandidateById(int id) throws SQLException {
        String input = String.format("DELETE FROM VOTING_CANDIDATES WHERE CANDIDATE_ID=%1$d", id);
        ConnectionToDataBase.getConnection().insert(input);
    }

    @Override
    public void deleteCandidateFromVoting(int candidateId, int votionId) throws SQLException {
        String input = String.format("DELETE FROM VOTING_CANDIDATES WHERE CANDIDATE_ID=%1$d AND VOTING_ID=%2$d", candidateId, votionId);
        ConnectionToDataBase.getConnection().insert(input);
    }

    @Override
    public List<History> getResults(int votingId) throws SQLException{

        List<History> resultList = new ArrayList<>();
        ResultSet rs = ConnectionToDataBase.getConnection().insertText(
                "CALL VotingDataBase.GET_RESULTS_WITH_VOTING_ID(" + votingId + ");"
        );
        resultList = ListConverter.convertResultSetToVotingResultList(rs);
        return resultList;
    }

}
