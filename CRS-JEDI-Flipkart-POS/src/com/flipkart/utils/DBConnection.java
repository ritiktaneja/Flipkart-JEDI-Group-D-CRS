package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/CSRDatabase";
    private static final String ID = "root";
    private static final String PASS = "";


    private DBConnection() {
    }

    public static Connection getConnection() {
        try {
            Connection DBConnection = null;
            Class.forName(DRIVER_NAME);
            DBConnection = DriverManager.getConnection(DB_URL, ID, PASS);
            return DBConnection;
        } catch (Exception e) {
            System.out.println("Connection not established");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
