package com.example.dz7Api.dto;

import java.util.List;

public class CategoryUpdateDTO {
    private String categoryName;
    private int minCategoryTemperature;
    private int maxCategoryTemperature;


    public CategoryUpdateDTO(){}

    
    public CategoryUpdateDTO(String categoryName, int minCategoryTemperature, int maxCategoryTemperature) {
        this.categoryName = categoryName;
        this.minCategoryTemperature = minCategoryTemperature;
        this.maxCategoryTemperature = maxCategoryTemperature;
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

}
