package lk.ijse.hibernate.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dto.RemainKeyMnyDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.service.SuperService;
import lk.ijse.hibernate.view.dtm.RemainKeyMoneyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationService extends SuperService {
    ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<RemainKeyMnyDTO> searchReserveDetails() throws SQLException, ClassNotFoundException, IOException;

    ObservableList<RemainKeyMoneyTM> getRemainKeyMnyStudent() throws SQLException, ClassNotFoundException, IOException;

}
