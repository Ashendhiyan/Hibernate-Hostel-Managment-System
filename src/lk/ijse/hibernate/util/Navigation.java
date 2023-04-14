package lk.ijse.hibernate.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Navigation {

    private static AnchorPane anchorPane;

    public static void navigate(Routes routes,AnchorPane anchorPane) throws IOException ,NullPointerException{
        Navigation.anchorPane=anchorPane;
        Navigation.anchorPane.getChildren().clear();
        Stage window = (Stage) Navigation.anchorPane.getScene().getWindow();

        switch (routes){

            case DASHBOARD:
                    window.setTitle("Dashboard");
                    window.setResizable(false);
                    initUI("dashboardForm.fxml");
                    break;

            case LOGINDETAILS:
                    window.setTitle("Login Details");
                    window.setResizable(false);
                    initUI("loginDetailsForm.fxml");
                    break;

            case LOGIN:
                    window.setTitle("Login page");
                    window.setResizable(false);
                    initUI("LoginForm.fxml");
                    break;

            case MANAGEROOMS:
                    window.setTitle("Manage Rooms");
                    window.setResizable(false);
                    initUI("manageRoomsForm.fxml");
                    break;

            case MANAGESTUDENTS:
                    window.setTitle("Manage students");
                    window.setResizable(false);
                    initUI("manageStudentForm.fxml");
                    break;

            case REMAINKEYMONEY:
                    window.setTitle("Remain Key Money");
                    window.setResizable(false);
                    initUI("remainKeyMoneyForm.fxml");
                    break;

            case RESERVATION:
                    window.setTitle("Reservation");
                    window.setResizable(false);
                    initUI("reservationForm.fxml");
                    break;

            default:
                new Alert(Alert.AlertType.ERROR,"Ui not found..!").show();

        }

    }

    public static void initUI(String location) throws IOException,NullPointerException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("../view/" + location)));
    }

}
