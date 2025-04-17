package com.leveling.solo.player;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PlayerDTO {
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 16, message = "Length should be more than 8 and less than 17 characters")
    private String password;

    @NotEmpty(message = "Confirm password is required")
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @AssertTrue(message = "Passwords aren't matched")
    private boolean isPasswordMatched() {
        return password.compareTo(confirmPassword) == 0;
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

}
