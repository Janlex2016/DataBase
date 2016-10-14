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
public class CandidateNotFound extends Exception{

    public CandidateNotFound(String msg) {
        super(msg);
    } 

    public CandidateNotFound() {
        super("Candidate not found");
    } 
}    

