package eg.serviceImpl;

import eg.converters.ListToArray;
import eg.converters.ListToNameArray;
import eg.dao.UserDao;
import eg.dao.VotingDao;
import eg.exceptions.*;
import eg.models.Voting;
import eg.service.VotingService;
import eg.daoImpl.DaoFactory;
import eg.models.History;
import eg.models.User;
import eg.models.enums.Access;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotingServiceImpl implements VotingService {
    
    private final VotingDao votingDao = DaoFactory.getVotingDao();
    private final UserDao userDao = DaoFactory.getUserDao();
    
    @Override
    public void addCandidateToVoting(int candidateId, int votingId) throws SQLException,AccessDenied,UserNotFound,VotingNotFound, CandidateNotFound{
        
        if(userDao.getCandidateById(candidateId)==null) throw new UserNotFound();
        if(userDao.getCandidateById(candidateId).getAccess()!=Access.CANDIDATE) throw new AccessDenied();
        if(votingDao.getById(votingId)==null) throw new VotingNotFound();
        //if
        votingDao.addCandidateToVoting(new History(0, candidateId, votingId, 0));
    }

    @Override
    public void add(String title) throws SQLException, IncorrectInput {
        
        if(title.length()<4) throw new IncorrectInput();
        votingDao.add(new Voting(0, title));
    }

    @Override
    public void deleteById(int id) throws SQLException, VotingNotFound {

        if(votingDao.getById(id)==null) throw new VotingNotFound();
        votingDao.deleteById(id);
    }

    @Override
    public List<Voting> getAll() throws SQLException, ListIsEmpty {
        List<Voting> list = votingDao.getAll();
        if(list==null) throw new ListIsEmpty();
        return list;
    }

    @Override
    public Voting getById(int id) throws SQLException, VotingNotFound, UserNotFound{

        Voting voting = votingDao.getById(id);
        if(voting ==null) throw new VotingNotFound();
        addCandidateList(voting, getVotingCandidateList(id));//Добавление листа не для всех
        return voting;
    }

    @Override
    public Voting getByName(String name) throws SQLException, VotingNotFound, UserNotFound{

        Voting voting = votingDao.getByName(name);
        if(voting ==null) throw new VotingNotFound();
        addCandidateList(voting, getVotingCandidateList(votingDao.getByName(name).getId()));//Добавление листа не для всех
        return voting;
    }

//    @Override
//    public Voting getCandidateByName(String name) throws SQLException, VotingNotFound, UserNotFound{
//
//        Voting voting = votingDao.getByName(name);
//        if(voting ==null) throw new VotingNotFound();
//        addCandidateList(voting, getVotingCandidateList(votingDao.getByName(name).getId()));//Добавление листа не для всех
//        return voting;
//    }
    
    @Override
    public void addCandidateList(Voting voting, List<User> list) throws SQLException, VotingNotFound {
        if(votingDao.getById(voting.getId())==null) throw new VotingNotFound();
        votingDao.addCandidateList(voting, list);
    }
    
    @Override
    public List<User> getVotingCandidateList(int votingId) throws SQLException{
        
        List<History> candidateList = votingDao.getVotingWithCandidatesList(votingId);
        if(candidateList==null) return null;
        List<User> list = new ArrayList<>();
        
        for(History v: candidateList){
            list.add(userDao.getCandidateById(v.getCandidateId()));
        }
        
        return list;
    }
    
    @Override
    public List<User> getPossibleCandidateList(int votingId) throws SQLException{
        
        List<User> candidateList = userDao.getAllCandidates();
        
        try{
            List<User> votionCandidateList = getVotingCandidateList(votingId);
            

            for(int s=0; s<votionCandidateList.size(); s++){
                for(int i=0; i<candidateList.size(); i++){

                    if(candidateList.get(i).getId()==votionCandidateList.get(s).getId()){
                        candidateList.remove(i);
                    }
                }
            }

            return candidateList;
        }catch(NullPointerException e){
            return candidateList;
        }
    }

    @Override
    public String[] getResultArray(int votingId) throws SQLException, CandidateNotFound, AccessDenied, UserNotFound, VotingNotFound {
        List<History> resultList = votingDao.getResults(votingId);
        return ListToArray.getResultArray(resultList);
    }

    @Override
    public String[] getCandidatesDedicatedToVoting(String votingTitle) throws SQLException {
        List<String> candidateNames = votingDao.getCandidatesDedicatedToVoting(votingTitle);
        return ListToNameArray.getCandidatesDedicatedToVotingArray(candidateNames);
    }

    @Override
    public String[] getVotingTitleArray() throws SQLException, ListIsEmpty {
        return ListToNameArray.getVotionTitleArray(getAll());
    }
    
    @Override
    public String[] getVotingArray() throws SQLException, ListIsEmpty {
        return ListToArray.getVotingArray(getAll());
    }

    @Override
    public String[] getVotingTitleArrayWithNull() throws SQLException, ListIsEmpty {
        return ListToNameArray.getVotingTitleArrayWithNull(getAll());
    }

    
//    @Override
//    public void deleteByCandidateId(int id) throws SQLException {
//        votingDao.deleteCandidateById(id);
//    }

    @Override
    public void deleteCandidateFromVoting(int candidateId, int votingId) throws SQLException, AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound {
       
        if(userDao.getCandidateById(candidateId)==null) throw new UserNotFound();
        if(userDao.getCandidateById(candidateId).getAccess()!=Access.CANDIDATE) throw new AccessDenied();
        if(votingDao.getById(votingId)==null) throw new VotingNotFound();
        //if
        votingDao.deleteCandidateFromVoting(candidateId, votingId);
    }
}
