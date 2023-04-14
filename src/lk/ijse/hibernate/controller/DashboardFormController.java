package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane Dashboard;
    public AnchorPane LoadingPage;

    public void btnLoginDetails(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.LOGINDETAILS,LoadingPage);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }

    public void btnManageRooms(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.MANAGEROOMS,LoadingPage);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }

    public void btnReservation(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.RESERVATION,LoadingPage);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }

    public void btnRemainKeyMoney(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.REMAINKEYMONEY,LoadingPage);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }

    public void btnLogout(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.LOGIN,Dashboard);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }

    public void btnManageStudent(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.MANAGESTUDENTS,LoadingPage);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Ui Not Found..!").show();
        }
    }
}
