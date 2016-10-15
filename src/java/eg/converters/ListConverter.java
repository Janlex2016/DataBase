/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.converters;

import eg.models.History;
import eg.models.User;
import eg.models.Votion;
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
    
    public static List<Votion> convertResultSetToVotionList(ResultSet rs) throws SQLException{
        List<Votion> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new Votion(rs.getInt("ID"),rs.getString("TITLE")));
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
}
