package com.hrm.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDto {
    @NotBlank(message = "username is requred")
    private String username;
    @NotBlank(message = "password  is requred ")
    private String password;
    private String role;
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
