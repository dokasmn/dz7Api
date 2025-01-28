package com.example.dz7Api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.dz7Api.Models.Category;

public class CategoryDTO {
    private Long idCategory;
    private String categoryName;
    private int minCategoryTemperature;
    private int maxCategoryTemperature;
    private List<MusicCategoryDTO> musics;


    public CategoryDTO(){}
    
    
    public CategoryDTO(Long idCategory, String categoryName, int minCategoryTemperature, int maxCategoryTemperature,
            List<MusicCategoryDTO> musics) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.minCategoryTemperature = minCategoryTemperature;
        this.maxCategoryTemperature = maxCategoryTemperature;
        this.musics = musics;
    }


    public Long getIdCategory() {
        return idCategory;
    }


    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }


    public String getCategoryName() {
        return categoryName;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public int getMinCategoryTemperature() {
        return minCategoryTemperature;
    }


    public void setMinCategoryTemperature(int minCategoryTemperature) {
        this.minCategoryTemperature = minCategoryTemperature;
    }


    public int getMaxCategoryTemperature() {
        return maxCategoryTemperature;
    }


    public void setMaxCategoryTemperature(int maxCategoryTemperature) {
        this.maxCategoryTemperature = maxCategoryTemperature;
    }


    public List<MusicCategoryDTO> getMusics() {
        return musics;
    }


    public void setMusics(List<MusicCategoryDTO> musics) {
        this.musics = musics;
    }


}