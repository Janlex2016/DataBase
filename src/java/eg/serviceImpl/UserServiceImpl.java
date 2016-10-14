package eg.serviceImpl;

import eg.converters.ListToArray;
import eg.converters.ListToNameArray;
import eg.dao.HistoryDao;
import eg.dao.UserDao;
import eg.dao.VotingDao;
import eg.daoImpl.DaoFactory;
import eg.exceptions.AccessDenied;
import eg.exceptions.CandidateNotFound;
import eg.exceptions.HistoryNotFound;
import eg.exceptions.IncorrectInput;
import eg.exceptions.ListIsEmpty;
import eg.exceptions.UserNotFound;
import eg.exceptions.VotingNotFound;
import eg.main.Main;
import eg.menu.AdminMenu;
import eg.menu.UserMenu;
import eg.models.User;
import eg.models.History;
import eg.models.enums.Access;
import eg.service.UserService;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserDao userDao = DaoFactory.getUserDao();
    private final HistoryDao historyDao = DaoFactory.getHistoryDao();    
    private final VotingDao votingDao = DaoFactory.getVotingDao();

    @Override
    public void addUser(String name, String login, String password, String access) throws SQLException, IncorrectInput{

        User user = new User(0, name, login, password, access);
        if(name.length()<4) throw new IncorrectInput("Name is too short");
        if(access.equals(Access.CANDIDATE.name())){
            user.setLogin("");
            user.setPassword("");
        }else{
            if(login.length()<4||password.length()<4) throw new IncorrectInput();
        }
        userDao.add(user);
    }

    @Override
    public void deleteCandidate(int candidateId) throws SQLException{
        
        userDao.deleteCandidateById(candidateId);
    }

    @Override
    public List<User> getAll() throws SQLException, ListIsEmpty{
        List<User> list = userDao.getAll();
        if(list==null) throw new ListIsEmpty();
        return list;
    }
    
    @Override
    public List<User> getUsers() throws SQLException, ListIsEmpty {
        List<User> list = userDao.getUsersByAccess("USER");
        if(list==null) throw new ListIsEmpty();
        return list;
    }

    @Override
    public List<User> getCandidates() throws SQLException, ListIsEmpty {
        List<User> list = userDao.getUsersByAccess("CANDIDATE");
        if(list==null) throw new ListIsEmpty();
        return list;
    }

    @Override
    public List<User> getAdmins() throws SQLException, ListIsEmpty {
        List<User> list = userDao.getUsersByAccess("ADMIN");
        if(list==null) throw new ListIsEmpty();
        return list;
    }

    @Override
    public User getUserById(int userId) throws SQLException, UserNotFound, AccessDenied{
        User user = userDao.getById(userId);
        if(user==null) throw new UserNotFound();
        if(user.getAccess()!=Access.USER) throw new AccessDenied();
        return user;
    }

    @Override
    public User getCandidateById(int userId) throws SQLException,AccessDenied,CandidateNotFound {
        User user = userDao.getById(userId);
        if(user==null) throw new CandidateNotFound();
        if(user.getAccess()!=Access.CANDIDATE) throw new AccessDenied();
        return user;
    }
    
    @Override
    public User getCandidateByName(String name) throws SQLException,AccessDenied,CandidateNotFound {
        User user = userDao.getByName(name);
        if(user==null) throw new CandidateNotFound();
        if(user.getAccess()!=Access.CANDIDATE) throw new AccessDenied();
        return user;
    }

    @Override
    public User getAdminById(int userId) throws SQLException,UserNotFound,AccessDenied{
        User user = userDao.getById(userId);
        if(user==null) throw new UserNotFound();
        if(user.getAccess()!=Access.ADMIN) throw new AccessDenied();
        return user;
    }
    
    @Override
    public User getById(int id) throws SQLException,UserNotFound{
        User user = userDao.getById(id);
        if(user==null) throw new UserNotFound();
        return user;
    }

    @Override
    public void deleteById(int userId) throws SQLException,UserNotFound{
        User user = userDao.getById(userId);
        if(user==null) throw new UserNotFound();
        userDao.deleteById(userId);
    }
    
    @Override
    public void deleteByName(String name) throws SQLException,UserNotFound{
        userDao.deleteByName(name);
    }

    @Override
    public User enter(String login, String password) throws SQLException, UserNotFound, ListIsEmpty {
        if (login.length() < 4) throw new UserNotFound("Login is too short, try again!");
        if (password.length() < 4) throw new UserNotFound("Password is too short, try again!");
        User user = userDao.enter(login, password);
        if(user==null)throw new UserNotFound("Wrong name or password!");
        if (user.getAccess().equals(Access.USER)) Main.setUserMenu(new UserMenu(user));
        if (user.getAccess().equals(Access.ADMIN)) Main.setAdminMenu(new AdminMenu(user));
        return user;
    }

    @Override
    public void vote(int candidateId, int votionId, int userId) throws SQLException,AccessDenied,CandidateNotFound,HistoryNotFound,UserNotFound,VotingNotFound {
        
        if(getCandidateById(candidateId)==null) throw new CandidateNotFound();
        if(getUserById(userId)==null) throw new UserNotFound();
        if(votingDao.getById(votionId)==null) throw new VotingNotFound();
        if(historyDao.isThereVotionAndUserId(votionId, userId)) throw new AccessDenied("You cant vote in this voting again!");
        historyDao.add(new History(0, candidateId, votionId, userId));
    }

    @Override
    public String[] getCandidateNameArray(List<User> list) throws SQLException {
        String[] nameArray = ListToNameArray.getUserNameArray(list);
        return nameArray;
    }

    @Override
    public String[] getUserArray() throws SQLException, ListIsEmpty {
        return ListToArray.getUserArray(getAll());
    }
}
