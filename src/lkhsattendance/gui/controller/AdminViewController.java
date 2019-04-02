/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.be.Subject;
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
    @FXML
    private Button btnStudentTeacherToggle;
    
    private boolean isCreateStudent = true;
    @FXML
    private VBox vbox;
    
    
    @FXML
    private Label lblCreate;
    @FXML
    private ComboBox<Clss> cbxClasses;
    
    private int n = 0;
    private List<Subject> subjectsWithoutTeacher = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstStudents.getItems().addAll(model.getAllStudentsWithAttendance());
        lstTeachers.getItems().addAll(model.getAllTeachersWithClassesAndSubjects());
        cbxClasses.getItems().addAll(model.getAllClasses());
        for (Subject subject : model.getAllSubjects()) {
            if(subject.getTeacherId() == 0){
                subjectsWithoutTeacher.add(subject);
            }
        }
        
        
    }
    
    @FXML
    private void clickLogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/lkhsattendance/gui/view/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickToggle(ActionEvent event) {
        if(isCreateStudent){
            isCreateStudent = false;
            btnStudentTeacherToggle.setText("Teacher");
            lblCreate.setText("Create Teacher");
            setUpTeacher();
        } else {
            isCreateStudent = true;
            btnStudentTeacherToggle.setText("Student");
            lblCreate.setText("Create Student");
            setUpStudent();
        }
    }
    
    private void setUpTeacher(){
        vbox.getChildren().remove(cbxClasses);
        
        Button btn = new Button();
        btn.setText("Add Subject");
        btn.setOnAction((event) -> {
            addSubject();
        });
        vbox.getChildren().add(btn);
        addSubject();
        
        
    }
    
    private void setUpStudent(){
        vbox.getChildren().remove(5, vbox.getChildren().size());
        vbox.getChildren().add(cbxClasses);
    }
    
    private void addSubject(){
        ComboBox<Subject> temp = new ComboBox();
        temp.setPromptText("Subject");
        temp.getItems().addAll(subjectsWithoutTeacher);
        vbox.getChildren().add(temp);
    }
    
}
