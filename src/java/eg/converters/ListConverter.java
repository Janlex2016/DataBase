/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.converters;

import eg.models.History;
import eg.models.User;
import eg.models.Voting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ListConverter {
   
    
    public static List<History> convertResultSetToHistoryList(ResultSet rs) throws SQLException{
        List<History> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new History(rs.getInt("ID"),rs.getInt("CANDIDATE_ID"), rs.getInt("VOTING_ID"), rs.getInt("USER_ID")));
        }
        return list;
    }
    
    public static List<Voting> convertResultSetToVotionList(ResultSet rs) throws SQLException{
        List<Voting> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new Voting(rs.getInt("ID"),rs.getString("TITLE")));
        }
        return list;
    }
    
    public static List<User> convertResultSetToUserList(ResultSet rs) throws SQLException{
        List<User> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new User(rs.getInt("ID"),rs.getString("NAME"), rs.getString("LOGIN"), rs.getString("PASSWORD"), rs.getString("ACCESS")));
        }
        return list;
    }
    public static List<User> convertResultSetToCandidateList(ResultSet rs) throws SQLException{
        List<User> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new User(rs.getInt("ID"),rs.getString("NAME"), "", "", rs.getString("ACCESS")));
        }
        return list;
    }

    public static List<History> convertResultSetToVotingWithCandidatesList(ResultSet rs) throws SQLException{
        List<History> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new History(rs.getInt("ID"), rs.getInt("CANDIDATE_ID"), rs.getInt("VOTING_ID"), 0));
        }
        return list;
    }

    public static List<String> convertResultSetToTableArray(ResultSet rs) throws SQLException {
        List<String> tableNameList = new ArrayList<>();
        if(!rs.first()) return null;
        rs.previous();
        while(rs.next()){
            tableNameList.add(rs.getString("Tables_in_VotingDataBase"));
        }
        return tableNameList;
    }

    public static List<History> convertResultSetToVotingResultList(ResultSet rs) throws SQLException {
        List<History> resultsList = new ArrayList<>();
        if(!rs.first()) return null;
        rs.previous();
        while(rs.next()){
            resultsList.add(new History(0,rs.getInt("CANDIDATE_ID"), rs.getInt("VOTING_ID"), rs.getInt("VOTES")));
        }
        return resultsList;
    }

    public static List<String> convertResultSetToDedicatedCandidatesList(ResultSet rs) throws SQLException {
        List<String> resultsList = new ArrayList<>();
        if(!rs.first()) return null;
        rs.previous();
        while(rs.next()){
            resultsList.add(rs.getString("NAME"));
        }
        return resultsList;
    }
}
