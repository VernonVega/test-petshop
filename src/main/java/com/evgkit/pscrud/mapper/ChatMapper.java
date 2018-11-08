package com.evgkit.pscrud.mapper;

import com.evgkit.pscrud.domain.Chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<Chat> selectAllChats();

    Chat selectChatById(long id);

    boolean updateChat(Chat chat);

    boolean createChat(Chat chat);

    boolean deleteChatById(long id);
}
