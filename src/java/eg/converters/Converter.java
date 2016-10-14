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

/**
 *
 * @author noname
 */
public class Converter {
    
    public static History convertResultSetToHistory(ResultSet rs) throws SQLException{
        if(!rs.next()) return null;
        return new History(rs.getInt("id"),rs.getInt("CandidateId"), rs.getInt("VotionId"), rs.getInt("UserId"));
    }
    
    public static User convertResultSetToUser(ResultSet rs) throws SQLException{
        if(!rs.next()) return null;
        return new User(rs.getInt("id"),rs.getString("name"), rs.getString("login"), rs.getString("password"), rs.getString("access"));
    }
    
    public static Votion convertResultSetToVotion(ResultSet rs) throws SQLException{
        if(!rs.next()) return null;
        return new Votion(rs.getInt("id"),rs.getString("title"));
    }

 }

