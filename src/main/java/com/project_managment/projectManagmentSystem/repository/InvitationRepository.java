package com.project_managment.projectManagmentSystem.repository;

import com.project_managment.projectManagmentSystem.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);

}
