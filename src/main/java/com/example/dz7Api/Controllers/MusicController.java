package com.example.dz7Api.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MusicController {

    @GetMapping("/music")
    public String Index(Model model){
        return "helloPage";
    }

}
