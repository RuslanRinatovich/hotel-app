module ru.demo.hotelapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires org.hibernate.validator;
    requires org.postgresql.jdbc;
    opens ru.demo.hotelapp to javafx.fxml;
    opens ru.demo.hotelapp.model to org.hibernate.orm.core, javafx.base;
    exports ru.demo.hotelapp;
    exports ru.demo.hotelapp.controller;
    opens ru.demo.hotelapp.controller to javafx.fxml;
    opens ru.demo.hotelapp.util to org.hibernate.orm.core;

}