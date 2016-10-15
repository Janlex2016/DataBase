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
        return new History(rs.getInt("ID"),rs.getInt("CANDIDATE_ID"), rs.getInt("VOTING_ID"), rs.getInt("USER_ID"));
    }
    
    public static User convertResultSetToUser(ResultSet rs) throws SQLException{
        if(!rs.next()) return null;
        return new User(rs.getInt("ID"),rs.getString("NAME"), rs.getString("LOGIN"), rs.getString("PASSWORD"), rs.getString("ACCESS"));
    }
    
    public static Votion convertResultSetToVoting(ResultSet rs) throws SQLException{
        if(!rs.next()) return null;
        return new Votion(rs.getInt("ID"),rs.getString("TITLE"));
    }

 }

