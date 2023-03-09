package com.vuespring.webChat.controllers;

import com.vuespring.webChat.domain.Message;
import com.vuespring.webChat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GET
    @Path("/{chatName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> findAllChatMessages(@PathParam("chatName") String chatName){
        return chatService.findAllChatMessages(chatName);
    }
    @POST
    @Path("/{chatName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long findChatId(@PathParam("chatName") String chatName){
        return chatService.findChatId(chatName);
    }

}
