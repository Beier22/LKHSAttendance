/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.bll.IModel;
import lkhsattendance.bll.Model;

/**
 * FXML Controller class
 *
 * @author LKHS
 */
public class TeacherViewController implements Initializable {

    @FXML
    private JFXComboBox<?> menu;
    @FXML
    private JFXComboBox<Clss> pickClass;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXListView<Student> lstStudents;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnMoreInfo;

    private Teacher teacher;
    private Clss selectedClass;
    
    private IModel model = new Model();
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setValue(LocalDate.now());
    }    

    @FXML
    private void handleMenu(ActionEvent event) {
    }

    @FXML
    private void pickDate(ActionEvent event) {
        lstStudents.getItems().clear();
        lstStudents.getItems().addAll(model.getUnattendingStudents(Date.valueOf(datePicker.getValue()), 1));
    }

    @FXML
    private void handleMoreInfo(MouseEvent event) {
    }

    @FXML
    private void handleBtnBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/lkhsattendance/gui/view/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnMore(ActionEvent event) {
    }
    
    public void setUp(Teacher teacher){
        this.teacher = teacher;
        System.out.println("Teacher: " + teacher.getNameF());
        ObservableList<Clss> classes = FXCollections.observableArrayList(model.getTeachingClasses(teacher));
        pickClass.setItems(classes);
        pickClass.getSelectionModel().select(0);
        selectedClass = pickClass.getSelectionModel().getSelectedItem();
        lstStudents.getItems().addAll(model.getUnattendingStudents(Date.valueOf(LocalDate.now()), selectedClass.getId()));
    }

    @FXML
    private void clickPickClass(ActionEvent event) {
        selectedClass = pickClass.getSelectionModel().getSelectedItem();
        lstStudents.getItems().clear();
        lstStudents.getItems().addAll(model.getUnattendingStudents(Date.valueOf(LocalDate.now()), selectedClass.getId()));
    }
    
    
    
}

