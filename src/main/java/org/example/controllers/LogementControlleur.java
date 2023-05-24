package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.models.Logement;
import org.example.services.LogementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logements")
public class LogementControlleur {

    @Autowired
    private LogementService logementService;

    @GetMapping()
    @Operation(description = "Get list of all users")
    public List<Logement> getAll() throws Exception {
        return logementService.getAllLogement();
    }

    @PostMapping(path = "/add")
    public Logement postLogement(@RequestBody Logement lgt) {
        return logementService.addOrUpdate(lgt);
    }
}
