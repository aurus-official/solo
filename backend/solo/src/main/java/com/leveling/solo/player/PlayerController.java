package com.leveling.solo.player;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/auth")
class PlayerController {

    private final PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(path = "/logoutredirect")
    ResponseEntity<String> logoutUser(Authentication auth) {
        return ResponseEntity.ok(String.format("Player %s has logged out!", auth.getName()));
    }

    @PostMapping(path = "/register")
    ResponseEntity<String> registerUser(@Valid @RequestBody PlayerDTO newPlayer) {
        playerService.addUser(newPlayer);
        return ResponseEntity.ok(String.format("Player %s has added!", newPlayer.getUsername()));
    }

    @PostMapping(path = "/login")
    ResponseEntity<String> loginUser(Authentication auth) {
        return ResponseEntity.ok(String.format("Player %s has logged in!", auth.getName()));
    }
}
