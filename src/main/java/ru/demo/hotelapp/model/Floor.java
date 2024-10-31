package ru.demo.hotelapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "floors")
public class Floor {


    @Id
    @Column(name = "floor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long floorId;
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}