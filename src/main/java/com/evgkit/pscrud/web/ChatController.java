package com.evgkit.pscrud.web;

import com.evgkit.pscrud.domain.Chat;
import com.evgkit.pscrud.service.ChatService;
import com.evgkit.pscrud.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/chats")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public boolean create(@RequestBody Chat chat) {
        return chatService.add(chat);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Chat>> findAll() {
        Map<String, List<Chat>> chats = new HashMap<>();
        chats.put("chats", chatService.findAll());
        return chats;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delete(@RequestBody Chat chat) {
        return chatService.delete(chat.getId());
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public boolean update(@RequestBody Chat chat) {
        return chatService.update(chat);
    }
}
