package com.fssa.reparo.util;
import com.fssa.reparo.exception.DTBException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDb {

    public static Connection getConnection() throws DTBException {
        Connection connect;
        String dbUrl;
        String dbUser;
        String dbPassword;


            dbUrl = System.getenv("DB_URL");
            dbUser = System.getenv("DB_USER");
            dbPassword = System.getenv("DB_PASSWORD");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            assert dbUrl != null;
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception e) {
            throw new DTBException("Problem with the connection to the data base",e);
        }
        return  connect ;
    }

   

    }



