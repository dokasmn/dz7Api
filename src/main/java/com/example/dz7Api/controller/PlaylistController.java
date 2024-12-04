package com.example.dz7Api.controller;

import org.springframework.web.bind.annotation.*;

import com.example.dz7Api.models.Playlist;
import com.example.dz7Api.service.PlaylistService;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
    
    List<Playlist> playlists = new ArrayList<>();

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listPlaylists(Model model){
        if (playlists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{playlistName}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Playlist> foundPlaylist = playlists.stream().filter(playlists -> playlists.getPlaylistName().equalsIgnoreCase(playlistName)).findFirst();

        return foundPlaylist.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Playlist> savePlaylist(@RequestBody Playlist playlist){
        try {
            Playlist savedPlaylist = playlistService.savePlaylist(playlist);
            return ResponseEntity.ok(savedPlaylist);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long id) {
        try {
            Playlist playlist = playlistService.getPlaylistById(id);
            return ResponseEntity.ok(playlist);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }
}
