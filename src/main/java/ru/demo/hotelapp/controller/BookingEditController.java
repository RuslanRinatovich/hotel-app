package ru.demo.hotelapp.controller;

import javafx.stage.Stage;
import ru.demo.hotelapp.model.Booking;
import ru.demo.hotelapp.repository.BookingDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import ru.demo.hotelapp.model.Client;
import ru.demo.hotelapp.model.Room;
import ru.demo.hotelapp.repository.ClientDao;
import ru.demo.hotelapp.repository.RoomDao;

import static ru.demo.hotelapp.util.Manager.MessageBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookingEditController implements Initializable {

    private final ClientDao clientDao = new ClientDao();

    private final RoomDao roomDao = new RoomDao();
    private final BookingDao bookingDao = new BookingDao();
    @FXML
    private Button BtnCancel;

    @FXML
    private Button BtnSave;

    @FXML
    private ComboBox<Client> CmbClient;

    @FXML
    private ComboBox<Room> CmbRoom;

    @FXML
    private DatePicker DatePickerDateEnd;

    @FXML
    private DatePicker DatePickerDateStart;

    @FXML
    void BtnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) BtnCancel.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void BtnSaveOnAction(ActionEvent event) {
        String error = checkFields().toString();
        if (!error.isEmpty()) {
            MessageBox("Ошибка", "Заполните поля", error, Alert.AlertType.ERROR);
            return;
        }
        try {
            Booking booking = new Booking();
            booking.setClient(CmbClient.getValue());
            booking.setRoom(CmbRoom.getValue());
            booking.setDateStart(DatePickerDateStart.getValue());
            if (DatePickerDateEnd.getValue() != null) {
                booking.setDateEnd(DatePickerDateEnd.getValue());
            }
            bookingDao.save(booking);
            MessageBox("Информация", "", "Данные сохранены успешно", Alert.AlertType.INFORMATION);
            BtnSave.setDisable(true);
        }
        catch (Exception e)
        {
            if (e.getCause() != null && ((SQLException) e.getCause()).getSQLState().equalsIgnoreCase("45000"))
                MessageBox("Ошибка", "", "Данная комната занята", Alert.AlertType.ERROR);
            else
                MessageBox("Ошибка", "", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initController();
    }

    public void initController() {
        CmbClient.setItems(FXCollections.observableArrayList(clientDao.findAll()));
        CmbRoom.setItems(FXCollections.observableArrayList(roomDao.findAll()));
    }

    StringBuilder checkFields() {
        StringBuilder error = new StringBuilder();

        if (CmbRoom.getValue() == null) {
            error.append("Выберите комнату\n");
        }
        if (CmbClient.getValue() == null) {
            error.append("Выберите клиента\n");
        }
        if (DatePickerDateStart.getValue() == null) {
            error.append("Выберите дату\n");
        }

        if (DatePickerDateStart.getValue().isAfter(DatePickerDateEnd.getValue())) {
            error.append("Дата отъезда не может быть раньше даты приезда\n");
        }
        return error;
    }

}
