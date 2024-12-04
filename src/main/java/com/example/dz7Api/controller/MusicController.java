package com.example.dz7Api.controller;

import org.springframework.web.bind.annotation.*;

import com.example.dz7Api.models.Music;
import com.example.dz7Api.service.MusicService;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/music")
public class MusicController {

    List<Music> musics = new ArrayList<>();

    private final MusicService musicService;

    
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }


    @GetMapping
    public ResponseEntity<List<Music>> getAllMusics() {
        List<Music> musics = musicService.findAll();
        if (musics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(musics);
    }


    // @GetMapping("/{musicName}")
    // public ResponseEntity<Music> getMusic(@PathVariable String musicName) {
    //     if (musicName == null || musicName.isEmpty()) {
    //         return ResponseEntity.badRequest().build();
    //     }

    //     Optional<Music> foundMusic = musics.stream()
    //     .filter(music -> music.getMusicName().equalsIgnoreCase(musicName))
    //     .findFirst();

    //     return foundMusic.map(ResponseEntity::ok)
    //                 .orElse(ResponseEntity.notFound().build());
    // }


    @PostMapping
    public ResponseEntity<Music> saveMusic(@RequestBody Music music) {
        try {
            Music savedMusic = musicService.saveMusic(music);
            return ResponseEntity.ok(savedMusic);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        try {
            Music music = musicService.getMusicById(id);
            return ResponseEntity.ok(music);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return ResponseEntity.noContent().build();
    }
}