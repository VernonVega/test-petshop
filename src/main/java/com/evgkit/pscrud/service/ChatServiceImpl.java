package com.evgkit.pscrud.service;

import com.evgkit.pscrud.domain.Chat;
import com.evgkit.pscrud.dao.ChatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private final ChatDao chatDao;

    public ChatServiceImpl(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @Override
    public List<Chat> findAll() {
        return chatDao.findAll();
    }

    @Override
    public Chat findById(Long id) {
        return chatDao.findById(id);
    }

    @Override
    public boolean add(Chat chat) {
        return chatDao.add(chat);
    }

    @Override
    public boolean update(Chat chat) {
        return chatDao.update(chat);
    }

    @Override
    public boolean delete(Long id) {
        return chatDao.delete(id);
    }
}
