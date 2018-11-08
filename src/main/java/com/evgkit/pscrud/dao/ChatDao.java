package com.evgkit.pscrud.dao;

import com.evgkit.pscrud.domain.Chat;

import java.util.List;

public interface ChatDao {
    List<Chat> findAll();

    Chat findById(Long id);

    boolean add(Chat chat);

    boolean update(Chat chat);

    boolean delete(Long id);
}
