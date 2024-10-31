package ru.demo.hotelapp.repository;

import ru.demo.hotelapp.model.Room;

public class RoomDao extends BaseDao<Room> {
    public RoomDao() {
        super(Room.class);
    }
}
