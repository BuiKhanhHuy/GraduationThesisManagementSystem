package com.buikhanhhuy.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "AdminChatController")
@RequestMapping(path = "/admin")
public class ChatController {

    @GetMapping(path = "/chats")
    public String chats(){

        return "adminChat";
    }
}
