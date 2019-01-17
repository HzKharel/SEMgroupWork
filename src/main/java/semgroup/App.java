package semgroup;
import java.util.*;

public class App {

    public static void  main(String args[]){
        SQLConnectionClass sql = new SQLConnectionClass();
       List<String> SqlReturn =  sql.ExecuteSQL("Select name,population,countrycode from city where countrycode = 'GBR' ORDER by name");
        for (String x :SqlReturn) {
            System.out.println(x);

        }
    }
}
