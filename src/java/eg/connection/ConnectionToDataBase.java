package eg.connection;

import eg.util.database.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public final class ConnectionToDataBase {
    
    private Connection con;
    private Statement statement;
    private static ConnectionToDataBase db;
    private DataBaseConfig dataBaseConfig;

    private ConnectionToDataBase() throws SQLException {

        dataBaseConfig = new DataBaseConfig();
        con = DriverManager.getConnection(
                dataBaseConfig.getConfigData().getURL(),
                dataBaseConfig.getConfigData().getLogin(),
                dataBaseConfig.getConfigData().getPassword()
        );
        statement = con.createStatement();

    }

    public static ConnectionToDataBase getConnection() throws SQLException {
        if ( db == null ) {
            db = new ConnectionToDataBase();
        }
        return db;
    }

    public ResultSet query(String query) throws SQLException{

        ResultSet res = statement.executeQuery("select * from "+query);
        return res;
    }
  
    public int insert(String insertQuery) throws SQLException {
        int result = statement.executeUpdate(insertQuery);
        return result;
    }

    public ResultSet insertText(String text) throws SQLException {
        ResultSet rs = statement.executeQuery(text);
        return rs;
    }
}
