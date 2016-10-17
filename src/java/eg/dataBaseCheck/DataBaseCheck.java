package eg.dataBaseCheck;

import eg.connection.ConnectionToDataBase;
import eg.exceptions.TablesDoNotExist;

import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseCheck {

    public DataBaseCheck() throws SQLException, TablesDoNotExist {
        checkTables();
    }

    private void checkTables() throws SQLException {
        try {
            checkIfTableExist();
        } catch (TablesDoNotExist ex) {
            System.out.println("Creating tables!");
            createTables();
            createProcedures();
        } catch (SQLException ex) {
            throw new SQLException("Connection problem");
        }
    }

    private void checkIfTableExist() throws SQLException, TablesDoNotExist {
        ResultSet rs = ConnectionToDataBase.getConnection().insertText("SHOW TABLES;");
        if (!rs.first()) throw new TablesDoNotExist();
    }

    private void createTables() throws SQLException {
        createUsersTable();
        createCandidatesTable();
        createHistoryTable();
        createVotingTable();
        createVotingCandidatesTable();
        createDefaultAdmin();
    }

    private void createProcedures() throws SQLException{
        createDeleteVotingProcedure();
        createDeleteCandidateProcedure();
        createDeleteUserProcedure();
        createDeleteHistoryProcedure();
        createGetResultsProcedure();
        createGetCandidatesDedicatedToVotingProcedure();
    }

    private void createUsersTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE USERS\n" +
                "(\n" +
                "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "NAME varchar(40) NOT NULL,\n" +
                "LOGIN varchar(40) NOT NULL UNIQUE ,\n" +
                "PASSWORD varchar(40) NOT NULL,\n" +
                "ACCESS varchar(40) NOT NULL\n" +
                ")"
        );
    }

    private void createCandidatesTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE CANDIDATES\n" +
                "(\n" +
                "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "NAME varchar(40) NOT NULL UNIQUE,\n" +
                "ACCESS varchar(40) NOT NULL\n" +
                ")"
        );
    }

    private void createHistoryTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE HISTORY\n" +
                "(\n" +
                "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "CANDIDATE_ID Integer NOT NULL,\n" +
                "VOTING_ID Integer NOT NULL,\n" +
                "USER_ID Integer NOT NULL\n" +
                ")"
        );
    }

    private void createVotingTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE VOTING\n" +
                "(\n" +
                "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "TITLE varchar(40) NOT NULL UNIQUE\n" +
                ")"
        );
    }

    private void createVotingCandidatesTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE VOTING_CANDIDATES\n" +
                "(\n" +
                "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                "CANDIDATE_ID varchar(40) NOT NULL,\n" +
                "VOTING_ID varchar(40) NOT NULL\n" +
                ")"
        );
    }
    public void createDeleteVotingProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().go(
                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`DELETE_VOTING`");
        ConnectionToDataBase.getConnection().go(
                "CREATE PROCEDURE `VotingDataBase`.`DELETE_VOTING` (IN VOTING_ID INT)\n" +
                "BEGIN \n" +
                "SET SQL_SAFE_UPDATES = 0;\n" +
                "DELETE VOTING\n" +
                "FROM VOTING\n" +
                "WHERE VOTING.ID = VOTING_ID;\n" +
                "\n" +
                "DELETE VOTING_CANDIDATES\n" +
                "FROM VOTING_CANDIDATES\n" +
                "WHERE VOTING_CANDIDATES.VOTING_ID = VOTING_ID;\n" +
                "\n" +
                "DELETE HISTORY\n" +
                "FROM HISTORY\n" +
                "WHERE HISTORY.VOTING_ID = VOTING_ID;\n" +
                "\n" +
                "END"

        );
    }

    public void createDeleteUserProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().go(
                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`DELETE_USER`");
        ConnectionToDataBase.getConnection().go(
                "CREATE PROCEDURE `VotingDataBase`.`DELETE_USER` (IN USER_ID INT)\n" +
                "BEGIN \n" +
                "SET SQL_SAFE_UPDATES = 0;\n" +
                "DELETE USERS\n" +
                "FROM USERS\n" +
                "WHERE USERS.ID = USER_ID;\n" +
                "\n"+
                "DELETE HISTORY\n" +
                "FROM HISTORY\n" +
                "WHERE HISTORY.USER_ID = USER_ID;\n" +
                "\n" +
                "END"

        );
    }

    public  void createDeleteCandidateProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().go(
                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`DELETE_CANDIDATE`");
        ConnectionToDataBase.getConnection().go(
                "CREATE PROCEDURE `VotingDataBase`.`DELETE_CANDIDATE` (IN CANDIDATE_ID INT)\n" +
                "BEGIN\n" +
                "SET SQL_SAFE_UPDATES = 0;\n" +
                "DELETE CANDIDATES\n" +
                "    FROM CANDIDATES\n" +
                "    WHERE CANDIDATES.ID = CANDIDATE_ID;\n" +
                "\n" +
                "DELETE VOTING_CANDIDATES\n" +
                "    FROM VOTING_CANDIDATES\n" +
                "    WHERE VOTING_CANDIDATES.CANDIDATE_ID = CANDIDATE_ID;\n" +
                "\n" +
                "DELETE HISTORY\n" +
                "    FROM HISTORY\n" +
                "    WHERE HISTORY.CANDIDATE_ID = CANDIDATE_ID;" +
                "END"

        );
    }

    public void createDeleteHistoryProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().go(
                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`DELETE_HISTORY`");
        ConnectionToDataBase.getConnection().go(
                "CREATE PROCEDURE `VotingDataBase`.`DELETE_HISTORY` (IN HISTORY_ID INT)\n" +
                "BEGIN \n" +
                "SET SQL_SAFE_UPDATES = 0;\n" +
                "DELETE HISTORY\n" +
                "FROM HISTORY\n" +
                "WHERE HISTORY.ID = HISTORY_ID;\n" +
                "\n" +
                "END"

        );
    }

    public void createGetResultsProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().go(
                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`GET_RESULTS_WITH_VOTING_ID`");
        ConnectionToDataBase.getConnection().go(
                "CREATE PROCEDURE `VotingDataBase`.`GET_RESULTS_WITH_VOTING_ID` (IN GET_VOTING_ID INT)\n" +
                "BEGIN\n" +
                "    SELECT V.VOTING_ID, V.CANDIDATE_ID, \n" +
                "    (select count(USER_ID) \n" +
                "    from HISTORY H\n" +
                "    WHERE H.VOTING_ID=V.VOTING_ID AND H.CANDIDATE_ID=V.CANDIDATE_ID\n" +
                "    group by VOTING_ID, CANDIDATE_ID) AS VOTES\n" +
                "    FROM VOTING_CANDIDATES V\n" +
                "    WHERE V.VOTING_ID = GET_VOTING_ID;\n" +
                "END"
        );
    }

    public void createGetCandidatesDedicatedToVotingProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().go(
                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`GET_CANDIDATE_NAMES_DEDICATED_TO_VOTING`");
        ConnectionToDataBase.getConnection().go(
                "CREATE PROCEDURE `VotingDataBase`.`GET_CANDIDATE_NAMES_DEDICATED_TO_VOTING` (IN VOTING_TITLE VARCHAR(40))\n" +
                "BEGIN\n" +
                "    SELECT C.NAME\n" +
                "    FROM CANDIDATES C, VOTING_CANDIDATES V\n" +
                "    WHERE \n" +
                "        VOTING_ID=(\n" +
                "            SELECT VOTING.ID \n" +
                "            FROM VOTING \n" +
                "            WHERE VOTING.TITLE=VOTING_TITLE\n" +
                "        )\n" +
                "        AND V.CANDIDATE_ID=C.ID;\n" +
                "END"
        );
    }

    private void createDefaultAdmin() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "INSERT INTO USERS (NAME, LOGIN, PASSWORD, ACCESS) VALUES\n" +
                "('admin', 'admin', 'admin', 'ADMIN');");
    }

    public void testProcedure() throws SQLException {
//        ConnectionToDataBase.getConnection().go(
//                "DROP PROCEDURE IF EXISTS `VotingDataBase`.`DELETE_CANDIDATE`");
//        ConnectionToDataBase.getConnection().go(
//                "CREATE PROCEDURE `VotingDataBase`.`DELETE_CANDIDATE` \n" +
//                "   (IN CANDIDATE_ID INT)\n" +
//                "BEGIN\n" +
//                "SET SQL_SAFE_UPDATES = 0;\n"+
//                "DELETE CANDIDATES\n" +
//                "    FROM CANDIDATES\n" +
//                "    WHERE CANDIDATES.ID = CANDIDATE_ID;\n"+
//                "\n"+
//                "DELETE VOTING_CANDIDATES\n" +
//                "    FROM VOTING_CANDIDATES\n" +
//                "    WHERE VOTING_CANDIDATES.CANDIDATE_ID = CANDIDATE_ID;\n" +
//                "\n" +
//                "DELETE HISTORY\n" +
//                "    FROM HISTORY\n" +
//                "    WHERE HISTORY.CANDIDATE_ID = CANDIDATE_ID;"+
//                "END");
//                "CREATE PROCEDURE `VotingDataBase`.`GET_RESULTS_WITH_VOTING_ID` (IN GET_VOTING_ID INT)\n" +
//                "BEGIN\n" +
//                "    SELECT V.VOTING_ID, V.CANDIDATE_ID, \n" +
//                "    (select count(USER_ID) \n" +
//                "    from HISTORY H\n" +
//                "    WHERE H.VOTING_ID=V.VOTING_ID AND H.CANDIDATE_ID=V.CANDIDATE_ID\n" +
//                "    group by VOTING_ID, CANDIDATE_ID) AS VOTES\n" +
//                "    FROM VOTING_CANDIDATES V\n" +
//                "    WHERE V.VOTING_ID = GET_VOTING_ID;\n" +
//                "END");
    }
}
