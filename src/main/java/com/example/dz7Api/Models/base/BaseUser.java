package com.example.dz7Api.Models.base;

    
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String username;
    private String email;
    private String userPassword;


    public BaseUser() {
    }

    
    public BaseUser(String username, String email, String userPassword) {
        this.username = username;
        this.email = email;
        this.userPassword = userPassword;
    }


    public void validatePassword(String passwordToCheck) {
        if (!this.userPassword.equals(passwordToCheck)) {
            throw new SecurityException("Unauthorized access");
        }
    }
    

    public abstract String getRole();
    
    
    public Long getId() {
        return id;
    }
    

    public void setId(Long id) {
        this.id = id;
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

}


