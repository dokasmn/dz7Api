package com.example.dz7Api.Models;

import com.example.dz7Api.Models.base.BaseUser;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class StandardUser extends BaseUser {

    public StandardUser(String username, String email, String password) {
        super(username, email, password);
    }

    @Override
    public String getRole() {
        return "Standard";
    }

    public void likeMusic(Music music) {}
}
