package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.*;

import com.example.dz7Api.Models.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/music")
public class MusicController {

    List<Music> musics = new ArrayList<>();


    @GetMapping
    public ResponseEntity<List<Music>> listMusics (Model model) {
        if (musics.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(musics);
    }


    @GetMapping("{musicName}")
    public ResponseEntity<Music> getMusic (@PathVariable String musicName) {

        if (musicName == null || musicName.isEmpty())
            return ResponseEntity.badRequest().build();

        Optional<Music> foundMusic = musics.stream()
                                            .filter(music -> music.getMusicName().equalsIgnoreCase(musicName))
                                            .findFirst();
        if (foundMusic.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(foundMusic.get());
    }


   @PostMapping("")
   public ResponseEntity<String> addMusic (@RequestBody Music newMusic) {
       return ResponseEntity.created();
   }
}
