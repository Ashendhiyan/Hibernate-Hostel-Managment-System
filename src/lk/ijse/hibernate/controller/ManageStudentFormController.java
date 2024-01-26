package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.service.ServiceFactory;
import lk.ijse.hibernate.service.ServiceTypes;
import lk.ijse.hibernate.service.custom.StudentService;
import lk.ijse.hibernate.service.custom.impl.StudentServiceImpl;
import lk.ijse.hibernate.util.Service;
import lk.ijse.hibernate.view.dtm.StudentTM;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ManageStudentFormController {

    StudentService studentService= (StudentService)ServiceFactory.getInstance().getService(ServiceTypes.STUDENT);
    public AnchorPane ManageStudent;
    public TextField txtIdSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtTelNo;
    public TextField txtAddress;
    public Button btnAddDisable;
    public TableColumn<Student,String> colStudentId;
    public TableColumn<Student,String> colName;
    public TableColumn<Student,String> colAddress;
    public TableColumn<Student,String> colTelNo;
    public TableColumn<Student,String> colDOB;
    public TableColumn<Student,String> colGender;
    public JFXComboBox<String> cmbGender;
    public TableView<StudentTM> tblStudent;
    public JFXDatePicker cmbDOB;

    boolean isMatchStudentID=false;
    boolean isMatchName=false;
    boolean isMatchAddress=false;
    boolean isMatchTelNo=false;




    public void initialize(){
       cmbGender.getItems().addAll("Male","Female");
       loadTableData();

    }

    private void loadTableData(){
        refreshTable();
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void refreshTable(){
        tblStudent.getItems().clear();
        try {
            List<StudentDTO> allStudents = studentService.findStudent();
            for (StudentDTO s : allStudents) {
                tblStudent.getItems().add(new StudentTM(
                s.getStudentId(),
                s.getName(),
                s.getAddress(),
                s.getTelNo(),
                s.getDob(),
                s.getGender()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
      /*  try {
            List<StudentDTO> studentDTOList = studentService.findStudent();
            ObservableList<StudentTM> objectObservableList = FXCollections.observableArrayList();
            objectObservableList.addAll((StudentTM) studentDTOList);
            tblStudent.setItems(objectObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    private void clearFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTelNo.clear();
        cmbDOB.setValue(null);
        cmbGender.setValue(null);
    }
    public void idSearchOnAction(ActionEvent actionEvent) {
        btnSearch(actionEvent);
    }

    public void txtIdOnAction(KeyEvent keyEvent) {
        if (Service.isValidStudentId(txtId.getText())){
            txtId.setStyle("-fx-border-color: green");
            isMatchStudentID=true;
        }else {
            txtId.setStyle("-fx-border-color: red");
            isMatchStudentID=false;
        }

    }

    public void txtNameOnAction(KeyEvent keyEvent) {
        if (Service.isValidName(txtName.getText())){
            txtName.setStyle("-fx-border-color: green");
            isMatchName=true;
        }else {
            txtName.setStyle("-fx-border-color: red");
            isMatchName=false;
        }
    }

    public void txtTelNoOnAction(KeyEvent keyEvent) {
        if (Service.isValidTelephoneNumber(txtTelNo.getText())){
            txtTelNo.setStyle("-fx-border-color: green");
            isMatchTelNo=true;
        }else {
            txtTelNo.setStyle("-fx-border-color: red");
            isMatchTelNo=false;
        }
    }

    public void txtAddress(KeyEvent keyEvent) {
        if (Service.isValidName(txtAddress.getText())){
            txtAddress.setStyle("-fx-border-color: green");
            isMatchAddress=true;
        }else {
            txtAddress.setStyle("-fx-border-color: red");
            isMatchAddress=false;
        }
    }

    public void btnSearch(ActionEvent actionEvent) {
        String search=txtIdSearch.getText();
        try {
            StudentDTO studentDTO = studentService.searchStudent(search);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        if (!isMatchStudentID) {
        } else if (!isMatchName) {
        } else if (!isMatchAddress) {
        } else if (!isMatchTelNo) {
        } else {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String telNo = txtTelNo.getText();
            LocalDate dob = cmbDOB.getValue();
            String gender = cmbGender.getValue();

            StudentDTO student = new StudentDTO(id, name, address, telNo, dob, gender);
            boolean isAdded = false;
            try {
                isAdded = studentService.saveStudent(student);
                refreshTable();
                tblStudent.refresh();
                clearFields();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student  Successfully Added..!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong..!");
            }
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        if (!isMatchStudentID) {
        } else if (!isMatchName) {
        } else if (!isMatchAddress) {
        } else if (!isMatchTelNo) {
        } else {
            String id = txtId.getText();
            String name = txtName.getText();
            String telNo = txtTelNo.getText();
            String address = txtAddress.getText();
            LocalDate dob = cmbDOB.getValue();
            String gender = cmbGender.getValue();

            StudentDTO student = new StudentDTO(id, name, address, telNo, dob, gender);
            boolean isAdded = false;
            try {
                isAdded = studentService.updateStudent(student);
                refreshTable();
                clearFields();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student  Successfully Updated..!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong..!");
            }
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        String student_id=txtId.getText();

        try {
            boolean isAdded=studentService.deleteStudent(student_id);
            refreshTable();
            clearFields();
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Student Deleted..!");
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong..!");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tblCustomerOnMouseClick(MouseEvent mouseEvent) {
        StudentTM studentDTO=tblStudent.getSelectionModel().getSelectedItem();
        if (studentDTO!=null){
            txtId.setText(studentDTO.getStudentId());
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtTelNo.setText(studentDTO.getTelNo());
            cmbDOB.setValue(studentDTO.getDob());
            cmbGender.setValue(studentDTO.getGender());
            btnAddDisable.setDisable(true);
        }
    }
}
