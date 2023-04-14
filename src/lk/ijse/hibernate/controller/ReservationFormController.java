package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.service.ServiceFactory;
import lk.ijse.hibernate.service.ServiceTypes;
import lk.ijse.hibernate.service.custom.PurchaseReserveService;
import lk.ijse.hibernate.service.custom.RoomService;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import lk.ijse.hibernate.view.dtm.ReservationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationFormController {
    public AnchorPane reservation;
    public Label Reservation;
    public TextField txtStatus;
    public Label lblDate;
    public JFXButton btnAddToRemain;
    public TextField txtIdSearch;
    public TableView<ReservationTM> tblReservation;
    public TableColumn colReservationID;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TextField txtkeyMoney;
    public TableColumn colStudentQty;
    public TableColumn colKeyMny;
    public TableColumn colStatus;
    public TableColumn colDelete;
    public TextField txtRoomQty;
    public TextField txtStudentName;
    public TextField txtRoomType;
    public TextField txtStudentQty;
    public JFXComboBox cmbRoomID;
    public Label lblReserveID;
    public JFXComboBox cmbStudentID;
    String reservationId;
    int preQty;
    static LocalDate date;


    ArrayList<RoomDTO> allrooms;

    PurchaseReserveService purchaseReserveBO= ServiceFactory.getInstance().getService(ServiceTypes.PERCHASE_RESERVE);

    RoomService roomBO=ServiceFactory.getInstance().getService(ServiceTypes.ROOM);


    public void initialize() {

        RF();
        loadDate();

        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reserveID"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("studentQty"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservation.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                if(tblReservation.getSelectionModel().getSelectedItem()!=null){
                    try {
                        if(purchaseReserveBO.deleteReservation(reservationId)){
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted.....").show();
                            ////ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(lblReserveID.getText());
                            //  Room roomID = reservationDTO.getRoomID();
                            System.out.println("qry room: "+preQty);

                            RoomDTO roomDTO1 = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());

                            roomDTO1.setRoomQty(preQty);

                            roomBO.updateRoom(roomDTO1);

                            tblReservation.getItems().remove(param.getValue());
                            tblReservation.getSelectionModel().clearSelection();
                            clearFields();

                        }else {

                            new Alert(Alert.AlertType.ERROR,"Try Again.....").show(); ;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"Please Select Row....").show(); ;
                }

            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        try {
            loadAllReservation();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadStudentIds();
        loadRoomIds();

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) newValue);
                txtStudentName.setText(studentDTO.getName());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }

    private  void RF(){

        reservationId=generateNewOrderId();
        lblReserveID.setText(reservationId);

    }
    private void clearFields(){
        cmbStudentID.setValue(null);
        txtStudentName.clear();
        cmbRoomID.setValue(null);
        txtRoomType.clear();
        txtkeyMoney.clear();
        txtRoomQty.clear();
        txtStatus.clear();
        txtStudentQty.clear();
    }


    public void txtAddress(KeyEvent keyEvent) {

    }
    public void AddToRemainOnAction(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.REMAINKEYMONEY,reservation);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }
    public void idSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSearch(ActionEvent actionEvent) {
    }

    public void reservationTableClicked(MouseEvent mouseEvent)  throws SQLException, IOException, ClassNotFoundException {
        if (tblReservation.getSelectionModel().getSelectedItem() != null) {
            ReservationTM selectedItem = tblReservation.getSelectionModel().getSelectedItem();
            reservationId = selectedItem.getReserveID();

            ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(reservationId);
            Student student = reservationDTO.getStudentID();
            Room roomID = reservationDTO.getRoomID();
            preQty=roomID.getQty()+selectedItem.getStudentQty();
            cmbStudentID.setValue(student.getStudent_id());
            txtStudentName.setText(student.getName());

            lblReserveID.setText(selectedItem.getReserveID());

            cmbRoomID.setValue(selectedItem.getRoomID());
            txtRoomType.setText(selectedItem.getRoomType());
            txtkeyMoney.setText(String.valueOf(selectedItem.getKeyMoney()));
            txtRoomQty.setText(String.valueOf(roomID.getQty()));

            txtStatus.setText(selectedItem.getStatus());
            txtStudentQty.setText(String.valueOf(selectedItem.getStudentQty()));
        }
    }

    public void updateOnAction(ActionEvent actionEvent)throws Exception  {

        String res_id = lblReserveID.getText();
        LocalDate date = ReservationFormController.date;
        StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getTelNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtkeyMoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());

        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student,room, key_money, status, qty);

        if(purchaseReserveBO.UpdateReservation(reservationDTO)){
            loadAllReservation();


            int b=preQty-Integer.parseInt(txtStudentQty.getText());



            RoomDTO roomDTO1=new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),b);

            roomBO.updateRoom(roomDTO1);


            new Alert(Alert.AlertType.CONFIRMATION,"Updated.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }

    }

    public void ReserveOnAction(ActionEvent actionEvent) throws Exception {

        String res_id = lblReserveID.getText();
        LocalDate date = ReservationFormController.date;
        StudentDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getTelNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtkeyMoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());

        ReservationDTO reservationDTO = new ReservationDTO(res_id, date,student,room, key_money, status, qty);
        if(purchaseReserveBO.purchaseReserveSave(reservationDTO)){
            updateRoomQty((String) cmbRoomID.getValue());
            loadAllReservation();
            RF();
            new Alert(Alert.AlertType.CONFIRMATION,"Saved.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }
    }

    public void loadStudentIds() {
        try {
            cmbStudentID.getItems().addAll(purchaseReserveBO.getStudentIds());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadRoomIds() {
        try {
            cmbRoomID.getItems().addAll(purchaseReserveBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllReservation() throws Exception {
        ObservableList<ReservationTM> observableList = FXCollections.observableArrayList();
        List<ReservationDTO> list = purchaseReserveBO.getAllReservation();

        for (ReservationDTO r : list) {
            String reserveID = r.getRes_id();
            Room room = r.getRoomID();
            String roomID = room.getRoom_type_id();
            String roomType = room.getType();
            int studentQty = r.getQty();
            double keyMoney = r.getKey_money();
            String status = r.getStatus();


            ReservationTM reservationTM = new ReservationTM(reserveID, roomID, roomType, studentQty, keyMoney, status);
            observableList.add(reservationTM);
            tblReservation.setItems(observableList);
        }
    }
    public void updateRoomQty(String id) throws SQLException, IOException, ClassNotFoundException {
        RoomDTO roomDTO = purchaseReserveBO.searchRooms(id);
        int newqty=roomDTO.getRoomQty()-Integer.parseInt(txtStudentQty.getText());

        roomDTO.setRoomQty(newqty);
        roomBO.updateRoom(roomDTO);
    }



    public String generateNewOrderId() {

        try {
            return purchaseReserveBO.generateNewOrderID();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();

        return "R001";
    }

    private void loadDate(){
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        date= LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

}
