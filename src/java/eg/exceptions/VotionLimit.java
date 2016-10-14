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
public class VotionLimit extends Exception{

    public VotionLimit(String msg) {
        super(msg);
    } 

    public VotionLimit() {
        super("You can't vote again");
    } 
}
