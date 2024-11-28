package com.example.dz7Api.Models;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Category {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private String categoryTemperature;

    public Category(String categoryName, String categoryTemperature) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("A categoria deve possuir um nome!");
        }
        
        if (categoryTemperature == null || categoryTemperature.isEmpty()) {
            throw new IllegalArgumentException("A categoria deve possuir uma temperatura!");
    }

    this.categoryName = categoryName;
    this.categoryTemperature = categoryTemperature;
}
