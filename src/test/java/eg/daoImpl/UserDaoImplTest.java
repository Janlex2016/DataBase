package eg.daoImpl;

import eg.dao.UserDao;
import eg.models.User;
import eg.models.enums.Access;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void testUserAddAndDelete() throws Exception {
        User user = new User(0, "UserName", "UserLogin", "UserPassword", Access.USER.name());
        userDao.add(user);
        User testUser1;
        User testUser2;
        testUser1 = userDao.getByName(user.getName());
        testUser2 = userDao.getById(testUser1.getId());
        Assert.assertNotNull(testUser1);
        Assert.assertNotNull(testUser2);
        userDao.deleteById(testUser1.getId());
        testUser1 = userDao.getByName(user.getName());
        Assert.assertNull(testUser1);
    }

    @Test
    public void testCandidateAddAndDelete() throws Exception {
        User candidate = new User(0, "CandidateName", "", "", Access.CANDIDATE.name());
        userDao.addCandidate(candidate);
        User testUser1;
        User testUser2;
        testUser1 = userDao.getCandidateByName(candidate.getName());
        testUser2 = userDao.getCandidateById(testUser1.getId());
        Assert.assertNotNull(testUser1);
        Assert.assertNotNull(testUser2);
        userDao.deleteCandidateById(testUser1.getId());
        testUser1 = userDao.getCandidateByName(candidate.getName());
        Assert.assertNull(testUser1);
    }
}