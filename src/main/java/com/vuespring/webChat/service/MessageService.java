package com.vuespring.webChat.service;

import com.vuespring.webChat.dao.ChatRepo;
import com.vuespring.webChat.dao.MessageRepo;
import com.vuespring.webChat.dao.UserRepo;
import com.vuespring.webChat.domain.Chat;
import com.vuespring.webChat.domain.Message;
import com.vuespring.webChat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public String addMessage(String userMessage, Long chatId, Long userId){
        Message newMessage = new Message();
        newMessage.setMessageText(userMessage);
        Chat chat = chatRepo.findById(chatId).get();
        newMessage.setChat(chat);
        User user = userRepo.findById(userId).get();
        newMessage.setUser(user);
        messageRepo.save(newMessage);
        return userMessage;

    }

}
