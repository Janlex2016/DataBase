package eg.daoImpl;

import eg.dao.HistoryDao;
import eg.dao.UserDao;
import eg.dao.VotingDao;

public class DaoFactory {
    
    private static HistoryDao historyDao;
    private static UserDao userDao;
    private static VotingDao votingDao;
    
    public static HistoryDao getHistoryDao() {
        if(historyDao==null) {
            historyDao = new HistoryDaoImpl();
        }
        return historyDao;
    }
    
    public static UserDao getUserDao() {
        if(userDao==null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
    
    public static VotingDao getVotingDao() {
        if(votingDao ==null) {
            votingDao = new VotingDaoImpl();
        }
        return votingDao;
    }
}
