package eg.serviceImpl;

import eg.converters.ListToArray;
import eg.dao.*;
import eg.daoImpl.DaoFactory;
import eg.exceptions.*;
import eg.models.History;
import eg.models.User;
import eg.models.enums.Access;
import eg.service.HistoryService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryServiceImpl implements HistoryService{
    
    private final HistoryDao historyDao = DaoFactory.getHistoryDao();
    private final UserDao userDao = DaoFactory.getUserDao();
    private final VotingDao votingDao = DaoFactory.getVotingDao();
    
    @Override
    public void add(int candidateId, int votionId, int userId) throws SQLException, AccessDenied,CandidateNotFound,UserNotFound,VotingNotFound, IncorrectInput{
        if(candidateId<1||votionId<1||userId<1) throw new IncorrectInput();
        if(userDao.getById(candidateId)==null) throw new CandidateNotFound();
        if(userDao.getById(userId)==null) throw new UserNotFound();
        if(userDao.getById(candidateId).getAccess()!=Access.CANDIDATE) throw new AccessDenied("User "+userDao.getById(candidateId)+" isn't candidate");
        if(userDao.getById(userId).getAccess()!=Access.USER) throw new AccessDenied((userDao.getById(userId).getAccess()!=Access.USER)+" : "+userDao.getById(userId).getAccess()+" : "+userDao.getById(userId).getName());
        if(votingDao.getById(votionId)==null) throw new VotingNotFound();
        historyDao.add(new History(0, candidateId, votionId, userId));
    }

    @Override
    public List<History> getAll() throws SQLException, ListIsEmpty{
        List<History> list = historyDao.getAll();
        
        if(list==null) throw new ListIsEmpty();
        return list;
    }

    @Override
    public History getById(int historyId) throws SQLException, HistoryNotFound{
        History history = historyDao.getById(historyId);
        if(history==null) throw new HistoryNotFound();
        return history;
    }

    @Override
    public void deleteById(int historyId) throws SQLException, HistoryNotFound{
        
        historyDao.deleteById(historyId);
    }

    @Override
    public boolean isThereVotionAndUserId(int votionId, int userId) throws SQLException {
        return historyDao.isThereVotionAndUserId(votionId, userId);
    }

    @Override
    public List<History> countVoices(int votionId) throws SQLException {
        
        List<History> ulist = votingDao.getVotionWithCandidatesList(votionId);
        List<History> vlist = new ArrayList<>();
        
        for(History u:ulist){
            vlist.add(new History(u.getHistoryId(), u.getCandidateId(), historyDao.numberOfVoicesInVotion(u.getCandidateId(), votionId), 0));
        }
        return vlist;
    }
    
    @Override
    public String[] getHistoryArray() throws SQLException, ListIsEmpty {
        return ListToArray.getHistoryArray(rewrited(getAll()));
    }

    @Override
    public List<User> rewrited(List<History> hlist) throws SQLException, ListIsEmpty {
        
        List<User> ulist = new ArrayList<>();
        for(History h:hlist){
            ulist.add(new User(h.getHistoryId(), userDao.getById(h.getCandidateId()).getName(), votingDao.getById(h.getVotionId()).getTitle(), userDao.getById(h.getUserId()).getName(), "USER"));
        }
        
        return ulist;
    }
}