package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.Playlist;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Repositories.UserRepository;
import com.example.dz7Api.Services.MusicService;
import com.example.dz7Api.Services.PlaylistService;
import com.example.dz7Api.Services.UserService;
import com.example.dz7Api.dto.PlaylistCreateDTO;
import com.example.dz7Api.dto.PlaylistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MusicService musicService;


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


    @PutMapping("/{playlistId}/addMusic/{musicId}")
    public ResponseEntity<Playlist> addMusicToPlaylist(@PathVariable Long playlistId,
                                                        @PathVariable Long musicId,
                                                        @RequestParam Long userId,
                                                        @RequestParam String userPassword) {
        try {
            BaseUser user = userService.getUserById(userId);
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }
            Playlist playlist = playlistService.getPlaylistById(playlistId);

            if (playlist.getPlaylistUser().getId().equals(userId)) {
                Music music = musicService.getMusicById(musicId);
                

                Set<Music> currentMusics = playlist.getPlaylistMusics();
                currentMusics.add(music);
                playlist.setPlaylistMusics(currentMusics);
                Playlist updatedPlaylist = playlistService.savePlaylist(playlist);
                return ResponseEntity.ok(updatedPlaylist);
            } else {
                return ResponseEntity.status(403).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
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


    @PutMapping("/{playlistId}/removeMusic/{musicId}")
    public ResponseEntity<Playlist> removeMusicFromPlaylist(@PathVariable Long playlistId,
                                                            @PathVariable Long musicId,
                                                            @RequestParam Long userId,
                                                            @RequestParam String userPassword) {
        try {
            BaseUser user = userService.getUserById(userId);
            if (!user.getUserPassword().equals(userPassword)) {
                return ResponseEntity.status(401).build();
            }

            Playlist playlist = playlistService.getPlaylistById(playlistId);

            if (playlist.getPlaylistUser().getId().equals(userId)) {
                Music music = musicService.getMusicById(musicId);

                Set<Music> currentMusics = playlist.getPlaylistMusics();
                if (currentMusics.contains(music)) {
                    currentMusics.remove(music);
                    playlist.setPlaylistMusics(currentMusics);
                } else {
                    return ResponseEntity.status(404).body(null);
                }

                Playlist updatedPlaylist = playlistService.savePlaylist(playlist);
                return ResponseEntity.ok(updatedPlaylist);
            } else {
                return ResponseEntity.status(403).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
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