package lk.ijse.hibernate.service.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dto.RemainKeyMnyDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.service.custom.ReservationService;
import lk.ijse.hibernate.view.dtm.RemainKeyMoneyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    ReservationDAO reservationDAO= DAOFactory.getInstance().getDAO(DAOTypes.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<RemainKeyMnyDTO> searchReserveDetails() throws SQLException, ClassNotFoundException, IOException {
      /*  List<Reservation> all = reservationDAO.findAll();
        ArrayList<RemainKeyMnyDTO> arrayList=new ArrayList<>();
        for (Reservation r : all) {
            arrayList.add(new RemainKeyMnyDTO(
                    r.get

                   *//* r.getRes_id(),
                    r.getDate(),
                    r.getStudent(),
                    r.getRoom(),
                    r.getKey_money(),
                    r.getStatus(),
                    r.getQty()*//*

            ));
        }
        return arrayList;*/
        return null;
    }

    @Override
    public ObservableList<RemainKeyMoneyTM> getRemainKeyMnyStudent() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getRemainKeyMoney();
    }
}
