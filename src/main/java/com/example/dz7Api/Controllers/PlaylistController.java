package com.example.dz7Api.Controllers;

// models
import com.example.dz7Api.Models.Playlist;
import com.example.dz7Api.Services.PlaylistService;

// jakarta
import jakarta.persistence.EntityNotFoundException;

// java util
import java.util.ArrayList;
import java.util.List;

// spring
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {
    
    List<Playlist> playlists = new ArrayList<>();

    private final PlaylistService playlistService;


    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }


    @GetMapping
    public ResponseEntity<List<Playlist>> listPlaylists(@RequestParam Long userId) {
        List<Playlist> playlists = playlistService.findAll(userId);
        if (playlists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(playlists);
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

    
    @DeleteMapping("/{playlistId}/remove-music/{musicId}")
    public ResponseEntity<Playlist> removeMusicFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long musicId) {
        try {
            Playlist updatedPlaylist = playlistService.removeMusicFromPlaylist(playlistId, musicId);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

   
    @PutMapping("/{playlistId}")
    public ResponseEntity<Playlist> updatePlaylist(
            @PathVariable Long playlistId,
            @RequestBody Playlist updatedPlaylist) {
        try {
            Playlist savedPlaylist = playlistService.updatePlaylist(playlistId, updatedPlaylist);
            return ResponseEntity.ok(savedPlaylist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
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

}
