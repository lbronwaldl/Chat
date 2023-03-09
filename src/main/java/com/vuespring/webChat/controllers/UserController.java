package com.vuespring.webChat.controllers;

import com.vuespring.webChat.domain.User;
import com.vuespring.webChat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User findUserByNameOrCreate(User user){
      return userService.findUserByNameOrCreate(user);
    }

    @GET
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> findChatsByUserId(@PathParam("userId") Long userId){
        return userService.findChatsByUserId(userId);
    }
}
