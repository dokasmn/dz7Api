    package com.example.dz7Api.Models;

    import java.util.List;

import com.example.dz7Api.Models.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;

    @Entity
    @Table(name = "category")
    public class Category extends BaseModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_category")
        private Long idCategory;

        @Column(name = "name_category")
        private String categoryName;

        @Column(name = "min_temperature")
        private int minCategoryTemperature;

        @Column(name = "max_temperature")
        private int maxCategoryTemperature;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<Music> musics;


        public Category(){}
        

        public Category(Long idCategory, String categoryName, int minCategoryTemperature, int maxCategoryTemperature,
                List<Music> musics) {
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


        public List<Music> getMusics() {
            return musics;
        }


        public void setMusics(List<Music> musics) {
            this.musics = musics;
        }

    }