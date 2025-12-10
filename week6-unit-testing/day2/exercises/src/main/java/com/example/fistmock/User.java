package com.example.fistmock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private boolean active = true;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
    
    // Constructors, getters, setters
}
