package ru.demo.hotelapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room_status")
public class RoomStatus {


    @Id
    @Column(name = "room_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomStatusId;
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    public Long getRoomStatusId() {
        return roomStatusId;
    }

    public void setRoomStatusId(Long roomStatusId) {
        this.roomStatusId = roomStatusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}