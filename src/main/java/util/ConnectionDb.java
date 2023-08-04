package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {

    public static Connection getConnection() throws DTBException{
        Connection connect = null ;
        String url = "jdbc:mysql://localhost/reparo";
        String userName = "root";
        String passWord = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            throw new DTBException("Problem with the connection to the data base",e);
        }




        return  connect ;
    }


}
