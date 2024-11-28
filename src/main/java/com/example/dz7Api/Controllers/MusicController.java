package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dz7Api.Models.Music;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/music")
public class MusicController {

    List<Music> musics = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Music>> listMusics (Model model){
        if (musics.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(musics, HttpStatus.OK);
    }

}
