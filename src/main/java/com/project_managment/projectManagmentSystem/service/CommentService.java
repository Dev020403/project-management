package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, Long issueId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId) throws Exception;
}
