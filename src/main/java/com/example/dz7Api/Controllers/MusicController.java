package com.example.dz7Api.Controllers;

import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Models.Category;
import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Models.base.BaseUser;
import com.example.dz7Api.Services.MusicService;
import com.example.dz7Api.dto.MusicCreateDTO;
import com.example.dz7Api.dto.MusicDTO;
import com.example.dz7Api.dto.OwnerDTO;
import com.example.dz7Api.Repositories.CategoryRepository;
import com.example.dz7Api.Repositories.MusicRepository;
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
    private final CategoryRepository categoryRepository;
    private final MusicRepository musicRepository;

    public MusicController(
        MusicService musicService,
        UserRepository userRepository,
        CategoryRepository categoryRepository,
        MusicRepository musicRepository
    ){
        this.musicService = musicService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.musicRepository = musicRepository;
    }

    @GetMapping
    public ResponseEntity<List<MusicDTO>> getAllMusics() {
        List<Music> musics = musicService.findAll();
        if (musics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<MusicDTO> musicDTOs = musics.stream().map(music -> {
            List<OwnerDTO> owners = music.getOwners().stream()
                    .map(owner -> new OwnerDTO(owner.getId(), owner.getUsername(), owner.getRole()))
                    .toList();
            return new MusicDTO(
                music.getIdMusic(),
                music.getMusicName(),
                music.getMusicLink(),
                music.getMusicDuration(),
                music.getMusicGenre(),
                music.getMusicCountry(),
                owners
            );
        }).toList();

        return ResponseEntity.ok(musicDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MusicDTO> getMusicById(@PathVariable Long id) {
        try {
            Music music = musicService.getMusicById(id);
    
            List<OwnerDTO> owners = music.getOwners().stream()
                    .map(owner -> new OwnerDTO(owner.getId(), owner.getUsername(), owner.getRole()))
                    .toList();
    
            MusicDTO musicDTO = new MusicDTO(
                    music.getIdMusic(),
                    music.getMusicName(),
                    music.getMusicLink(),
                    music.getMusicDuration(),
                    music.getMusicGenre(),
                    music.getMusicCountry(),
                    owners
            );
    
            return ResponseEntity.ok(musicDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Music> saveMusic(@RequestBody MusicCreateDTO musicDTO,
                                        @RequestParam Long userId,
                                        @RequestParam String userPassword) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!"Artist".equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Music music = new Music();
        music.setMusicName(musicDTO.getMusicName());
        music.setMusicLink(musicDTO.getMusicLink());
        music.setMusicDuration(musicDTO.getMusicDuration());
        music.setMusicGenre(musicDTO.getMusicGenre());
        music.setMusicCountry(musicDTO.getMusicCountry());

        if (musicDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(musicDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
            music.setCategory(category);
        }

        music.getOwners().add((Artist) user);
        Music savedMusic = musicService.saveMusic(music, (Artist) user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMusic);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@RequestBody MusicCreateDTO musicDTO,
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
            if (existingMusic.getOwners().contains(user) || user.getRole().equals("Admin")) {
                existingMusic.setMusicName(musicDTO.getMusicName());
                existingMusic.setMusicLink(musicDTO.getMusicLink());
                existingMusic.setMusicDuration(musicDTO.getMusicDuration());
                existingMusic.setMusicGenre(musicDTO.getMusicGenre());
                existingMusic.setMusicCountry(musicDTO.getMusicCountry());
    
                if (musicDTO.getCategoryId() != null) {
                    Category category = categoryRepository.findById(musicDTO.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
                    existingMusic.setCategory(category);
                }
    
                Music updatedMusic = musicRepository.save(existingMusic);
                return ResponseEntity.ok(updatedMusic);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(
        @PathVariable Long id,
        @RequestParam Long userId,
        @RequestParam String userPassword
    ) {
        BaseUser user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        if (!user.getUserPassword().equals(userPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            Music music = musicService.getMusicById(id);
            boolean isOwner = music.getOwners().stream()
                .anyMatch(owner -> owner.getId().equals(user.getId()));
            boolean isAdmin = "Admin".equals(user.getRole());
            if (isOwner || isAdmin) {
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
