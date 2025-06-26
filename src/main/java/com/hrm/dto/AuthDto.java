package com.hrm.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDto {
    @NotBlank(message = "username is requred")
    private String username;
    @NotBlank(message = "password  is requred ")
    private String password;
//    @NotBlank(message = "role is requried")
    private String role;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
