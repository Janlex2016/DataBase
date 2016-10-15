/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.converters;

import eg.models.User;
import eg.models.Votion;
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
    
    public static  String[] getVotingArray(List<Votion> list){

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
        System.out.println("lk;lk;"+nameArray.toString());
        if(nameArray[0].equals("")) {
            System.out.println("Nul in history");
            return null;
        }else return nameArray;
    }
}
