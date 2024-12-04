    package com.example.dz7Api.models;

    import java.util.List;

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
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_category")
        private int idCategory;

        @Column(name = "name_category")
        private String categoryName;

        @Column(name = "temperature")
        private int categoryTemperature;

        @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<Music> musics;


        public Category(){}

        public Category(int idCategory, String categoryName, int categoryTemperature, List<Music> musics) {
            this.idCategory = idCategory;
            this.categoryName = categoryName;
            this.categoryTemperature = categoryTemperature;
            this.musics = musics;
        }

        public int getIdCategory() {
            return idCategory;
        }

        public void setIdCategory(int idCategory) {
            this.idCategory = idCategory;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCategoryTemperature() {
            return categoryTemperature;
        }

        public void setCategoryTemperature(int categoryTemperature) {
            this.categoryTemperature = categoryTemperature;
        }

        public List<Music> getMusics() {
            return musics;
        }

        public void setMusics(List<Music> musics) {
            this.musics = musics;
        }

    }