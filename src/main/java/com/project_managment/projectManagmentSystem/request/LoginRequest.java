package com.project_managment.projectManagmentSystem.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
