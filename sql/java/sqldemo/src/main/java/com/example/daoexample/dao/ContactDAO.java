package com.example.daoexample.dao;

import java.util.List;

import com.example.daoexample.model.Contacts;

public interface ContactDAO {

    public default List<Contacts> getAllContacts(){
        return null;
    }
    public default Contacts getContact(int id){
        return null;
    }
    public default void save(Contacts contacts){

    }
    public default Contacts update(Contacts contacts){
        return null;
    }
    public default void delete(int id){

    }
}
