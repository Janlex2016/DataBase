package eg.converters;

import eg.dataBaseCheck.DataBaseCheck;

public class AnotherTest {

    @org.junit.Test
    public void test() throws Exception {

        DataBaseCheck dataBaseCheck = new DataBaseCheck();
//        dataBaseCheck.createDeleteCandidateProcedure();
//        dataBaseCheck.createDeleteVotingProcedure();
        dataBaseCheck.testProcedure();
    }
}