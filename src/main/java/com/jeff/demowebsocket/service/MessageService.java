package com.jeff.demowebsocket.service;

import com.jeff.demowebsocket.controller.vo.ResponseMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void processAndSend(ResponseMessageVo responseMessageVo) {
        messagingTemplate.convertAndSend("/topic/messages", responseMessageVo);
    }
}