package com.example.dz7Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dz7Api.models.Artist;
import com.example.dz7Api.models.Music;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END " +
           "FROM Artist a JOIN a.musics m " +
           "WHERE m = :music AND a.artistName = :artistName")
    boolean existsByMusicsContainingAndArtistName(@Param("music") Music music,
                                                 @Param("artistName") String artistName);
}


