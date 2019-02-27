package com.evgkit.pscrud.mapper;

import com.evgkit.pscrud.domain.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    List<Client> selectAllClients();

    Client selectClientById(Integer id);

    boolean updateClient(Client client);

    boolean createClient(Client client);

    boolean deleteClientById(Integer id);
}
