package com.example.chatapp.service;

import com.example.chatapp.Model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {
    private static final Logger logger =  LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @EventListener
    public void handleWebSocketEventListener (SessionConnectedEvent sessionConnectedEvent)
    {
        logger.info("receive a new web socket connection");
    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent disconnectEvent)
    {
        StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(disconnectEvent.getMessage());
        String username= (String) stompHeaderAccessor.getSessionAttributes().get("username");
        if (username!=null)
        {
            logger.info("User disconnected"+username);
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);
            messagingTemplate.convertAndSend("topic/public",chatMessage);
        }

    }


}
