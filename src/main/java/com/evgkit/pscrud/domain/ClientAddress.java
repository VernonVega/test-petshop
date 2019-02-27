package com.evgkit.pscrud.domain;

/**
 * Created by user on 20.02.19.
 */
public class ClientAddress {
    private Integer clientId;
    private Integer clientAddressId;
    private String clientAddressCity;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getClientAddressId() {
        return clientAddressId;
    }

    public void setClientAddressId(Integer clientAddressId) {
        this.clientAddressId = clientAddressId;
    }

    public String getClientAddressCity() {
        return clientAddressCity;
    }

    public void setClientAddressCity(String clientAddressCity) {
        this.clientAddressCity = clientAddressCity;
    }
}
