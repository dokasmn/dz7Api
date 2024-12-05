package com.example.dz7Api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dz7Api.Models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}