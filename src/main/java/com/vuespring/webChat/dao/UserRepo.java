package com.vuespring.webChat.dao;

import com.vuespring.webChat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Autowired
    User findByUserName(String userName);

    @Query("select n.chatName from User u inner join u.userChats n where u.userId = :userId ")
    List<String> findChatsByUserId(@Param("userId") Long userId);

}
