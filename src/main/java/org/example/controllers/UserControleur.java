package org.example.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.models.Logement;
import org.example.models.User;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserControleur {

    @Autowired
    private UserService userService;

    @Operation(summary = "Tous les users", description = "Tous les users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Erreur de saisie", content = @Content),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error occurred", content = @Content)

    })
    @GetMapping(path = "")
    public List<User> getAllUsers() throws Exception {
        return userService.getAllUser();
    }

    @Operation(summary = "Retourne un user", description = "Retourne un user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Erreur de saisie", content = @Content),
            @ApiResponse(responseCode = "500", description = "An Internal Server Error occurred", content = @Content)

    })
    @GetMapping("/{id}")
    public User getUser(
            @Parameter(description = "id of user")
            @PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @PutMapping(path = "/update/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PatchMapping(path = "/partialUpdate/{id}")
    public User partialUpdateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.partialUpdate(id, user);

    }

    @GetMapping("/{id}/logements")
    public Set<Logement> getAllLogementUser(@PathVariable("id") long id) {
        return userService.getUser(id).getLogements();
    }


}
