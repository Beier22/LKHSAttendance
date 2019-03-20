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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;

/**
 * FXML Controller class
 *
 * @author LKHS
 */
public class TeacherViewController implements Initializable {

    @FXML
    private JFXComboBox<?> pickTeacher;
    @FXML
    private JFXComboBox<?> menu;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXListView<Student> lstStudents;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnMoreInfo;

    private Teacher teacher;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleMenu(ActionEvent event) {
    }

    @FXML
    private void pickDate(ActionEvent event) {
    }

    @FXML
    private void handleMoreInfo(MouseEvent event) {
    }

    @FXML
    private void handleBtnBack(ActionEvent event) {
    }

    @FXML
    private void btnMore(ActionEvent event) {
    }
    
    public void setUp(Teacher teacher){
        this.teacher = teacher;
        System.out.println("Teacher: " + teacher.getNameF());
    }
    
}

