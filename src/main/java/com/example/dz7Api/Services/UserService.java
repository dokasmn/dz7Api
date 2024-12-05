package com.example.dz7Api.Services;

import org.springframework.stereotype.Service;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<BaseUser> findAllUsers() {
        return userRepository.findAll();
    }


    public BaseUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }


    public BaseUser saveUser(BaseUser user) {
        return userRepository.save(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

