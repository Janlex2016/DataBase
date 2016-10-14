/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.serviceImpl;

import eg.service.HistoryService;
import eg.service.UserService;
import eg.service.VotingService;


/**
 *
 * @author noname
 */
public class ServiceFactory{
    
    private static HistoryService historyService;
    private static UserService userService;
    private static VotingService votingService;
    
    public static  HistoryService getHistoryService() {
        if(historyService==null) {
            historyService = new HistoryServiceImpl();
        }
        return historyService;
    }
    
    public static UserService getUserService() {
        if(userService==null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
    
    public static VotingService getVotingService() {
        if(votingService ==null) {
            votingService = new VotingServiceImpl();
        }
        return votingService;
    }
}

