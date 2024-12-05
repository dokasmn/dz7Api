package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.Playlist;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.MusicRepository;
import com.example.dz7Api.Repositories.UserRepository;
import com.example.dz7Api.Services.PlaylistService;
import com.example.dz7Api.dto.PlaylistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<Playlist>> listPlaylists(@RequestParam Long userId) {
        List<Playlist> playlists = playlistService.findAll(userId);
        if (playlists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(playlists);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        try {
            Playlist playlist = playlistService.getPlaylistById(id);
            return ResponseEntity.ok(playlist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{playlistId}/add-music/{musicId}")
    public ResponseEntity<Playlist> addMusicToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long musicId) {
        try {
            Playlist updatedPlaylist = playlistService.addMusicToPlaylist(playlistId, musicId);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        BaseUser user = userRepository.findById(playlistDTO.getPlaylistUser())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getUserPassword().equals(playlistDTO.getUserPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Playlist playlist = new Playlist(playlistDTO.getPlaylistName(), new HashSet<>(), user);
        Playlist savedPlaylist = playlistService.savePlaylist(playlist);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlaylist);
    }


    @PutMapping("/{playlistId}")
    public ResponseEntity<Playlist> updatePlaylist(
            @PathVariable Long playlistId,
            @RequestBody PlaylistDTO playlistDTO) {
        try {
            BaseUser user = userRepository.findById(playlistDTO.getPlaylistUser())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            if (!user.getUserPassword().equals(playlistDTO.getUserPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            Playlist playlistToUpdate = playlistService.getPlaylistById(playlistId);
            playlistToUpdate.setPlaylistName(playlistDTO.getPlaylistName());
            Playlist updatedPlaylist = playlistService.updatePlaylist(playlistId, playlistToUpdate);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/{playlistId}/remove-music/{musicId}")
    public ResponseEntity<Playlist> removeMusicFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long musicId,
            @RequestParam Long userId,
            @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            user.validatePassword(userPassword);

            Playlist updatedPlaylist = playlistService.removeMusicFromPlaylist(playlistId, musicId);

            return ResponseEntity.ok(updatedPlaylist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(
            @PathVariable Long playlistId,
            @RequestParam Long userId,
            @RequestParam String userPassword) {
        try {
            BaseUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

            user.validatePassword(userPassword);
            playlistService.deletePlaylist(playlistId, userId);

            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
