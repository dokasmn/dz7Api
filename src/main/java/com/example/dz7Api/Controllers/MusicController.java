package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Artist;
// models
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.base.BaseUser;
// services
import com.example.dz7Api.Services.MusicService;

// jakarta
import jakarta.persistence.EntityNotFoundException;

// java util
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// spring
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/music")
public class MusicController {

    List<Music> musics = new ArrayList<>();

    private final MusicService musicService;

    
    @Autowired
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


    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable Long id) {
        try {
            Music music = musicService.getMusicById(id);
            return ResponseEntity.ok(music);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<Music> saveMusic(@RequestBody Music music, @RequestParam Artist artist) {
        try {
            Music savedMusic = musicService.saveMusic(music, artist);
            return ResponseEntity.ok(savedMusic);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/{id}")
    public Music updateMusic(@RequestBody Music music, @PathVariable Long id, @RequestParam Artist artist) {
        return musicService.updateMusic(music, id, artist);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id, @RequestParam BaseUser user) {
        musicService.deleteMusic(id, user);
        return ResponseEntity.noContent().build();
    }
}