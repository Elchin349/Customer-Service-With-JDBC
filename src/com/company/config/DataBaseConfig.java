package com.company.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    private static DataBaseConfig dataBaseConfig = null;
    private final String url =  "jdbc:postgresql://localhost/customer_tracker";
    private final String user = "postgres";
    private final String password = "3499190";

    private DataBaseConfig(){

    }

    public static DataBaseConfig INSTANCE(){
        if(dataBaseConfig == null){
            dataBaseConfig = new DataBaseConfig();
        }
        return dataBaseConfig;
    }

    public Connection connect() throws ClassNotFoundException {

        Connection con = null;

        try {
            con = DriverManager.getConnection(url,user,password);
           // System.out.println("Connected to the PostgreSQL server succesfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }
}
