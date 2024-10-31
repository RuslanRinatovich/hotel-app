package ru.demo.hotelapp.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.demo.hotelapp.HotelApp;
import ru.demo.hotelapp.model.Booking;
import ru.demo.hotelapp.repository.BookingDao;
import ru.demo.hotelapp.util.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static ru.demo.hotelapp.util.Manager.ShowConfirmPopup;

public class MainController implements Initializable {
    private final BookingDao bookingDao = new BookingDao();
    @FXML
    private Button BtnCreate;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnFilter;

    @FXML
    private Button BtnReset;

    @FXML
    private DatePicker DatePickerEnd;

    @FXML
    private DatePicker DatePickerStart;

    @FXML
    private TableColumn<Booking, String> TableColumnBookingId;

    @FXML
    private TableColumn<Booking, String> TableColumnClient;

    @FXML
    private TableColumn<Booking, String> TableColumnDateEnd;

    @FXML
    private TableColumn<Booking, String> TableColumnDateStart;

    @FXML
    private TableColumn<Booking, String> TableColumnRoomCategoryTitle;

    @FXML
    private TableView<Booking> TableViewBooking;

    @FXML
    void BtnCreateOnAction(ActionEvent event) {

            Stage newWindow = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HotelApp.class.getResource("booking-edit-view.fxml"));

            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                scene.getStylesheets().add("base-styles.css");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newWindow.setTitle("Изменить данные");
            newWindow.initOwner(Manager.secondStage);
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.setScene(scene);
            Manager.currentStage = newWindow;
            newWindow.showAndWait();
            Manager.currentStage = null;
            filterData();
    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        Booking booking = TableViewBooking.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = ShowConfirmPopup();
        if (result.get() == ButtonType.OK) {
            bookingDao.delete(booking);
            filterData();
        }
    }

    @FXML
    void BtnFilterOnAction(ActionEvent event) {
        filterData();
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {
        List<Booking> bookings = bookingDao.findAll();
        TableViewBooking.getItems().clear();
        for (Booking booking : bookings) {
            TableViewBooking.getItems().add(booking);
        }
        TableViewBooking.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    void DatePickerEndOnAction(ActionEvent event) {
        filterData();
    }

    @FXML
    void DatePickerStartOnAction(ActionEvent event) {
        filterData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();
    }

    public void initController() {
        setCellValueFactories();
        filterData();
    }

    void filterData() {

        List<Booking> bookings = bookingDao.findAll();

        if (DatePickerStart.getValue() != null) {
            bookings = bookings.stream().filter(b -> b.getDateStart().isAfter(DatePickerStart.getValue())).collect(Collectors.toList());
        }

        if (DatePickerEnd.getValue() != null) {
            bookings = bookings.stream().filter(b -> b.getDateEnd().isBefore(DatePickerEnd.getValue())).collect(Collectors.toList());
        }

        TableViewBooking.getItems().clear();
        for (Booking booking : bookings) {
            TableViewBooking.getItems().add(booking);
        }
        TableViewBooking.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setCellValueFactories() {
        TableColumnBookingId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookingId().toString()));
        TableColumnDateStart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateStart().toString()));

        TableColumnDateEnd.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDateEnd() != null)
                return new SimpleStringProperty(cellData.getValue().getDateEnd().toString());
            else
                return new SimpleStringProperty("");
        });
        TableColumnClient.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getFIO()));
        TableColumnRoomCategoryTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoom().getRoomCategory().getTitle()));
    }


}
