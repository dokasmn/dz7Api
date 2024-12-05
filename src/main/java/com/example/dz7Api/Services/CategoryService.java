package com.example.dz7Api.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dz7Api.Models.Category;
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Repositories.CategoryRepository;
import com.example.dz7Api.Repositories.MusicRepository;
import com.example.dz7Api.dto.CategoryDTO;
import com.example.dz7Api.dto.MusicCategoryDTO;
import com.example.dz7Api.dto.MusicDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MusicRepository musicRepository;

    
    public CategoryService(CategoryRepository categoryRepository, MusicRepository musicRepository) {
        this.categoryRepository = categoryRepository;
        this.musicRepository = musicRepository;
    }


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->
            new EntityNotFoundException("Category not found!"));
    }


    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found!"));
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setMinCategoryTemperature(category.getMinCategoryTemperature());
        existingCategory.setMaxCategoryTemperature(category.getMaxCategoryTemperature());
        existingCategory.setMusics(category.getMusics());
        return categoryRepository.save(existingCategory);
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


    public void addMusicToCategory(Long categoryId, Long musicId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        Music music = musicRepository.findById(musicId)
            .orElseThrow(() -> new EntityNotFoundException("Música não encontrada"));

        category.getMusics().add(music);
        categoryRepository.save(category);
    }


    public void removeMusicFromCategory(Long categoryId, Long musicId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        Music music = musicRepository.findById(musicId)
            .orElseThrow(() -> new EntityNotFoundException("Música não encontrada"));

        category.getMusics().remove(music);
        categoryRepository.save(category);
    }


    public MusicCategoryDTO toMusicCategoryDTO(Music music) {
        MusicCategoryDTO dto = new MusicCategoryDTO();
        dto.setIdMusic(music.getIdMusic());
        dto.setMusicName(music.getMusicName());
        dto.setMusicLink(music.getMusicLink());
        dto.setMusicDuration(music.getMusicDuration());
        dto.setMusicGenre(music.getMusicGenre());
        dto.setMusicCountry(music.getMusicCountry());
        return dto;
    }
    


    public CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setIdCategory(category.getIdCategory());
        dto.setCategoryName(category.getCategoryName());
        dto.setMinCategoryTemperature(category.getMinCategoryTemperature());
        dto.setMaxCategoryTemperature(category.getMaxCategoryTemperature());
        dto.setMusics(category.getMusics().stream()
                               .map(this::toMusicCategoryDTO)
                               .collect(Collectors.toList()));
        return dto;
    }
    

}