package com.vuespring.webChat.dao;

import com.vuespring.webChat.domain.Chat;
import com.vuespring.webChat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {

    @Query("select res.messageText from Chat ch inner join ch.chatMessages res where ch.chatName = :chatName")
   List<String> findAllChatMessages(@Param("chatName") String chatName);

    @Query("select ch.chatId from Chat ch where ch.chatName = :chatName")
    Long findChatId(@Param("chatName") String chatName);

}
