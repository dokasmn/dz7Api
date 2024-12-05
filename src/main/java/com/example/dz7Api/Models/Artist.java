package com.example.dz7Api.Models;

import com.example.dz7Api.Models.base.BaseUser;

import java.util.List;

// jakarta
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Artist extends BaseUser {

    @ManyToMany(mappedBy = "owners")
    private List<Music> musics;


    public Artist() {
    }


    public Artist(String username, String email, String password) {
        super(username, email, password);
    }


    @Override
    public String getRole() {
        return "Artist";
    }
    
    
    public void addMusic(Music music) {
        if (music != null) {
            musics.add(music);
        }
    }
    
    
    public void removeMusic(Music music) {
        musics.remove(music);
    }


    public List<Music> getMusics() {
        return musics;
    }


    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }
}