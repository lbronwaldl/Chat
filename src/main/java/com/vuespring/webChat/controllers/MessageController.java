package com.vuespring.webChat.controllers;

import com.vuespring.webChat.dao.MessageRepo;
import com.vuespring.webChat.domain.Message;
import com.vuespring.webChat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @GET
    @Path("/{userMessage}/{chatId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message addMessage(@PathParam("userMessage") String userMessage,@PathParam("chatId") Long chatId,@PathParam("userId") Long userId){

        String mes = messageService.addMessage(userMessage, chatId, userId);
        Message mess = new Message();
        mess.setUser(null);
        mess.setMessageId(null);
        mess.setChat(null);
        mess.setMessageText(mes);
        return mess;
    }

}

