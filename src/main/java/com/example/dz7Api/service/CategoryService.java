package com.example.dz7Api.service;

import org.springframework.stereotype.Service;

import com.example.dz7Api.models.Category;
import com.example.dz7Api.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->
            new EntityNotFoundException("Category not found!"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
