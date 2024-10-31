package ru.demo.hotelapp.repository;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.demo.hotelapp.model.Booking;

import java.sql.SQLException;

import static ru.demo.hotelapp.util.Manager.MessageBox;

public class BookingDao extends BaseDao<Booking> {
    public BookingDao() {
        super(Booking.class);
    }

    @Override
    public void save(Booking entity) {
        Session session = getCurrentSession();
        Transaction tx1 = null;
        try
        {
            tx1 = session.beginTransaction();
            session.persist(entity);
            tx1.commit();
        } catch (Exception e) {

            if (tx1 != null) tx1.rollback();
            throw (e);
        } finally {
            session.close();
        }
    }
}
