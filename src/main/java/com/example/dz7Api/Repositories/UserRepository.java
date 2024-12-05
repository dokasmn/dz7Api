package com.example.dz7Api.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dz7Api.Models.base.BaseUser;

public interface UserRepository extends JpaRepository<BaseUser, Long> {
    List<BaseUser> findByUsername(String username);
    List<BaseUser> findByEmail(String email);
}
