package com.example.dz7Api.Models.base;

    
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
    private Long id;
    private String username;
    private String email;
    private String userPassword;

    
    public BaseUser(String username, String email, String userPassword) {
        this.username = username;
        this.email = email;
        this.userPassword = userPassword;
    }
    
    public abstract String getRole();
    

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


    public String getPassword() {
        return userPassword;
    }
    

    public void setPassword(String password) {
        this.userPassword = password;
    }
}


