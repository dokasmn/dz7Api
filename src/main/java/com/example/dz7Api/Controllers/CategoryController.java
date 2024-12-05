package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Category;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.UserRepository;
import com.example.dz7Api.Services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserRepository userRepository;


    public CategoryController(CategoryService categoryService, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category, @RequestParam Long userId, @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }

            if (!user.getRole().equals("Admin")) {
                return ResponseEntity.status(403).build();
            }

            Category savedCategory = categoryService.saveCategory(category);
            return ResponseEntity.status(201).body(savedCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category, @RequestParam Long userId, @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }

            if (!user.getRole().equals("Admin")) {
                return ResponseEntity.status(403).build();
            }

            Category updatedCategory = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(updatedCategory);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id, @RequestParam Long userId, @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }

            if (!user.getRole().equals("Admin")) {
                return ResponseEntity.status(403).build();
            }

            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
