package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.Issue;
import com.project_managment.projectManagmentSystem.model.Project;
import com.project_managment.projectManagmentSystem.model.User;
import com.project_managment.projectManagmentSystem.repository.IssueRepository;
import com.project_managment.projectManagmentSystem.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()) {
            return issue.get();
        }
        throw new Exception("No issue found with issueId" + issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issue, User user) throws Exception {
        Project project = projectService.getProjectById(issue.getProjectId());
        Issue newIssue = new Issue();
        newIssue.setTitle(issue.getTitle());
        newIssue.setDescription(issue.getDescription());
        newIssue.setProject(project);
        newIssue.setStatus(issue.getStatus());
        newIssue.setProjectId(issue.getProjectId());
        newIssue.setPriority(issue.getPriority());
        newIssue.setDueDate(issue.getDueDate());
        newIssue.setProject(project);

        return issueRepository.save(newIssue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);
        issue.setAssignee(user);

        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String Status) throws Exception {
        Issue issue = getIssueById(issueId);
        issue.setStatus(Status);
        return issueRepository.save(issue);
    }
}
