package com.evgkit.pscrud.service;

import com.evgkit.pscrud.domain.Client;
import com.evgkit.pscrud.dao.ClientDao;
import com.evgkit.pscrud.domain.ClientAddress;
import com.evgkit.pscrud.domain.ClientFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Formatter;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    @Override
    public List<Client> findAll(ClientFilter clientFilter) {
        List<Client> clientList = clientDao.findAll(clientFilter);
        clientList.stream().forEach(a -> {
            if (a.getAge().equals(25)) {
                a.setClientName(a.getClientName() + "(dangerous)");
            }
            if (a.getClientCity() == "Москва") {
                a.setClientCity("Msc");
            } else if (a.getClientCity() == "Санкт-Петербург" || a.getClientCity() == "СПБ") {
                a.setClientCity("Spb");
            }

        });
        return clientList;
    }

    @Override
    public Client findById(Integer id) {
        ClientFilter clientFilter = new ClientFilter();
        clientFilter.setClientId(id);
        return this.findAll(clientFilter).get(0);
    }

    @Override
    public boolean add(Client client) {
        if (checkPhone(client.getClientPhone())) {
            clientDao.add(client);
            if (client.getClientCity() != null && !client.getClientCity().equals("")) {
                addClientAddress(client);
            }
        }
        return true;
    }

    @Override
    public boolean update(Client client) {
        Client clientOld = findById(client.getClientId());
        if (clientOld != null) {
            clientDao.update(client);
        } else {
            this.add(client);
        }

        return true;
    }

    @Override
    public boolean delete(Integer id) {
        return clientDao.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void addClientAddress (Client client) {
        try {
            ClientAddress clientAddress = new ClientAddress();
            clientAddress.setClientId(client.getClientId());
            clientAddress.setClientAddressCity(client.getClientCity());
            clientDao.addAddress(clientAddress);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Boolean checkPhone(String phone) {
        if (phone.length() == 10) {
            return true;
        } else {
            return false;
        }
    }
}
