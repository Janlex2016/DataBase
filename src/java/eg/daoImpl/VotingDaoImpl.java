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

/**
 *
 * @author noname
 */
public class VotingDaoImpl implements VotingDao {

    @Override
    public void add(Votion votion) throws SQLException {
        String input = String.format("INSERT INTO Votion (id, title) VALUES ('%1$d','%2$s');", votion.getId(), votion.getTitle());
        ConnectionToDataBase.getConnection().insert(input);
//        ConnectionToDataBase.getConnection().insert("INSERT INTO Votion (id, title) VALUES ('"+votion.getId()+"','"+votion.getTitle()+"');");
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        ConnectionToDataBase.getConnection().insert("SET SQL_SAFE_UPDATES = 0;\n" +
            "DELETE Votion, VotionTab FROM Votion, VotionTab WHERE Votion.id = VotionTab.VotionId AND Votion.id = "+id+"");
        return true;
    }

    @Override
    public void addCandidateToVotion(History v) throws SQLException {
        String input = String.format("INSERT INTO VotionTab (id, CandidateId, VotionId) VALUES ('%1$d','%2$d' , '%3$d');",v.getHistoryId(),v.getCandidateId(),v.getVotionId());
        ConnectionToDataBase.getConnection().insert(input);    
//        ConnectionToDataBase.getConnection().insert("INSERT INTO VotionTab (id, CandidateId, VotionId) VALUES ('"+v.getHistoryId()+"','"+v.getCandidateId()+"' , '"+v.getVotionId()+"');");    
    }
    
    @Override
    public Votion getById(int id) throws SQLException{
        String input = String.format("Votion WHERE id='%1$d'", id);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Votion WHERE id='"+id+"'");
        return Converter.convertResultSetToVotion(rs);
    }
    
    @Override
    public Votion getByName(String name) throws SQLException{
        String input = String.format("Votion WHERE title='%1$s'", name);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("Votion WHERE title='"+name+"'");
        return Converter.convertResultSetToVotion(rs);
    }

    @Override
    public List<Votion> getAll() throws SQLException {
        ResultSet rs = ConnectionToDataBase.getConnection().query("Votion");
        return ListConverter.convertResultSetToVotionList(rs);
    }

    @Override
    public void addCandidateList(Votion votion ,List<User> list) throws SQLException {
        votion.setCandidateIds(list);
    }
    
    @Override
    public List<History> getVotionWithCandidatesList(int votionId) throws SQLException {
        String input = String.format("VotionTab WHERE VotionId='%1$d'", votionId);
        ResultSet rs = ConnectionToDataBase.getConnection().query(input);
//        ResultSet rs = ConnectionToDataBase.getConnection().query("VotionTab WHERE VotionId='"+votionId+"'");
        return ListConverter.convertResultSetToVotionWithCandidatesList(rs);
    }
    
    @Override
    public void deleteCandidateById(int id) throws SQLException {
        String input = String.format("DELETE FROM VotionTab WHERE CandidateId=%1$d", id);
        ConnectionToDataBase.getConnection().insert(input); 
    }

    @Override
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException {
        String input = String.format("DELETE FROM VotionTab WHERE CandidateId=%1$d AND VotionId=%2$d",candidateId, votionId);
        ConnectionToDataBase.getConnection().insert(input);      }
}
