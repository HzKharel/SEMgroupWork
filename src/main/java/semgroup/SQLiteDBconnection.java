package semgroup;
import java.sql.*;
import java.util.List;

import static java.lang.Class.forName;

public class SQLiteDBconnection {
    public List<String> Command (String statement){
        Connection c = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/database/world.db";
            // create a connection to the database
            c = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return  null;
    }
}
