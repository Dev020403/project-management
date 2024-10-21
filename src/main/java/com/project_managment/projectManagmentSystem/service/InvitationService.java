package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.Invitation;

public interface InvitationService {
    public void sendInvitation(String email, Long projectId) throws Exception;

    public Invitation acceptInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String email) throws Exception;

    void deleteToken(String token) throws Exception;
}
