/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.service;

import eg.exceptions.*;
import eg.models.User;
import eg.models.Voting;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author noname
 */
public interface VotingService {
    
    public void addCandidateToVoting(int candidateId, int votionId) throws SQLException,AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound;
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException,AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound;
    public void add(String title)throws SQLException,IncorrectInput;
    public void addCandidateList(Voting voting, List<User> list) throws SQLException, VotingNotFound;
    public List<User> getVotingCandidateList(int votionId) throws SQLException, UserNotFound;
    public void deleteById(int id)throws SQLException, VotingNotFound, UserNotFound;
    public void deleteByCandidateId(int id)throws SQLException;
    public List<Voting> getAll() throws SQLException, ListIsEmpty;
    public Voting getById(int id) throws SQLException, VotingNotFound, UserNotFound;
    public Voting getByName(String name) throws SQLException, VotingNotFound, UserNotFound;
    public Voting getCandidateByName(String name) throws SQLException, VotingNotFound, UserNotFound;
    public String[] getVotingTitleArray() throws SQLException, ListIsEmpty;
    public String[] getVotingTitleArrayWithNull() throws SQLException, ListIsEmpty;
    public String[] getVotingArray() throws SQLException, ListIsEmpty;
    public List<User> getPossibleCandidateList(int votionId) throws SQLException, UserNotFound;
  
}
