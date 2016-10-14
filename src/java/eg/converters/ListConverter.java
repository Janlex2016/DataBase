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
            list.add(new History(rs.getInt("id"),rs.getInt("CandidateId"), rs.getInt("VotionId"), rs.getInt("UserId")));
        }
        return list;
    }
    
    public static List<Votion> convertResultSetToVotionList(ResultSet rs) throws SQLException{
        List<Votion> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new Votion(rs.getInt("id"),rs.getString("title")));
        }
        return list;
    }
    
    public static List<User> convertResultSetToUserList(ResultSet rs) throws SQLException{
        List<User> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new User(rs.getInt("id"),rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getString("access")));
        }
        return list;
    }
    
    public static List<History> convertResultSetToVotionWithCandidatesList(ResultSet rs) throws SQLException{  
        List<History> list = new ArrayList<>();
        if(!rs.first())return null;
        rs.previous();
        while(rs.next()){
            list.add(new History(rs.getInt("id"), rs.getInt("CandidateId"), rs.getInt("VotionId"), 0));
        }
        return list;
    }
}
