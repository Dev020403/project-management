package com.project_managment.projectManagmentSystem.controller;

import com.project_managment.projectManagmentSystem.model.Chat;
import com.project_managment.projectManagmentSystem.model.Message;
import com.project_managment.projectManagmentSystem.model.User;
import com.project_managment.projectManagmentSystem.request.CreateMessageRequest;
import com.project_managment.projectManagmentSystem.service.MessageService;
import com.project_managment.projectManagmentSystem.service.ProjectService;
import com.project_managment.projectManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest req) throws Exception {
        if (req.getSenderId() == null || req.getProjectId() == null) {
            throw new Exception("Sender ID and Project ID must not be null");
        }
        User user = userService.findUserById(req.getSenderId());
        Chat chat = projectService.getProjectById(req.getProjectId()).getChat();
        if (chat == null) {
            throw new Exception("Chat not found");
        }
        Message sendMessage = messageService.sendMessage(req.getSenderId(), req.getProjectId(), req.getContent());
        return ResponseEntity.ok(sendMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
