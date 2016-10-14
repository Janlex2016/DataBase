/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.exceptions;

/**
 *
 * @author noname
 */
public class HistoryNotFound extends Exception{

    public HistoryNotFound(String msg) {
        super(msg);
    } 

    public HistoryNotFound() {
        super("History not found");
    } 
}     

