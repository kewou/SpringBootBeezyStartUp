package org.example.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControleur {

    @PreAuthorize("hasAnyAuthority('ADMIN', 'LOCATAIRE')")
    @GetMapping("/home")
    public String home() {
        return "This is Home Page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is Admin Page";
    }
}
