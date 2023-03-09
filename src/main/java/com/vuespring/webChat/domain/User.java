package com.vuespring.webChat.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ch_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    @Column(name="user_name")
    private String userName;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Message> userMessages;
    @ManyToMany
    @JoinTable(
            name = "user_chats",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "chat_id")}
    )
    private List<Chat> userChats;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Message> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<Message> userMessages) {
        this.userMessages = userMessages;
    }

    public List<Chat> getUserChats() {
        return userChats;
    }

    public void setUserChats(List<Chat> userChats) {
        this.userChats = userChats;
    }
}
