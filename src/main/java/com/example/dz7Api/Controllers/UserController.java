package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.UserRepository;
import com.example.dz7Api.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<BaseUser>> listUsers(@RequestParam Long requestUserId, @RequestParam String userPassword) {
        BaseUser requestingUser = userService.getUserById(requestUserId);
        if (requestingUser.getUserPassword().equals(userPassword) && requestingUser.getRole().equals("Admin")) {
            List<BaseUser> users = userService.findAllUsers();
            users.forEach(user -> user.setUserPassword("Não visível"));
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseUser> getUserById(@PathVariable Long id, @RequestParam Long requestUserId, @RequestParam String userPassword) {
        try {
            BaseUser requestingUser = userService.getUserById(requestUserId);
            if (requestingUser.getUserPassword().equals(userPassword)) {
                BaseUser user = userService.getUserById(id);
                if (requestingUser.getId().equals(user.getId()) || requestingUser.getRole().equals("Admin")) {
                    if (requestingUser.getRole().equals("Admin")) {
                        user.setUserPassword(null);
                    }
                    return ResponseEntity.ok(user);
                }
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<BaseUser> saveUser(@RequestBody BaseUser user, @RequestParam Long requestUserId, @RequestParam String userPassword) {
        BaseUser requestingUser = userService.getUserById(requestUserId);
        if (requestingUser.getUserPassword().equals(userPassword) && requestingUser.getRole().equals("Admin")) {
            try {
                BaseUser savedUser = userService.saveUser(user);
                return ResponseEntity.ok(savedUser);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseUser> updateUser(@PathVariable Long id, @RequestBody BaseUser user, 
                                               @RequestParam Long requestUserId, @RequestParam String userPassword) {
        try {
            BaseUser existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            BaseUser requestingUser = userService.getUserById(requestUserId);
            if (requestingUser.getUserPassword().equals(userPassword) && existingUser.getUserPassword().equals(userPassword)) {
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                userRepository.save(existingUser);
                return ResponseEntity.ok(existingUser);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestParam String userPassword, @RequestParam Long requestUserId) {
        try {
            BaseUser existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            BaseUser requestingUser = userService.getUserById(requestUserId);
            if (requestingUser.getUserPassword().equals(userPassword) && existingUser.getUserPassword().equals(userPassword)) {
                userRepository.delete(existingUser);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
