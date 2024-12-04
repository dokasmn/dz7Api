package com.example.dz7Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dz7Api.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
