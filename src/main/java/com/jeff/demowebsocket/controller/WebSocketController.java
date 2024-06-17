package com.jeff.demowebsocket.controller;

import com.jeff.demowebsocket.controller.vo.MessageVo;
import com.jeff.demowebsocket.controller.vo.ResponseMessageVo;
import com.jeff.demowebsocket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    MessageService messageService;

    @MessageMapping("/message")
//    @SendTo("/topic/messages")
    public void receiveClientMsg(final MessageVo message) throws Exception {
        log.info("Message received: " + message.getContent());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        ResponseMessageVo responseMessageVo = new ResponseMessageVo();
        responseMessageVo.setOrigContent(message.getContent());
        responseMessageVo.setResult("success : " + formattedDate);
        messageService.processAndSend(responseMessageVo);
    }

}