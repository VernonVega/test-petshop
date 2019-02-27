package com.evgkit.pscrud.domain;

import java.math.BigDecimal;

public class Client {
    private Integer clientId;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private boolean clientIsWholesale;
    private String prefix;
    private BigDecimal age;
    private String clientCity;

    public Client() {
    }

    public Client(Integer clientId, String clientName, String clientPhone, String clientEmail, boolean clientIsWholesale) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.clientIsWholesale = clientIsWholesale;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public boolean isClientIsWholesale() {
        return clientIsWholesale;
    }

    public void setClientIsWholesale(boolean clientIsWholesale) {
        this.clientIsWholesale = clientIsWholesale;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName=" + clientName +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientIsWholesale='" + clientIsWholesale + '\'' +
                ", clientCity='" + clientCity + '\'' +
                ", age='" + age + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
