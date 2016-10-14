package eg.converters;

import eg.connection.ConnectionToDataBase;

import java.sql.ResultSet;
import java.util.List;

public class ListConverterTest {

    @org.junit.Test
    public void testConvertResultSetToTableArray() throws Exception {

        List<String> tableNamesList;
        ResultSet rs = ConnectionToDataBase.getConnection().insertText("SHOW TABLES;");
        if (rs == null) throw new StackOverflowError("No tables");
        tableNamesList = ListConverter.convertResultSetToTableArray(rs);
        for (String str : tableNamesList) {
            System.out.println(str);
        }
    }
}