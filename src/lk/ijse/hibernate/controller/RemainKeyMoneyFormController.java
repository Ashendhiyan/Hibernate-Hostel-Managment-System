package lk.ijse.hibernate.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.dto.RemainKeyMnyDTO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.service.ServiceFactory;
import lk.ijse.hibernate.service.ServiceTypes;
import lk.ijse.hibernate.service.custom.ReservationService;
import lk.ijse.hibernate.view.dtm.RemainKeyMoneyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemainKeyMoneyFormController {
    public AnchorPane remainKeyMoney;
    public Label Reservation;
    public TableView<RemainKeyMoneyTM> tblRemainKeyMoney;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStatus;


        ReservationService reservationService= ServiceFactory.getInstance().getService(ServiceTypes.RESERVATION);


        public void initialize() throws SQLException, IOException, ClassNotFoundException {
            loadTableData();
            loadRemainKeyMoneyStudent();
        }

    private void loadTableData(){
       // refreshTable();
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void refreshTable(){
        tblRemainKeyMoney.getItems().clear();
        try {
            ArrayList<RemainKeyMnyDTO> reservationDTOArrayList = reservationService.searchReserveDetails();
            for (RemainKeyMnyDTO r : reservationDTOArrayList) {
                tblRemainKeyMoney.getItems().add(new RemainKeyMoneyTM(
                        r.getStudentID(),
                        r.getStudentName(),
                        r.getStatus()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadRemainKeyMoneyStudent() throws SQLException, IOException, ClassNotFoundException {

        ObservableList<RemainKeyMoneyTM> remainKeyMnyStudent = reservationService.getRemainKeyMnyStudent();
        tblRemainKeyMoney.setItems(remainKeyMnyStudent);

    }


}


