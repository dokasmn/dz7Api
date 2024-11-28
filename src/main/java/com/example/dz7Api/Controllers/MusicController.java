package com.example.dz7Api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/music")
public class MusicController {

    @GetMapping
    public String Index(Model model){
        return "music";
    }

}
