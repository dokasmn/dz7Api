package com.example.dz7Api.dto;

import java.util.List;

public class CategoryInputDTO {
    private String categoryName;
    private int minCategoryTemperature;
    private int maxCategoryTemperature;
    private List<Long> musicIds;

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

    public List<Long> getMusicIds() {
        return musicIds;
    }

    public void setMusicIds(List<Long> musicIds) {
        this.musicIds = musicIds;
    }
}

