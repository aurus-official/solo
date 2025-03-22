package com.leveling.solo.player;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PlayerUserDetails implements UserDetails {

    private final PlayerModel playerModel;

    PlayerUserDetails(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("PLAYER"));
    }

    @Override
    public String getPassword() {
        return playerModel.getPassword();
    }

    @Override
    public String getUsername() {
        return playerModel.getUsername();
    }

}
