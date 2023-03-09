package com.vuespring.webChat.service;

import com.vuespring.webChat.dao.UserRepo;
import com.vuespring.webChat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Transactional
    public User findUserByNameOrCreate(User user){
        if (userRepo.findByUserName(user.getUserName()) != null){
            user.setUserId(userRepo.findByUserName(user.getUserName()).getUserId());
            return user;
        }else {
            return userRepo.save(user);
        }
    }
    @Transactional
    public List<String> findChatsByUserId(Long userId) {
        return userRepo.findChatsByUserId(userId);
    }
}
