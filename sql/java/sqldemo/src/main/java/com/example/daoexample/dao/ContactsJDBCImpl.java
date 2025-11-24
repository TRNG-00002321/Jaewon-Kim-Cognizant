package com.example.daoexample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.daoexample.model.Contacts;
import com.example.daoexample.util.Database;

public class ContactsJDBCImpl implements ContactDAO{
    Connection connection=null;

    @Override
    public Contacts getContact(int id){
        connection = Database.connect();
        Contacts contacts=null;
        String getContact="select * from contacts where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(getContact);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                contacts=new Contacts(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return contacts;
    }
    
    @Override
    public List<Contacts> getAllContacts(){
        connection = Database.connect();
        List<Contacts> contacts = new ArrayList<>();
        String sql = "select * from contacts";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                contacts.add(new Contacts(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    @Override
    public void save(Contacts contacts){
        connection = Database.connect();
        String sql = "INSERT INTO contacts (name, email, phone) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, contacts.getName());
            preparedStatement.setString(2, contacts.getEmail());
            preparedStatement.setString(3, contacts.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Contacts update(Contacts contacts){
        connection = Database.connect();
        String sql = "UPDATE contacts SET name=?, email=?, phone=? WHERE id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, contacts.getName());
            preparedStatement.setString(2, contacts.getEmail());
            preparedStatement.setString(3, contacts.getPhone());
            preparedStatement.setInt(4, contacts.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
}

    @Override
    public void delete(int id){
        connection = Database.connect();
        String sql = "DELETE FROM contacts WHERE id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            boolean rowsAffected = preparedStatement.execute();
            if(rowsAffected) throw new Exception("Delete failed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch(Exception e){

        }
    }
}
