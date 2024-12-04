package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.*;

// models
import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Services.ArtistService;

// jakarta
import jakarta.persistence.EntityNotFoundException;

// java util
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// spring
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    
    List<Artist> artists = new ArrayList<>();

    private final ArtistService artistService;


    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }


    @GetMapping
    public ResponseEntity<List<Artist>> listArtists (Model model) {
        List<Artist> artists = artistService.findAll();
        if (artists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(artists);
    }


    // @GetMapping("/{artistName}")
    // public ResponseEntity<Artist> getArtist(@PathVariable String artistName) {
    //     if (artistName == null || artistName.isEmpty()) {
    //         return ResponseEntity.badRequest().build();
    //     }

    //     Optional<Artist> foundArtist = artists.stream()
    //     .filter(artist -> artist.getArtistName().equalsIgnoreCase(artistName))
    //     .findFirst();

    //     return foundArtist.map(ResponseEntity::ok)
    //                 .orElse(ResponseEntity.notFound().build());
    // }


    @PostMapping
    public ResponseEntity<Artist> saveArtist(@RequestBody Artist artist) {
        try {
            Artist savedArtist = artistService.saveArtist(artist);
            return ResponseEntity.ok(savedArtist);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        try {
            Artist artist = artistService.getArtistById(id);
            return ResponseEntity.ok(artist);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
    
}
