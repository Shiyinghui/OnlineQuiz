package test;

import java.sql.Connection;
import java.sql.SQLException;

import com.system.util.ConnectionFactory;

public class TestDBConnection {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

            Connection conn = ConnectionFactory.getInstance().makeConnection();
            try {
                System.out.println(conn.getCatalog());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }



}
