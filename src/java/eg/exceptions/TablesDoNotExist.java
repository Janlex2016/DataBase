/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.exceptions;

public class TablesDoNotExist extends Exception{

    public TablesDoNotExist(String msg) {
        super(msg);
    }

    public TablesDoNotExist() {
        super("Table do not exist");
    } 
}    

