package com.leveling.solo.player;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlayerDTO {
    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 16, message = "Length should be more than 8 and less than 17 characters")
    private String password;

    @NotNull(message = "Confirmed password is required")
    private String confirmedPassword;

    @AssertTrue(message = "Passwords aren't matched")
    private boolean isPasswordMatched() {
        return password.compareTo(confirmedPassword) == 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

}
