package semgroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
* A class used to connect and execute commands to the local SQLITE database, world.sqlite
* Implements a singleton anti patten to stop multiple instantiations of the connection being created
*
* Originally Created by: Hari Kharel
* Original Creation Date: 22/01/2019
*
* Last Modified by: Hari Kharel
* Last Modification Date: 22/01/2019
*
* Known bugs: Nones
*
* Up to date on GitHub.
*
 */
public class SQLiteDBconnection {

    private static final SQLiteDBconnection instance = new SQLiteDBconnection();
    private  static Connection dbConnection = null;

    /*
    *private connection method, called by the instance method when the object instance is being created
    *Takes in no parameters and returns a connection
    */
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/database/world.sqlite";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /*
    * A method that's used to execute SQL statements
    * Takes in a string which is a SQL statement and returns an SQL response as an string array, separated by ','
    * Uses the connection pre established by the instance method
    *
     */
    public List<String> command(String sql) {

        List<String> serverResponse = new ArrayList<String>();

        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = dbConnection;
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tempResult = "";

            while (rs.next()) {
                tempResult = "";
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (tempResult != "") {
                        tempResult += ',';
                    }
                    tempResult += rs.getString(i);

                }
                serverResponse.add(tempResult);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return serverResponse;
    }
    //Instance method
    public static SQLiteDBconnection getInstance() {
        dbConnection = connect();
        return instance;
    }
    //private Constructor
    private SQLiteDBconnection() { }
}
