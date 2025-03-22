package com.leveling.solo.player;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PlayerService(PlayerRepository playerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.playerRepository = playerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addUser(PlayerDTO newPlayer) {

        Optional<PlayerModel> playerExisting = playerRepository.getPlayerByUsername(newPlayer.getUsername());

        if (playerExisting.isPresent()) {
            return;
        }

        PlayerModel playerModel = new PlayerModel();
        playerModel.setUsername(newPlayer.getUsername());
        playerModel.setPassword(bCryptPasswordEncoder.encode(newPlayer.getPassword()));
        playerRepository.save(playerModel);
    }
}
