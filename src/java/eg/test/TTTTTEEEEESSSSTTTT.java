/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.test;

public class TTTTTEEEEESSSSTTTT {
 
   /* public static void main(String[] args) throws SQLException, UserNotFound{
        
        User currentUser;

        UserService userService = ServiceFactory.getUserService();
        HistoryService historyService = ServiceFactory.getHistoryService();
        VotionService votionService = ServiceFactory.getVotingService();
        

            //*************************************************************
//        try{
//            userService.addAdmin("VANYANNNN", "vanya", "vanya");
//            userService.addCandidate("Yes, we can!");
//            userService.addUser("User", "login", "password");
//            userService.addCandidate("Zdraste menya zovut iosif Kabzoon");
//            userService.addUser("Polzovatel", "plog", "ppass");
//            votionService.add("lol");
//            votionService.add("Za Kabzona!");
//            votionService.add("Issue");            
//            votionService.addCandidateToVotion(4, 14);
//            votionService.addCandidateToVotion(2, 14);
//        }catch(IncorrectInput e){
//            
//        }catch(AccessDenied|CandidateNotFound|UserNotFound|VotionNotFound e){
//            System.out.println(e.getMessage());
//        }
            
//        try {
////            userService.deleteById(16);
//            System.out.println(historyService.countVoices(14).get(0).getCandidateId());
//        }catch(SQLException e){
//            System.out.println(e);
//        }
        
        try{
            currentUser = userService.enter("login", "password");
            userService.vote(4, 14, currentUser.getId());
            System.out.println("Ваш голос учтен!");
            
            currentUser = userService.enter("plog", "ppass");
            userService.vote(4, 14, currentUser.getId());
            userService.vote(4, 14, currentUser.getId());

        }catch(AccessDenied|CandidateNotFound|UserNotFound|VotionNotFound|HistoryNotFound e){
            System.out.println(e.getMessage());
        }
//     *******************************************************   
        try{
            System.out.println("Za Kabzona! - Kandidaty"); 
            List<User> clist = votionService.getById(15).getCandidates();

            for(User user:clist){
                System.out.print(user.getId()+" : ");
                System.out.print(user.getName()+" : ");
                System.out.println(user.getAccess()+" : ");      
            }
        }catch(SQLException|UserNotFound|VotionNotFound|NullPointerException e){
            System.out.println(e.getMessage());
        }
    
            System.out.println();
            
        try{
            List<User> list = userService.getAll();

            for(User u:list){

                System.out.print(u.getId()+" : ");
                System.out.print(u.getName()+" : ");
                System.out.println(u.getAccess());
//                us.deleteById(u.getId());
            }
        }catch(ListIsEmpty e){
            System.out.println(e.getMessage());
        }

            System.out.println();
        
        try{
            List<History> vwclist = historyService.countVoices(14);
            for(History vwc:vwclist){
                System.out.print("can : "+userService.getById(vwc.getCandidateId()).getName());
                System.out.println(", "+vwc.getVotionId()+" голосов");
            }
        }catch(UserNotFound e){
            System.out.println(e.getMessage());
        }

        System.out.println();
    
        try{
            List<History> l = historyService.getAll();
            for(History h:l){

                System.out.print("historyId "+h.getHistoryId()+" : ");
                System.out.print("cId "+h.getCandidateId()+" : ");
                System.out.print("vId "+h.getVotionId()+" : ");
                System.out.println("uId "+h.getUserId());
//                hs.deleteById(h.getHistoryId());
            } 
        }catch(SQLException|HistoryNotFound|ListIsEmpty e){
            System.out.println(e.getMessage());
        }

        System.out.println();
        
            List<Votion> vlist = votionService.getAll();
            for(Votion p:vlist){
                System.out.print(p.getId()+" : ");
                System.out.println(p.getTitle());
//                vs.deleteById(p.getId());
            }

    }*/
}
