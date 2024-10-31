package ru.demo.hotelapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room_categories")
public class RoomCategory {



    @Id
    @Column(name = "room_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomCategoryId;
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    public Long getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(Long roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}