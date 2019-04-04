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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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

    @FXML private ListView<Teacher> lstTeachers;
    @FXML private ListView<Student> lstStudents;
    @FXML private Button btnLogout;
    @FXML private Button btnStudentTeacherToggle;
    @FXML private VBox vbox;
    @FXML private Label lblCreate;
    @FXML private TextField inpNameF;
    @FXML private TextField inpNameL;
    @FXML private TextField inpEmail;
    @FXML private Label lblEmail;
    @FXML private TextField inpPassword;
    @FXML private ComboBox<Clss> cbxClass;
    @FXML private Button btnCreate;

    private IModel model = new Model();
    private boolean isCreateStudent = true;
    private int n = 0;
    private List<Subject> subjectsWithoutTeacher = new ArrayList();
    private ComboBox<Subject> cbxFirstSubject = new ComboBox();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstStudents.getItems().addAll(model.getAllStudentsWithAttendance());
        lstTeachers.getItems().addAll(model.getAllTeachersWithClassesAndSubjects());
        cbxClass.getItems().addAll(model.getAllClasses());
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
            btnStudentTeacherToggle.setText("Toggle to Student");
            lblCreate.setText("Create Teacher");
            btnCreate.setText("Add Teacher");
            lblEmail.setText("@easv.dk");
            setUpTeacher();
        } else {
            isCreateStudent = true;
            btnStudentTeacherToggle.setText("Toggle to Teacher");
            lblCreate.setText("Create Student");
            btnCreate.setText("Add Student");
            lblEmail.setText("@easv365.dk");
            setUpStudent();
        }
    }
    
    private void setUpTeacher(){
        vbox.getChildren().remove(cbxClass);
        
        Button btn = new Button();
        btn.setText("Add Subject");
        btn.setOnAction((event) -> {
            addSubject();
        });
        cbxFirstSubject.setPromptText("Subject");
        cbxFirstSubject.getItems().addAll(subjectsWithoutTeacher);
        HBox hbox = new HBox();
        hbox.getChildren().add(cbxFirstSubject);
        vbox.getChildren().addAll(btn, hbox);
        
        
        
    }
    
    private void setUpStudent(){
        vbox.getChildren().remove(5, vbox.getChildren().size());
        vbox.getChildren().add(cbxClass);
    }
    
    private void addSubject(){
        ComboBox<Subject> temp = new ComboBox();
        temp.setPromptText("Subject");
        temp.getItems().addAll(subjectsWithoutTeacher);
        
        Button btn = new Button();
        btn.setText("-");
        btn.setOnAction((event) -> {
            Button btn2 = (Button) event.getSource();
            HBox hb = (HBox) btn2.getParent();
            vbox.getChildren().remove(hb);
        });
        
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(temp, btn);
        
        vbox.getChildren().add(hbox);
    }
    
    private List<Subject> getSelectedSubjects(){
        List<Subject> subjects = new ArrayList();
        for (int i = 6; i < vbox.getChildren().size(); i++) {
            HBox hbox = (HBox) vbox.getChildren().get(i);
            ComboBox cbx = (ComboBox) hbox.getChildren().get(0);
            Subject s = (Subject) cbx.getSelectionModel().getSelectedItem();
            if(s == null)
                continue;
            subjects.add(s);
        }
        System.out.println(subjects);
        
        return subjects;
    }

    @FXML
    private void clickCreate(ActionEvent event) {
        if(isCreateStudent){
            if(inpNameF.getText().isEmpty() || inpNameL.getText().isEmpty() || inpEmail.getText().isEmpty() || cbxClass.getSelectionModel().getSelectedItem() == null){
                System.out.println("Input required fields");
                return;
            }
            Student s = new Student();
            s.setNameF(inpNameF.getText());
            s.setNameL(inpNameL.getText());
            s.setEmail(inpEmail.getText());
            s.setPassword(inpPassword.getText());
            s.setClassId(cbxClass.getSelectionModel().getSelectedItem().getId());
        } else {
            if(inpNameF.getText().isEmpty() || inpNameL.getText().isEmpty() || inpEmail.getText().isEmpty() || cbxFirstSubject.getSelectionModel().getSelectedItem() == null){
                System.out.println("Input required fields");
                return;
            }
            Teacher t = new Teacher();
            t.setNameF(inpNameF.getText());
            t.setNameL(inpNameL.getText());
            t.setEmail(inpEmail.getText());
            t.setPassword(inpPassword.getText());
            t.setSubjectsTeaching(getSelectedSubjects());
            System.out.println(t);
        }
    }
    
    
}
