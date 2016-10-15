/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.daoImpl;

import eg.connection.ConnectionToDataBase;
import eg.dao.VotingDao;
import eg.models.Votion;
import eg.converters.Converter;
import eg.converters.ListConverter;
import eg.models.History;
import eg.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VotingDaoImpl implements VotingDao {

    @Override
    public void add(Votion votion) throws SQLException {
        //TODO Check id
        String input = String.format("INSERT INTO VOTING (TITLE) VALUES ('%1$s');", votion.getTitle());
        ConnectionToDataBase.getConnection().insert(input);
//        ConnectionToDataBase.getConnection().insert("INSERT INTO Votion (id, title) VALUES ('"+votion.getId()+"','"+votion.getTitle()+"');");
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        ConnectionToDataBase.getConnection().insert("SET SQL_SAFE_UPDATES = 0;\n" +
            "DELETE VOTING, VOTING_CANDIDATES FROM VOTING, VOTING_CANDIDATES WHERE VOTING.ID = VOTING_CANDIDATES.VOTING_ID AND VOTING.ID = "+id+"");
        return true;
    }

    @Override
    public void addCandidateToVotion(History v) throws SQLException {
        String input = String.format("INSERT INTO VOTING_CANDIDATES (ID, CANDIDATE_ID, VOTING_ID) VALUES ('%1$d','%2$d' , '%3$d');",v.getHistoryId(),v.getCandidateId(),v.getVotionId());
        ConnectionToDataBase.getConnection().insert(input);    
//        ConnectionToDataBase.getConnection().insert("INSERT INTO VotionTab (id, CandidateId, VotionId) VALUES ('"+v.getHistoryId()+"','"+v.getCandidateId()+"' , '"+v.getVotionId()+"');");    
    }
    
    @Override
    public Votion getById(int id) throws SQLException{
        String input = String.format("VOTING WHERE ID='%1$d'", id);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Votion WHERE id='"+id+"'");
        return Converter.convertResultSetToVoting(rs);
    }
    
    @Override
    public Votion getByName(String name) throws SQLException{
        String input = String.format("VOTING WHERE TITLE='%1$s'", name);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Votion WHERE title='"+name+"'");
        return Converter.convertResultSetToVoting(rs);
    }

    @Override
    public List<Votion> getAll() throws SQLException {
        ResultSet rs = ConnectionToDataBase.getConnection().query("VOTING");
        return ListConverter.convertResultSetToVotionList(rs);
    }

    @Override
    public void addCandidateList(Votion votion ,List<User> list) throws SQLException {
        votion.setCandidateIds(list);
    }
    
    @Override
    public List<History> getVotingWithCandidatesList(int votionId) throws SQLException {
        String input = String.format("VOTING_CANDIDATES WHERE VOTING_ID='%1$d'", votionId);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("VotionTab WHERE VotionId='"+votionId+"'");
        return ListConverter.convertResultSetToVotingWithCandidatesList(rs);
    }
    
    @Override
    public void deleteCandidateById(int id) throws SQLException {
        String input = String.format("DELETE FROM VOTING_CANDIDATES WHERE CANDIDATE_ID=%1$d", id);
        ConnectionToDataBase.getConnection().insert(input); 
    }

    @Override
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException {
        String input = String.format("DELETE FROM VOTING_CANDIDATES WHERE CANDIDATE_ID=%1$d AND VOTING_ID=%2$d",candidateId, votionId);
        ConnectionToDataBase.getConnection().insert(input);      }
}
