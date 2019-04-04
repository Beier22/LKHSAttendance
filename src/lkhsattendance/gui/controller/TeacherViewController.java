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
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import lkhsattendance.gui.model.IModel;
import lkhsattendance.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author LKHS
 */
public class TeacherViewController implements Initializable {
    
    @FXML private JFXComboBox<String> studentOverview;
    @FXML private JFXComboBox<Clss> pickClass;
    @FXML private JFXDatePicker datePicker;
    @FXML private JFXListView<Student> lstStudents;
    @FXML private JFXButton btnBack;
    @FXML private JFXButton btnMoreInfo;

    private Teacher teacher;
    private Clss selectedClass;
    private Date selectedDate;
    private int selectedStudentOverview;
    private ObservableList<String> studentOverviewItems = FXCollections.observableArrayList();
    private ObservableList<Clss> classOverviewItems = FXCollections.observableArrayList();
    private IModel model = new Model();
    private List<Student> students = new ArrayList();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        students = model.getAllStudentsWithAttendance();
        
        datePicker.setValue(LocalDate.now());
        selectedDate = Date.valueOf(datePicker.getValue());
        
        studentOverviewItems.addAll("All students", "Absent students", "Attending students");
        studentOverview.setItems(studentOverviewItems);
        studentOverview.getSelectionModel().select(0);

        btnBack.setText("Log out");
        btnMoreInfo.setText("Summarized overview");
        
        
    }
    public void setUp(Teacher teacher){
        this.teacher = teacher;
        System.out.println("Teacher: " + teacher.getId() + ", name: " + teacher.getNameF() + ", classes teaching: " + teacher.getClassesTeaching() + ", subjects teaching:  " + teacher.getSubjectsTeaching());

        for (Clss clss : teacher.getClassesTeaching()) {
            classOverviewItems.add(clss);
        }
        pickClass.setItems(classOverviewItems);
        pickClass.getSelectionModel().select(0);
        
        
        
        selectedClass = pickClass.getSelectionModel().getSelectedItem();
        
        setListView(selectedDate, selectedClass, selectedStudentOverview);

    }
    @FXML
    private void handleStudentOverview(ActionEvent event) {
        selectedStudentOverview = studentOverview.getSelectionModel().getSelectedIndex();
        setListView(selectedDate, selectedClass, selectedStudentOverview);
    }

    @FXML
    private void pickDate(ActionEvent event) {
        selectedDate = Date.valueOf(datePicker.getValue());
        setListView(selectedDate, selectedClass, selectedStudentOverview);
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
    private void clickPickClass(ActionEvent event) {
        selectedClass = pickClass.getSelectionModel().getSelectedItem();
        setListView(selectedDate, selectedClass, selectedStudentOverview);
    }
    
    private void setListView(Date selectedDate, Clss selectedClass, int selectedStudentOverview){
        lstStudents.getItems().clear();
        if (selectedStudentOverview==0) {
            lstStudents.getItems().clear();
            ObservableList<Student> allStudents = FXCollections.observableArrayList();
            for (Student student : students) {
                if(student.getClassId()==(selectedClass.getId()) && (student.getDaysAttendance().contains(selectedDate) || student.getDaysAbsence().contains(selectedDate)) ) {
                    allStudents.add(student);
                }
            }
            lstStudents.getItems().addAll(allStudents);
        }
        if (selectedStudentOverview==1) {
            lstStudents.getItems().clear();
            ObservableList<Student> absentStudents = FXCollections.observableArrayList();
            for (Student student : students) {
                if(student.getDaysAbsence().contains(selectedDate) && student.getClassId()==selectedClass.getId())  {
                    absentStudents.add(student);
                }
            }
            lstStudents.getItems().addAll(absentStudents);
        }
        if (selectedStudentOverview==2) {
            lstStudents.getItems().clear();
            ObservableList<Student> attendantStudents = FXCollections.observableArrayList();
            for (Student student : students) {
                if(student.getDaysAttendance().contains(selectedDate) && student.getClassId()==selectedClass.getId())  {
                    attendantStudents.add(student);
                }
            }
            lstStudents.getItems().addAll(attendantStudents);
        }
    }

    @FXML
    private void handleMoreInfo(MouseEvent event) throws IOException {
        Student student = lstStudents.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) lstStudents.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/StudentView.fxml"));
        stage.setScene(new Scene(loader.load()));
        StudentViewController cont = loader.getController();
        cont.setUp(student, teacher);
    }

    @FXML
    private void clickSummarized(ActionEvent event) {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/SummarizedAttendance.fxml"));
            stage.setScene(new Scene(loader.load()));
            SummarizedAttendanceController cont = loader.getController();
            cont.setUp(teacher, classOverviewItems);
        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}

