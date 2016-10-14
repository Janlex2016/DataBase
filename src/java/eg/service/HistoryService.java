/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.service;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.exceptions.IncorrectInput;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.History;
import eg.models.User;
import java.sql.SQLException;
import java.util.List;

public interface HistoryService{
    
    public void add(int candidateId, int votionId, int userId) throws SQLException,AccessDenied,IncorrectInput, CandidateNotFound, UserNotFound, VotingNotFound;
    public boolean isThereVotionAndUserId(int votionId, int userId) throws SQLException;
    public List<History> countVoices(int votionId) throws SQLException;
    public void deleteById(int id)throws SQLException,HistoryNotFound;
    public List<History> getAll()throws SQLException,HistoryNotFound,ListIsEmpty;
    public History getById(int id) throws SQLException,HistoryNotFound;
    public String[] getHistoryArray() throws SQLException, ListIsEmpty;
    public List<User> rewrited(List<History> list) throws SQLException, ListIsEmpty;
}
