package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.dao.custom.UserLoginDAO;
import lk.ijse.hibernate.dto.UserLoginDTO;
import lk.ijse.hibernate.service.custom.UserLoginService;
import lk.ijse.hibernate.service.custom.impl.UserLoginServiceImpl;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;
import lk.ijse.hibernate.util.Service;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Struct;

public class LoginFormController {
    public AnchorPane Login;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public CheckBox CheckBoxPassword;
    public TextField txtPasswordHidden;

    boolean isMatchUserId=false;
    boolean isMatchPassword=false;

    UserLoginService userBo=new UserLoginServiceImpl();
    public void txtPassword(KeyEvent keyEvent) {
        if (Service.isValidPassword(txtPassword.getText())) {
            txtPassword.setStyle("-fx-border-color: green");
            isMatchPassword = true;
        } else {
            txtPassword.setStyle("-fx-border-color: red");
            isMatchPassword = false;
        }

    }

    public void txtUserId(KeyEvent keyEvent) {
        if (Service.isValidUserId(txtUserName.getText())) {
            txtUserName.setStyle("-fx-border-color: green");
            isMatchUserId = true;
        } else {
            txtUserName.setStyle("-fx-border-color: red");
            isMatchUserId = false;
        }
    }

    public void LoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        if (!isMatchUserId){
        } else if (!isMatchPassword) {
        }else {
            String name = txtUserName.getText();
            String password = txtPassword.getText();

            UserLoginDTO user = userBo.searchUser(name);

            if (user != null) {
                if (txtPassword.getText().equals(user.getPassword())) {
                    setUI("dashboardForm");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Incorrect Password..!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect User ID!").show();

            }
        }
    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) Login.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void changeVisibility(MouseEvent mouseEvent) {
        if (CheckBoxPassword.isSelected()){
                txtPasswordHidden.setText(txtPassword.getText());
                txtPasswordHidden.setVisible(true);
                txtPassword.setVisible(false);
                return ;
        }
        txtPasswordHidden.setText(txtPassword.getText());
        txtPassword.setVisible(true);
        txtPasswordHidden.setVisible(false);
    }
}
