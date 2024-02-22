package com.tanukode.crudapp;

import org.springframework.data.annotation.Id;

import java.util.UUID;


public class User{

    @Id
    private final UUID uid;
    private final String name;
    private final String email;
    private final String password;


    public User(UUID uid, String name, String email, String password){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return String.format("User{uid='%s', name='%s', email='%s', password='%s'}", uid, name, email, password);
    }
}
