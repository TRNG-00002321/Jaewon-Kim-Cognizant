package com.example.statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chinook", "root", "password")){
            String sql = "SELECT DISTINCT " +
                                "c.`Email`, " +
                                "CONCAT(c.`FirstName`,\" \",c.`LastName`) " +
                                "FROM `Customer` c " +
                                "INNER JOIN `Invoice` i on c.`CustomerId` = i.`CustomerId` " + 
                                "INNER JOIN `InvoiceLine` il on il.`InvoiceId` = i.`InvoiceId` " +
                                "INNER JOIN `Track` t on t.`TrackId` = il.`TrackId` " +
                                "INNER JOIN `Genre` g on t.`GenreId` = g.`GenreId` " +
                                "WHERE g.`Name` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Rock");
            if(preparedStatement.execute()) System.out.println("Insert Worked!");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
