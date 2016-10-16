/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.converters;

import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.models.History;
import eg.models.User;
import eg.models.Voting;
import eg.service.UserService;
import eg.service.VotingService;
import eg.serviceImpl.UserServiceImpl;
import eg.serviceImpl.VotingServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class ListToArray {
    
    private static String nameArray[];

    public static  String[] getUserArray(List<User> list){

        nameArray = new String[list.size()];

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = String.format(
                    "%1$d : %2$s : %3$s : %4$s : %5$s",
                    list.get(i).getId(),
                    list.get(i).getName(),
                    list.get(i).getLogin(),
                    list.get(i).getPassword(),
                    list.get(i).getAccess().toString()
            );
        }

        return nameArray;
    }
    
    public static  String[] getVotingArray(List<Voting> list){

        nameArray = new String[list.size()];

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = String.format(
                    "%1$d : %2$s",
                    list.get(i).getId(),
                    list.get(i).getTitle()
            );
        }
        return nameArray;
    }
    
    public static  String[] getHistoryArray(List<User> list){

        nameArray = new String[list.size()];

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = String.format(
                    "%1$d : %2$s : %3$s : %4$s",
                    list.get(i).getId(),
                    list.get(i).getName(),
                    list.get(i).getLogin(),
                    list.get(i).getPassword()
            );
        }
        if(nameArray[0].equals("")) {
            System.out.println("Nul in history");
            return null;
        }else return nameArray;
    }

    public static  String[] getResultArray(List<History> list) throws SQLException, AccessDenied, CandidateNotFound, UserNotFound, VotingNotFound {
        nameArray = new String[list.size()];
        UserService userService = new UserServiceImpl();

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = String.format(
                    "Candidate : %1$s has %2$d votes",
                    userService.getCandidateById(list.get(i).getCandidateId()).getName(),
                    list.get(i).getUserId()
            );
        }
        return nameArray;
    }
}
