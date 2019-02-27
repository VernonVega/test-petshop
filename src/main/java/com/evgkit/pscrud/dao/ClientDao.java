package com.evgkit.pscrud.dao;

import com.evgkit.pscrud.domain.Client;
import com.evgkit.pscrud.domain.ClientAddress;
import com.evgkit.pscrud.domain.ClientFilter;

import java.util.List;

public interface ClientDao {
    List<Client> findAll(ClientFilter clientFilter);

    boolean add(Client client);

    boolean update(Client client);

    boolean delete(Integer id);

    boolean addAddress(ClientAddress clientAddress);
}
