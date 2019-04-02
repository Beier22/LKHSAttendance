/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.gui.model.IModel;
import lkhsattendance.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class AdminViewController implements Initializable {

    @FXML
    private ListView<Teacher> lstTeachers;
    @FXML
    private ListView<Student> lstStudents;
    
    private IModel model = new Model();
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstStudents.getItems().addAll(model.getAllStudentsWithAttendance());
        lstTeachers.getItems().addAll(model.getAllTeachersWithClassesAndSubjects());
    }
    
    @FXML
    private void clickLogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/lkhsattendance/gui/view/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
