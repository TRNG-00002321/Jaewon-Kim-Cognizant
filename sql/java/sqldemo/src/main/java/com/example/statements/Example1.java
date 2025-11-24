package com.example.statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Example1 {
    public static void main(String[] args) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            //STEP 1.)LOAD DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 2.)CREATE CONNECTION
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "root", "password");
            //STEP 3.)CREATE THE STATEMENT OBJ
            statement=connection.createStatement();
            String selectQuery ="select * from Employee";
            //STEP 4.)EXECUTE THE QUERY AND COLLECT RESULTS IN RESULT SET
            resultSet = statement.executeQuery(selectQuery);
            //STEP 5.)PROCESS RESULT SET
            while(resultSet.next()){
                System.out.print("ID: "+resultSet.getInt("EmployeeId"));
                System.out.println(" Email: "+resultSet.getString("email"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
