package com.vuespring.webChat.service;

import com.vuespring.webChat.dao.ChatRepo;
import com.vuespring.webChat.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatRepo chatRepo;

    @Transactional
    public List<String> findAllChatMessages(String chatName){
        return chatRepo.findAllChatMessages(chatName);
    }

    @Transactional
    public Long findChatId(String chatName){
        return chatRepo.findChatId(chatName);
    }
}
