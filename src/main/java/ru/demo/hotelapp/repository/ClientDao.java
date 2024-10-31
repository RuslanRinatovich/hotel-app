package ru.demo.hotelapp.repository;

import ru.demo.hotelapp.model.Client;

public class ClientDao extends BaseDao<Client> {
    public ClientDao() {
        super(Client.class);
    }
}
