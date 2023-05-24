package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControleur {

    @GetMapping("/home")
    public String home() {
        return "This is Home Page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is Admin Page";
    }
}
