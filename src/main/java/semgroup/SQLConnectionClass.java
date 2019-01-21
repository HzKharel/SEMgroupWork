package semgroup;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class SQLConnectionClass {

    final String userName = System.getProperty("sqlUser");
    final String password = System.getProperty("sqlPassword");
    final String connectionString = System.getProperty("host");
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public List<String> ExecuteSQL(String statement) {
        List<String> serverResponse = new ArrayList<String>();



        String tempResult = "";
        try {
            while (rs.next()) {
                tempResult = "";
                rs = stmt.executeQuery(statement);
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                // System.out.println(rsmd.getColumnCount());
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (tempResult != "") {
                        tempResult += ",";
                    }
                    tempResult += rs.getString(i);

                }
                // System.out.println(rs.getString(1));
                serverResponse.add(tempResult);
            }

        }
        catch (Exception e){

        }

        return serverResponse;
    }

    public void SQLConnectionClass(){


        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(
                    connectionString, userName, password);
            stmt = (Statement) con.createStatement();
            System.out.println("Connection Established");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
