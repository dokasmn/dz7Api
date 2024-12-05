package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Category;
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.UserRepository;
import com.example.dz7Api.Services.CategoryService;
import com.example.dz7Api.Services.MusicService;
import com.example.dz7Api.dto.CategoryDTO;
import com.example.dz7Api.dto.CategoryInputDTO;
import com.example.dz7Api.dto.CategoryUpdateDTO;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserRepository userRepository;
    private final MusicService musicService;


    public CategoryController(CategoryService categoryService, UserRepository userRepository, MusicService musicService) {
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.musicService = musicService;
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoryDTO> categoryDTOs = categories.stream()
            .map(categoryService::toCategoryDTO)
            .toList();
        return ResponseEntity.ok(categoryDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(categoryService.toCategoryDTO(category));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(
        @RequestBody CategoryInputDTO categoryInputDTO,
        @RequestParam Long userId,
        @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }

            if (!user.getRole().equals("Admin")) {
                return ResponseEntity.status(403).build();
            }

            Category category = new Category();
            category.setCategoryName(categoryInputDTO.getCategoryName());
            category.setMinCategoryTemperature(categoryInputDTO.getMinCategoryTemperature());
            category.setMaxCategoryTemperature(categoryInputDTO.getMaxCategoryTemperature());

            List<Music> musics = categoryInputDTO.getMusicIds().stream()
                    .map(musicService::getMusicById)
                    .toList();
            category.setMusics(musics);
            Category savedCategory = categoryService.saveCategory(category);
            CategoryDTO categoryDTO = categoryService.toCategoryDTO(savedCategory);
            return ResponseEntity.status(201).body(categoryDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
        @PathVariable Long id,
        @RequestBody CategoryUpdateDTO categoryUpdateDTO,
        @RequestParam Long userId,
        @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }

            if (!user.getRole().equals("Admin")) {
                return ResponseEntity.status(403).build();
            }

            Category existingCategory = categoryService.getCategoryById(id);
            existingCategory.setCategoryName(categoryUpdateDTO.getCategoryName());
            existingCategory.setMinCategoryTemperature(categoryUpdateDTO.getMinCategoryTemperature());
            existingCategory.setMaxCategoryTemperature(categoryUpdateDTO.getMaxCategoryTemperature());
            Category updatedCategory = categoryService.updateCategory(id, existingCategory);
            CategoryDTO categoryDTO = categoryService.toCategoryDTO(updatedCategory);
            return ResponseEntity.ok(categoryDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
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


    @PostMapping("/{categoryId}/add-music/{musicId}")
    public ResponseEntity<Void> addMusicToCategory(@PathVariable Long categoryId,
                                                @PathVariable Long musicId,
                                                @RequestParam Long userId,
                                                @RequestParam String userPassword) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            Music music = musicService.getMusicById(musicId);
            Category category = categoryService.getCategoryById(categoryId);

            boolean isOwner = music.getOwners().stream()
                .anyMatch(owner -> owner.getId().equals(user.getId()));
            boolean isAdmin = "Admin".equals(user.getRole());

            if (isOwner || isAdmin) {
                categoryService.addMusicToCategory(categoryId, musicId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{categoryId}/remove-music/{musicId}")
    public ResponseEntity<Void> removeMusicFromCategory(
        @PathVariable Long categoryId,
        @PathVariable Long musicId,
        @RequestParam Long userId,
        @RequestParam String userPassword
    ) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            Music music = musicService.getMusicById(musicId);
            Category category = categoryService.getCategoryById(categoryId);
            boolean isOwner = music.getOwners().stream()
                .anyMatch(owner -> owner.getId().equals(user.getId()));
            boolean isAdmin = "Admin".equals(user.getRole());

            if (isOwner || isAdmin) {
                categoryService.removeMusicFromCategory(categoryId, musicId);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
