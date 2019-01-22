package semgroup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class App {

    public static void  main(String args[]){
        SQLiteDBconnection sql = SQLiteDBconnection.getInstance();

        System.out.println(sql.command("Select name from city"));


    }


}

