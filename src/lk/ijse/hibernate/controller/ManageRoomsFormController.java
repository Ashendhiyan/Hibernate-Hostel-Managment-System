package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.service.ServiceFactory;
import lk.ijse.hibernate.service.ServiceTypes;
import lk.ijse.hibernate.service.custom.RoomService;
import lk.ijse.hibernate.util.Service;
import lk.ijse.hibernate.view.dtm.RoomTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageRoomsFormController {
    public AnchorPane manageRooms;
    public TextField txtIdSearch;
    public TextField txtKeyMoney;
    public TextField txtRoomQty;
    public Button btnAddDisable;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colRoomQty;
    public JFXComboBox<String> cmbRoomId;
    public JFXComboBox<String> cmbRoomType;
    public TableView<RoomTm> tblRoom;

    boolean isMatchKeyMoney=false;
    boolean isMatchQty=false;

    RoomService roomService=(RoomService) ServiceFactory.getInstance().getService(ServiceTypes.ROOM);


    public void initialize(){
        cmbRoomId.getItems().addAll("RM-1324","RM-5467","RM-7896","RM-0093");
        cmbRoomType.getItems().addAll("Non-AC","Non-Ac/Food","AC","AC/Food");
        loadTableData();

    }

    private void loadTableData(){
        refreshTable();
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colRoomQty.setCellValueFactory(new PropertyValueFactory<>("roomQty"));
    }

    private void refreshTable(){
        tblRoom.getItems().clear();
        try {
            List<RoomDTO> room = roomService.findRoom();
            for (RoomDTO r : room) {
                tblRoom.getItems().add(new RoomTm(
                r.getRoomID(),
                r.getRoomType(),
                r.getKeyMoney(),
                r.getRoomQty()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }

    private void clearFields(){
        cmbRoomId.setValue(null);
        cmbRoomType.setValue(null);
        txtKeyMoney.clear();
        txtRoomQty.clear();
    }

    public void idSearchOnAction(ActionEvent actionEvent) {
        btnSearch(actionEvent);
    }



    public void txtRoomQty(KeyEvent keyEvent) {
        if (Service.QtyOnHand(txtRoomQty.getText())){
            txtRoomQty.setStyle("-fx-border-color: green");
            isMatchQty=true;
        }else {
            txtRoomQty.setStyle("-fx-border-color: red");
            isMatchQty=false;
        }
    }

    public void txtKeyMoneyOnAction(KeyEvent keyEvent) {
        if (Service.isValidPrice(txtKeyMoney.getText())){
            txtKeyMoney.setStyle("-fx-border-color: green");
            isMatchKeyMoney=true;
        }else {
            txtKeyMoney.setStyle("-fx-border-color: red");
            isMatchKeyMoney=false;
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        String search=txtIdSearch.getText();

        try {
            roomService.searchRoom(search);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        if (!isMatchKeyMoney){
        } else if (!isMatchQty) {
        }else {
            String id = cmbRoomId.getValue();
            String type = cmbRoomType.getValue();
            String key_mny = txtKeyMoney.getText();
            int qty = Integer.valueOf(txtRoomQty.getText());

            RoomDTO dto = new RoomDTO(id, type, key_mny, qty);
            boolean isAdded = false;
            try {
                isAdded = roomService.saveRoom(dto);
                refreshTable();
                tblRoom.refresh();
                clearFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room  Successfully Added..!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong..!");
            }
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        if (!isMatchKeyMoney) {
        } else if (!isMatchQty) {
        } else {
            String id = cmbRoomId.getValue();
            String type = cmbRoomType.getValue();
            String key_mny = txtKeyMoney.getText();
            int qty = Integer.valueOf(txtRoomQty.getText());

            RoomDTO dto = new RoomDTO(id, type, key_mny, qty);
            boolean isAdded = false;
            try {
                isAdded = roomService.updateRoom(dto);
                refreshTable();
                ;
                clearFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room  Successfully Updated..!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong..!");
            }
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        String room_id=tblRoom.getSelectionModel().getSelectedItem().getRoomID();

        try {
            boolean isAdded=roomService.deleteRoom(room_id);
            refreshTable();
            clearFields();
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Room Deleted..!");
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong..!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tblRoomOnMouseClick(MouseEvent mouseEvent) {
        RoomTm selectedItem = tblRoom.getSelectionModel().getSelectedItem();
        if (selectedItem!=null){
            cmbRoomId.setValue(selectedItem.getRoomID());
            cmbRoomType.setValue(selectedItem.getRoomType());
            txtKeyMoney.setText(selectedItem.getKeyMoney());
            txtRoomQty.setText(String.valueOf(selectedItem.getRoomQty()));
            btnAddDisable.setDisable(true);
        }
    }
}
