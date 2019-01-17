package semgroup;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnectionClass {
    public List<String> ExecuteSQL(String statement) {
        List<String> serverResponse = new ArrayList<String>();

        final String userName = System.getProperty("sqlUser");
        final String password = System.getProperty("sqlPassword");
        final String connectionString = System.getProperty("host");

        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(
                    connectionString, userName, password);
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(statement);

            while (rs.next()) {
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                String tempResult = "";
                System.out.println(rsmd.getColumnCount());
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    // System.out.println(rs.getString(i));
                }
                serverResponse.add(tempResult);
                System.out.println(rs.getString(tempResult));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return serverResponse;
    }
}
