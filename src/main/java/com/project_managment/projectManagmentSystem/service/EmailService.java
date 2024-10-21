package com.project_managment.projectManagmentSystem.service;

import org.springframework.stereotype.Service;

public interface EmailService {
    public void sendEmailwithToken(String userEmail,String link) throws Exception;
}
