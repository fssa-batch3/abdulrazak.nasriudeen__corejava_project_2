package com.fssa.reparo.util;


import com.fssa.reparo.exception.DTBException;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDb {

    public static Connection getConnection() throws DTBException {
        Connection connect = null ;
        String DB_URL;
        String DB_USER;
        String DB_PASSWORD;

        if (System.getenv("CI") != null) {
            DB_URL = System.getenv("DB_URL");
            DB_USER = System.getenv("DB_USER");
            DB_PASSWORD = System.getenv("DB_PASSWORD");
        } else {
            Dotenv env = Dotenv.load();
            DB_URL = env.get("DB_URL");
            DB_USER = env.get("DB_USER");
            DB_PASSWORD = env.get("DB_PASSWORD");
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            throw new DTBException("Problem with the connection to the data base",e);
        }




        return  connect ;
    }


}
