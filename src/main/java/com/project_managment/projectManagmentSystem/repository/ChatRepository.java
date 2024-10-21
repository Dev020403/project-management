package com.project_managment.projectManagmentSystem.repository;

import com.project_managment.projectManagmentSystem.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {

}