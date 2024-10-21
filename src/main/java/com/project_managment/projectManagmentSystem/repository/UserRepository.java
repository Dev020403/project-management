package com.project_managment.projectManagmentSystem.repository;

import com.project_managment.projectManagmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
