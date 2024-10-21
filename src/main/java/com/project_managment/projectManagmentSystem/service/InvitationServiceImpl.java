package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.Invitation;
import com.project_managment.projectManagmentSystem.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws Exception {
        String invitationToken = UUID.randomUUID().toString();
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);
        invitationRepository.save(invitation);

        String invitationLink = "http://localhost:3000/accept_invitation?token=" + invitationToken;
        emailService.sendEmailwithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if (invitation == null) {
            throw new Exception("Invalid Invitation");
        } else {
            invitation.setToken(null);
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String email) throws Exception {
        Invitation invitation = invitationRepository.findByEmail(email);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) throws Exception {
        Invitation Invitation = invitationRepository.findByToken(token);
        invitationRepository.delete(Invitation);
    }
}
