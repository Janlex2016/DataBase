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
        createHistoryTable();
        createVotingTable();
        createVotingCandidatesTable();
        createDefaultAdmin();
    }

    private void createUsersTable() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "CREATE TABLE USERS\n" +
                        "(\n" +
                        "ID Integer PRIMARY KEY AUTO_INCREMENT,\n" +
                        "NAME varchar(40) NOT NULL,\n" +
                        "LOGIN varchar(40) NOT NULL UNIQUE,\n" +
                        "PASSWORD varchar(40) NOT NULL,\n" +
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

    private void createDefaultAdmin() throws SQLException {
        ConnectionToDataBase.getConnection().insert(
                "INSERT INTO USERS (NAME, LOGIN, PASSWORD, ACCESS) VALUES\n"+
                "('admin', 'admin', 'admin', 'ADMIN');");
    }

    private void checkIfTableExist() throws SQLException, TablesDoNotExist {
        ResultSet rs = ConnectionToDataBase.getConnection().insertText("SHOW TABLES;");
        if (!rs.first()) throw new TablesDoNotExist();
    }
}
