package com.evgkit.pscrud.web;

import com.evgkit.pscrud.domain.Client;
import com.evgkit.pscrud.domain.ClientFilter;
import com.evgkit.pscrud.service.ClientService;
import com.evgkit.pscrud.service.ClientServiceImpl;
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
@RequestMapping(value = "/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public boolean create(@RequestBody Client client) {
        try {
            return clientService.add(client);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Client>> findAll() {
        try {
            Map<String, List<Client>> clients = new HashMap<>();
            ClientFilter clientFilter = new ClientFilter();
            clients.put("clients", clientService.findAll(clientFilter));
            return clients;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public boolean delete(@RequestBody Client client) {
        try {
            return clientService.delete(client.getClientId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return  false;
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public boolean update(@RequestBody Client client) {
        try {
            return clientService.update(client);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
