package com.example.dz7Api.service;

import org.springframework.stereotype.Service;

import com.example.dz7Api.models.Artist;
import com.example.dz7Api.repository.ArtistRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public Artist saveArtist(Artist artist){
        return artistRepository.save(artist);
    }

    public Artist getArtistById(Long id){
        return artistRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Artist not found"));
    }

    public void deleteArtist(Long id){
        artistRepository.deleteById(id);
    }
}
