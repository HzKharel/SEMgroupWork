package semgroup;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class SQLiteDBconnection {

    private static  final  SQLiteDBconnection instance = new SQLiteDBconnection();

    private Connection connect() {
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

    public List<String>  command(String sql){

        List<String> serverResponse = new ArrayList<String>();

        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =  this.connect();
            stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tempResult = "";
            while (rs.next()) {
                tempResult = "";
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                for(int i=1;i<=rsmd.getColumnCount(); i++){
                    if(tempResult != ""){
                        tempResult += ',';
                    }
                    tempResult += rs.getString(i);

                }
                // System.out.println(rs.getString(1));
                serverResponse.add(tempResult);

            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return  serverResponse;
    }
    public static SQLiteDBconnection getInstance(){
        return instance;
    }
    private SQLiteDBconnection(){

    }
}
