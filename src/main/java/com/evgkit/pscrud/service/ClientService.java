package com.evgkit.pscrud.service;

import com.evgkit.pscrud.domain.Client;
import com.evgkit.pscrud.domain.ClientFilter;

import java.util.List;

public interface ClientService {
    List<Client> findAll(ClientFilter clientFilter);

    Client findById(Integer id);

    boolean add(Client client);

    boolean update(Client client);

    boolean delete(Integer id);
}
