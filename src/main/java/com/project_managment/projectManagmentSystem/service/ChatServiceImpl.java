package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.Chat;
import com.project_managment.projectManagmentSystem.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(Chat chat) throws Exception {
        return chatRepository.save(chat);
    }
}
