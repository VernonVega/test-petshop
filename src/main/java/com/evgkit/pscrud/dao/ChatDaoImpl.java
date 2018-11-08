package com.evgkit.pscrud.dao;

import com.evgkit.pscrud.domain.Chat;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatDaoImpl implements ChatDao {
    @Autowired
    private final SqlSession sqlSession;

    public ChatDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Chat> findAll() {
        return sqlSession.selectList("selectAllChats");
    }

    @Override
    public Chat findById(Long id) {
        return sqlSession.selectOne("selectChatById", id);
    }

    @Override
    public boolean add(Chat chat) {
        sqlSession.insert("addChat", chat);
        return true;
    }

    @Override
    public boolean update(Chat chat) {
        sqlSession.update("updateChat", chat);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        sqlSession.delete("deleteChatById", id);
        return true;
    }
}
