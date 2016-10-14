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
public class IncorrectInput extends Exception{

    public IncorrectInput(String msg) {
        super(msg);
    } 

    public IncorrectInput() {
        super("Incorrect input");
    }     
}
