package eg.daoImpl;

import eg.dao.HistoryDao;
import eg.models.History;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HistoryDaoImplTest {
    HistoryDao historyDao = new HistoryDaoImpl();

    @Test
    public void testHistoryAddAndDelete() throws Exception {
        History testHistory = new History(0, 98, 55, 878);
        historyDao.add(testHistory);
        List<History> testHistoryList = historyDao.getAll();
        Assert.assertNotNull(testHistoryList);
        for (History history : testHistoryList) {
            if (
                    history.getUserId() == testHistory.getUserId()
                            && history.getCandidateId() == testHistory.getCandidateId()
                            && history.getVotingId() == testHistory.getVotingId()) {
                historyDao.deleteById(history.getHistoryId());
            }
        }
    }
}