package com.dhruv.controller;

import com.dhruv.service.ChatService;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/ask")
    public String getResponse(@RequestBody String question) throws URISyntaxException {
        String x = chatService.chatWithPaLM(question);
        x = x.replaceAll("\\\\n", "\n");
        return x;
    }

}
