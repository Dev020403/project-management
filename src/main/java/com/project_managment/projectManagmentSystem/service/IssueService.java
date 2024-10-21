package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.Issue;
import com.project_managment.projectManagmentSystem.model.User;
import com.project_managment.projectManagmentSystem.request.IssueRequest;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String Status) throws Exception;

}