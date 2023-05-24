package com.example.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //todo enable web socket server👍
public class ChatAppConfiguration implements WebSocketMessageBrokerConfigurer// todo configure websocket server
{

public void registerStompEndpoints(StompEndpointRegistry registry)
{
    registry.addEndpoint("/ws").withSockJS();
}
public void configureMessageBroker(MessageBrokerRegistry registry)
{
    registry.setApplicationDestinationPrefixes("/app");// todo routed to message handling method
    registry.enableSimpleBroker("/topic");// todo routed to message broker: broadcasts messages to all the connected clients who are subscribed to a particular topic.
}

}
