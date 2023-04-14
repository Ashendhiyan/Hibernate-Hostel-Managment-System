package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.dao.custom.UserLoginDAO;
import lk.ijse.hibernate.dto.UserLoginDTO;
import lk.ijse.hibernate.service.ServiceFactory;
import lk.ijse.hibernate.service.ServiceTypes;
import lk.ijse.hibernate.service.custom.UserLoginService;
import lk.ijse.hibernate.service.custom.impl.UserLoginServiceImpl;
import lk.ijse.hibernate.util.Service;
import lk.ijse.hibernate.view.dtm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginDetailsFormController {
    public AnchorPane loginDetails;
    public TextField txtIdSearch;
    public Button btnAddDisable;
    public TextField txtUserId;
    public TextField txtPassward;
    public TextField txtUserName;
    public TableView<UserTM> tblLogInDetail;
    public TableColumn colUserID;
    public TableColumn colUserName;
    public TableColumn colPassward;


    boolean isMatchUserId = false;
    boolean isMatchPassword = false;
    boolean isMatchName = false;

    // UserLoginService userBO= ServiceFactory.getInstance().getService(ServiceTypes.USER);

    UserLoginService userBO = new UserLoginServiceImpl();


    public void initialize() {
        loadTableData();
    }


    public void loadTableData() {
        refreshTable();
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassward.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void refreshTable() {
        tblLogInDetail.getItems().clear();
        try {
            List<UserLoginDTO> user = userBO.findUser();
            for (UserLoginDTO u : user) {
                tblLogInDetail.getItems().add(new UserTM(
                        u.getUserID(),
                        u.getUserName(),
                        u.getPassword()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtUserId.clear();
        txtUserName.clear();
        txtPassward.clear();
    }

    public void idSearchOnAction(ActionEvent actionEvent) {
        btnSearch(actionEvent);
    }

    public void txtPasswordOnAction(KeyEvent keyEvent) {
        if (Service.isValidPassword(txtPassward.getText())) {
            txtPassward.setStyle("-fx-border-color: green");
            isMatchPassword = true;
        } else {
            txtPassward.setStyle("-fx-border-color: red");
            isMatchPassword = false;
        }
    }

    public void txtUserNameOnAction(KeyEvent keyEvent) {
        if (Service.isValidName(txtUserName.getText())) {
            txtUserName.setStyle("-fx-border-color: green");
            isMatchName = true;
        } else {
            txtUserName.setStyle("-fx-border-color: red");
            isMatchName = false;
        }
    }

    public void txtUserIdOnAction(KeyEvent keyEvent) {
        if (Service.isValidUserId(txtUserId.getText())) {
            txtUserId.setStyle("-fx-border-color: green");
            isMatchUserId = true;
        } else {
            txtUserId.setStyle("-fx-border-color: red");
            isMatchUserId = false;
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        String search = txtIdSearch.getText();
        try {
            userBO.searchUser(search);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        if (!isMatchUserId) {
        } else if (!isMatchName) {
        } else if (!isMatchPassword) {
        } else {
            String id = txtUserId.getText();
            String name = txtUserName.getText();
            String password = txtPassward.getText();

            UserLoginDTO userLoginDTO = new UserLoginDTO(id, name, password);
            boolean isAdded = false;
            try {
                isAdded = userBO.saveUser(userLoginDTO);
                refreshTable();
                tblLogInDetail.refresh();
                clearFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User  Successfully Added..!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong..!");
            }
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        if (!isMatchUserId) {
        } else if (!isMatchName) {
        } else if (!isMatchPassword) {
        }else {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String password = txtPassward.getText();

        UserLoginDTO userLoginDTO = new UserLoginDTO(id, name, password);
        boolean isAdded = false;
        try {
            isAdded = userBO.updateUser(userLoginDTO);
            refreshTable();
            tblLogInDetail.refresh();
            clearFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "User  Successfully Updated..!");
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong..!");
        }
    }

}

    public void btnDelete(ActionEvent actionEvent) {
            String id=txtUserId.getText();
        try {
            boolean isAdded=userBO.deleteUser(id);
            refreshTable();
            clearFields();
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted..!");
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong..!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tblCustomerOnMouseClick(MouseEvent mouseEvent) {
        UserTM selectedItem = tblLogInDetail.getSelectionModel().getSelectedItem();
        if (selectedItem!=null){
            txtUserId.setText(selectedItem.getUserID());
            txtUserName.setText(selectedItem.getUserName());
            txtPassward.setText(selectedItem.getPassword());
            btnAddDisable.setDisable(true);
        }
    }
}
