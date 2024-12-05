package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.dz7Api.Models.Admin;
import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Models.StandardUser;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.UserRepository;
import com.example.dz7Api.Services.UserService;
import com.example.dz7Api.dto.UserDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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
    public ResponseEntity<BaseUser> saveUser(
        @RequestBody UserDTO userDTO,
        @RequestParam(required = false) Long requestUserId,
        @RequestParam(required = false) String userPassword
    ) {
        BaseUser requestingUser = userService.getUserById(requestUserId);

        if (requestingUser.getUserPassword().equals(userPassword) && requestingUser.getRole().equals("Admin")) {
            try {
                BaseUser newUser;
                switch (userDTO.getRole()) {
                    case "Admin":
                        newUser = new Admin(userDTO.getUsername(), userDTO.getEmail(), userDTO.getUserPassword());
                        break;
                    case "Standard":
                        newUser = new StandardUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getUserPassword());
                        break;
                    case "Artist":
                        newUser = new Artist(userDTO.getUsername(), userDTO.getEmail(), userDTO.getUserPassword());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid role: " + userDTO.getRole());
                }

                BaseUser savedUser = userService.saveUser(newUser);
                return ResponseEntity.ok(savedUser);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO,
            @RequestParam String userPassword
    ) {
        try {
            BaseUser existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            BaseUser requestingUser = userService.getUserById(id);
            if (!requestingUser.getUserPassword().equals(userPassword) ||
                    !existingUser.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());
            userRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + id + " não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar o usuário: " + e.getMessage());
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
