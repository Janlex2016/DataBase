package eg.daoImpl;

import eg.dao.VotingDao;
import eg.models.Voting;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VotingDaoImplTest {

    VotingDao votingDao = new VotingDaoImpl();

    @Test
    public void testVotingAddAndDelete() throws Exception {
        Voting voting = new Voting(0, "VotingTitle");
        votingDao.add(voting);
        Voting testVoting1 = votingDao.getByName(voting.getTitle());
        Voting testVoting2 = votingDao.getById(testVoting1.getId());
        Assert.assertNotNull(testVoting1);
        Assert.assertNotNull(testVoting2);
        votingDao.deleteById(testVoting1.getId());
        testVoting1 = votingDao.getByName(voting.getTitle());
        Assert.assertNull(testVoting1);
    }
}