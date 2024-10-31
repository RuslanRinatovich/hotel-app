package ru.demo.hotelapp.repository;

import ru.demo.hotelapp.model.RoomCategory;

public class RoomCategoryDao extends BaseDao<RoomCategory> {
    public RoomCategoryDao() {
        super(RoomCategory.class);
    }
}
