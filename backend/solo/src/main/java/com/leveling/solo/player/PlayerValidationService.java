package com.leveling.solo.player;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlayerValidationService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    PlayerValidationService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PlayerModel> existingPlayerModel = playerRepository.getPlayerByUsername(username);

        if (existingPlayerModel.isPresent()) {
            return new PlayerUserDetails(existingPlayerModel.get());
        }

        throw new UsernameNotFoundException("FUCKING EXCEPTION!");
    }

}
