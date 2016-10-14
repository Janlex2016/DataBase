package eg.serviceImpl;

import eg.converters.ListToArray;
import eg.converters.ListToNameArray;
import eg.dao.UserDao;
import eg.dao.VotingDao;
import eg.models.Votion;
import eg.service.VotingService;
import eg.daoImpl.DaoFactory;
import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.IncorrectInput;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
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
    public void addCandidateToVotion(int candidateId, int votionId) throws SQLException,AccessDenied,UserNotFound,VotingNotFound, CandidateNotFound{
        
        if(userDao.getById(candidateId)==null) throw new UserNotFound();
        if(userDao.getById(candidateId).getAccess()!=Access.CANDIDATE) throw new AccessDenied();
        if(votingDao.getById(votionId)==null) throw new VotingNotFound();
        //if
        votingDao.addCandidateToVotion(new History(0, candidateId, votionId, 0));
    }

    @Override
    public void add(String title) throws SQLException, IncorrectInput {
        
        if(title.length()<4) throw new IncorrectInput();
        votingDao.add(new Votion(0, title));
    }

    @Override
    public void deleteById(int id) throws SQLException, VotingNotFound {

        if(votingDao.getById(id)==null) throw new VotingNotFound();
        votingDao.deleteById(id);
    }

    @Override
    public List<Votion> getAll() throws SQLException{
        List<Votion> list = votingDao.getAll();
//        if(list==null) throw new ListIsEmpty();
        return list;
    }

    @Override
    public Votion getById(int id) throws SQLException, VotingNotFound, UserNotFound{

        Votion votion = votingDao.getById(id);
        if(votion==null) throw new VotingNotFound();
        addCandidateList(votion, getVotingCandidateList(id));//Добавление листа не для всех
        return votion;
    }

    @Override
    public Votion getByName(String name) throws SQLException, VotingNotFound, UserNotFound{

        Votion votion = votingDao.getByName(name);
        if(votion==null) throw new VotingNotFound();
        addCandidateList(votion, getVotingCandidateList(votingDao.getByName(name).getId()));//Добавление листа не для всех
        return votion;
    }
    
    @Override
    public void addCandidateList(Votion votion, List<User> list) throws SQLException, VotingNotFound {
        if(votingDao.getById(votion.getId())==null) throw new VotingNotFound();
        votingDao.addCandidateList(votion, list);
    }
    
    @Override
    public List<User> getVotingCandidateList(int votingId) throws SQLException{
        
        List<History> candidateList = votingDao.getVotionWithCandidatesList(votingId);
        if(candidateList==null) return null;
        List<User> list = new ArrayList<>();
        
        for(History v: candidateList){
            list.add(userDao.getById(v.getCandidateId()));
        }
        
        return list;
    }
    
    @Override
    public List<User> getPossibleCandidateList(int votionId) throws SQLException{
        
        List<User> candidateList = userDao.getUsersByAccess("CANDIDATE");
        
        try{
            List<User> votionCandidateList = getVotingCandidateList(votionId);
            

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
    public String[] getVotionTitleArray() throws SQLException {
        return ListToNameArray.getVotionTitleArray(getAll());
    }
    
    @Override
    public String[] getVotionArray() throws SQLException {
        return ListToArray.getVotionArray(getAll());
    }

    @Override
    public String[] getVotionTitleArrayWithNull() throws SQLException {
        return ListToNameArray.getVotionTitleArrayWithNull(getAll());
    }

    
    @Override
    public void deleteByCandidateId(int id) throws SQLException {
        votingDao.deleteCandidateById(id);
    }

    @Override
    public void deleteCandidateFromVotion(int candidateId, int votionId) throws SQLException, AccessDenied, CandidateNotFound, VotingNotFound, UserNotFound {
       
        if(userDao.getById(candidateId)==null) throw new UserNotFound();
        if(userDao.getById(candidateId).getAccess()!=Access.CANDIDATE) throw new AccessDenied();
        if(votingDao.getById(votionId)==null) throw new VotingNotFound();
        //if
        votingDao.deleteCandidateFromVotion(candidateId, votionId);
    }
}
