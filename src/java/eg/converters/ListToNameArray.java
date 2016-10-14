package eg.converters;

import eg.models.User;
import eg.models.Votion;
import java.util.List;

public class ListToNameArray {
    
    private static String nameArray[];

    public static  String[] getUserNameArray(List<User> list){

        nameArray = new String[list.size()];

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = list.get(i).getName();
        }
        return nameArray;
    }

    public static  String[] getVotionTitleArray(List<Votion> list){

        nameArray = new String[list.size()];

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = list.get(i).getTitle();
        }
        return nameArray;
    }
    
    public static  String[] getVotionTitleArrayWithNull(List<Votion> list){

        nameArray = new String[list.size()+1];
        nameArray[0] = "*All*";
        for (int i = 1; i < nameArray.length; i++) {
            nameArray[i] = list.get(i-1).getTitle();
        }
        return nameArray;
    }
}