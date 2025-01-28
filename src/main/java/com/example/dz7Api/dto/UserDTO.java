package com.example.dz7Api.dto;

public class UserDTO {
    private String username;
    private String email;
    private String userPassword;
    private String role;

    public UserDTO() {}

    public UserDTO(String username, String email, String userPassword, String role) {
        this.username = username;
        this.email = email;
        this.userPassword = userPassword;
        this.role = role;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
