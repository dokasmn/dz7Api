package com.example.dz7Api.Models;

import java.util.HashSet;
import java.util.Set;


import com.example.dz7Api.Models.base.BaseUser;


// jakarta
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Artist extends BaseUser {

    @ManyToMany
    @JoinTable(
        name = "artist_has_music",
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<Music> musics;


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
}