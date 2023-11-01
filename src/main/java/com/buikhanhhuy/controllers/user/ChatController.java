package com.buikhanhhuy.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "UserChatController")
public class ChatController {

    @GetMapping(path = "/chat")
    public String chat() {

        return "chat";
    }
}
