package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Services.MusicService;
import com.example.dz7Api.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;
    private final UserRepository userRepository;

    public MusicController(MusicService musicService, UserRepository userRepository) {
        this.musicService = musicService;
        this.userRepository = userRepository;
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

    @PostMapping
    public ResponseEntity<Music> saveMusic(@RequestBody Music music,
                                           @RequestParam Long userId,
                                           @RequestParam String userPassword) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        
        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (user.getRole().equals("Artista")) {
            try {
                Music savedMusic = musicService.saveMusic(music, (Artist) user);
                return ResponseEntity.ok(savedMusic);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@RequestBody Music music,
                                             @PathVariable Long id,
                                             @RequestParam Long userId,
                                             @RequestParam String userPassword) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            Music existingMusic = musicService.getMusicById(id);
            if (existingMusic.getOwners().equals(user) || user.getRole().equals("Admin")) {
                Music updatedMusic = musicService.updateMusic(music, id, (Artist) user);
                return ResponseEntity.ok(updatedMusic);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id,
                                            @RequestParam Long userId,
                                            @RequestParam String userPassword) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            Music music = musicService.getMusicById(id);
            if (music.getOwners().equals(user) || user.getRole().equals("Admin")) {
                musicService.deleteMusic(id, user);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
