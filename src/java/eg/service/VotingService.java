/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.service;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.IncorrectInput;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.User;
import eg.models.Votion;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author noname
 */
public interface VotingService {
    
    public void addCandidateToVotion(int candidateId, int votionId) throws SQLException,AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound;
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException,AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound;
    public void add(String title)throws SQLException,IncorrectInput;
    public void addCandidateList(Votion votion, List<User> list) throws SQLException, VotingNotFound;
    public List<User> getVotingCandidateList(int votionId) throws SQLException, UserNotFound;
    public void deleteById(int id)throws SQLException, VotingNotFound, UserNotFound;
    public void deleteByCandidateId(int id)throws SQLException;
    public List<Votion> getAll()throws SQLException;
    public Votion getById(int id) throws SQLException, VotingNotFound, UserNotFound;
    public Votion getByName(String name) throws SQLException, VotingNotFound, UserNotFound;
    public String[] getVotionTitleArray() throws SQLException;
    public String[] getVotionTitleArrayWithNull() throws SQLException;
    public String[] getVotionArray() throws SQLException;
    public List<User> getPossibleCandidateList(int votionId) throws SQLException, UserNotFound;
  
}
