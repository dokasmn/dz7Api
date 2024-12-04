package com.example.dz7Api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dz7Api.Models.Artist;
import com.example.dz7Api.Models.Music;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

       @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
              "FROM Artist a JOIN a.musics m " +
              "WHERE m.musicName = :musicTitle AND a.username = :artistName")
       boolean existsByMusicsTitleAndArtistName(String musicTitle, String artistName);
}


