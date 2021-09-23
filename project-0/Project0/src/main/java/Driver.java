import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args){
        try
        {
            Connection conn = ConnectionManager.getConnection();



        }
        catch(SQLException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
