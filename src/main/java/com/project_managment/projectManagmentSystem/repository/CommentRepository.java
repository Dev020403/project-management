package com.project_managment.projectManagmentSystem.repository;

import com.project_managment.projectManagmentSystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByIssueId(Long issueId);
}
