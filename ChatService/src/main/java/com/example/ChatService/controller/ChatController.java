package com.example.ChatService.controller;

import com.example.ChatService.dto.ChatMessage;
import com.example.ChatService.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/chatSendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/private/" + chatMessage.getRecipient(), chatMessage);
    }

    @MessageMapping("/notifyUser")
    public void sendNotificationToUser(@Payload Notification notification) {
        messagingTemplate.convertAndSend("/topic/private/notification/" + notification.getRecipient(), notification);
    }

    @MessageMapping("/notifyAdmin")
    public void sendNotificationToAdmins(@Payload Notification notification) {
        messagingTemplate.convertAndSend("/topic/public/notification", notification);
    }

    @MessageMapping("/chatAddUser")
    @SendTo("/topic/public")
    public  ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("User:" + chatMessage.getSender() + " UserId:" + chatMessage.getContent());
        return chatMessage;
    }

}