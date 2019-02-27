package com.evgkit.pscrud.dao;

import com.evgkit.pscrud.domain.Client;
import com.evgkit.pscrud.domain.ClientAddress;
import com.evgkit.pscrud.domain.ClientFilter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private final SqlSession sqlSession;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public ClientDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Client> findAll(ClientFilter clientFilter) {
        return sqlSession.selectList("selectAllClients", clientFilter);
    }

    @Override
    public boolean add(Client client) {
        sqlSession.insert("addClient", client);
        return true;
    }

    @Override
    public boolean update(Client client) {
        sqlSession.update("updateClient", client);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Client client = new Client();
        sqlSession.delete("deleteClientById", client);
        return true;
    }

    @Override
    public boolean addAddress(ClientAddress clientAddress) {
        sqlSession.insert("addAddress", clientAddress);
        return true;
    }
}
