/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.util.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Date {
    public static void main(String[] args) {
        
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String todaysDate = dateFormat.format(System.currentTimeMillis());
    
    }
}
