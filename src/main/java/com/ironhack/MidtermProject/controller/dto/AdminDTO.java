package com.ironhack.MidtermProject.controller.dto;

import javax.validation.constraints.NotNull;

public class AdminDTO {
    @NotNull(message = "username can not be null")
    private String username;
    @NotNull(message = "password can not be null")
    private String password;
    @NotNull(message = "Role can not be null")
    private String role;

    //CONSTRUCTORS
    public AdminDTO() {
    }

    public AdminDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //GETTERS AND SETTERS
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
