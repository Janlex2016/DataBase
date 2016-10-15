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
        } catch (SQLException ex) {
            throw new SQLException("Connection problem");
        }
    }

    private void createTables() throws SQLException {
        createUsersTable();
        createCandidatesTable();
        createHistoryTable();
        createVotingTable();
        createVotingCandidatesTable();
        createDefaultAdmin();
        createDeleteVotingProcedure();
        createDeleteCandidateProcedure();
    }

    private void createUsersTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE USERS\n" +
                        "(\n" +
                        "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                        "NAME varchar(40) NOT NULL,\n" +
                        "LOGIN varchar(40) UNIQUE DEFAULT NULL,\n" +
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
    private void createDeleteVotingProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().insertText(
                "DELIMITER // \n" +
                "\n" +
                "CREATE PROCEDURE `DELETE_VOTING` (IN VOTING_ID INT) \n" +
                "LANGUAGE SQL \n" +
                "COMMENT 'Voting delete' \n" +
                "BEGIN \n" +
                "        \n" +
                "SET SQL_SAFE_UPDATES = 0;\n" +
                "\n" +
                "DELETE VOTING\n" +
                "    FROM VOTING\n" +
                "    WHERE VOTING.ID = VOTING_ID;\n" +
                "\n" +
                "DELETE VOTING_CANDIDATES\n" +
                "    FROM VOTING_CANDIDATES\n" +
                "    WHERE VOTING_CANDIDATES.VOTING_ID = VOTING_ID;\n" +
                "\n" +
                "DELETE HISTORY\n" +
                "    FROM HISTORY\n" +
                "    WHERE HISTORY.VOTING_ID = VOTING_ID;\n" +
                "\n" +
                "END// "
        );
    }

    private void createDeleteCandidateProcedure() throws SQLException {
        ConnectionToDataBase.getConnection().insertText(
                "DELIMITER // \n" +
                "\n" +
                "CREATE PROCEDURE `DELETE_CANDIDATE` (IN CANDIDATE_ID INT) \n" +
                "LANGUAGE SQL \n" +
                "COMMENT 'Candidate delete' \n" +
                "BEGIN \n" +
                "\n" +
                "SET SQL_SAFE_UPDATES = 0;\n" +
                "\n" +
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
                "    WHERE HISTORY.CANDIDATE_ID = CANDIDATE_ID;\n" +
                "\n" +
                "END// "
        );
    }

    private void createDefaultAdmin() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "INSERT INTO USERS (NAME, LOGIN, PASSWORD, ACCESS) VALUES\n"+
                "('admin', 'admin', 'admin', 'ADMIN');");
    }

    private void checkIfTableExist() throws SQLException, TablesDoNotExist {
        ResultSet rs = ConnectionToDataBase.getConnection().insertText("SHOW TABLES;");
        if (!rs.first()) throw new TablesDoNotExist();
    }

    String procedure1 =
            "DELIMITER // \n" +
            "\n" +
            "CREATE PROCEDURE `COUNT_VOTES_WITH_CANDIDATE_ID_AND_VOTING_ID` (IN USER_ID INT, IN VOTING_ID INT) \n" +
            "LANGUAGE SQL \n" +
            "COMMENT 'Getting results' \n" +
            "BEGIN \n" +
            "    SELECT COUNT(*) FROM HISTORY H WHERE H.CANDIDATE_ID=USER_ID AND H.VOTING_ID=VOTING_ID; \n" +
            "END// ";
    String pr =
            "DELIMITER // \n" +
            "\n" +
            "CREATE PROCEDURE `COUNT_VOTES_WITH_CANDIDATE_ID_AND_VOTING_ID` (IN USER_ID INT, IN VOTING_ID INT) \n" +
            "LANGUAGE SQL \n" +
            "COMMENT 'Getting results' \n" +
            "BEGIN \n" +
            "        \n" +
            "SELECT\n" +
            "    USERS.NAME AS 'Candidate name',  \n" +
            "    (SELECT COUNT(*)\n" +
            "    FROM HISTORY H \n" +
            "    WHERE H.CANDIDATE_ID=USER_ID \n" +
            "    AND H.VOTING_ID=VOTING_ID ) AS 'Votes'\n" +
            "FROM USERS\n" +
            "WHERE \n" +
            "    USERS.ID=USER_ID\n" +
            "    AND USERS.ACCESS='CANDIDATE';\n" +
            "END// ";
}
