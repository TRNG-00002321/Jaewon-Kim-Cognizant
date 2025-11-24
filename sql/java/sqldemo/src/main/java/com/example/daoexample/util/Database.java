package com.example.daoexample.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connection = null;
    public static Connection connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "root", "password");
        } catch (Exception e) {
        }
        return connection;
    }
}
