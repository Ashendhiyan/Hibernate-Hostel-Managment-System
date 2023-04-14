package lk.ijse.hibernate.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.view.dtm.RemainKeyMoneyTM;

import java.io.IOException;
import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<Reservation,String> {

    public ObservableList<RemainKeyMoneyTM> getRemainKeyMoney() throws SQLException, ClassNotFoundException, IOException;

    public String generateNewID() throws IOException;
}
