package com.example.dz7Api.Models;

import com.example.dz7Api.Models.base.BaseUser;

import jakarta.persistence.Entity;

@Entity
public class Admin extends BaseUser {


    public Admin() {}


    public Admin(String username, String email, String password) {
        super(username, email, password);
    }


    @Override
    public String getRole() {
        return "Admin";
    }


    // public void manageUser(User user) {
        
    // }

    // public void deleteMusic(Music music) {
        
    // }
}