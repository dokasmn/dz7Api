package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Services.UserService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<BaseUser>> listUsers() {
        List<BaseUser> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseUser> getUserById(@PathVariable Long id) {
        try {
            BaseUser user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PostMapping
    public ResponseEntity<BaseUser> saveUser(@RequestBody BaseUser user) {
        try {
            BaseUser savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<BaseUser> updateUser(@PathVariable Long id, @RequestBody BaseUser user) {
        try {
            BaseUser existingUser = userService.getUserById(id);

            if (existingUser.getRole().equals("Admin")) {
                return ResponseEntity.status(403).body(null);
            }

            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setUserPassword(user.getUserPassword());

            BaseUser updatedUser = userService.saveUser(existingUser);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        BaseUser user = userService.getUserById(id);

        if (user.getRole().equals("Admin")) {
            return ResponseEntity.status(403).build();
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
