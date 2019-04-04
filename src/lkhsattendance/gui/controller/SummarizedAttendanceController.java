/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lkhsattendance.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lkhsattendance.be.Clss;
import lkhsattendance.be.Student;
import lkhsattendance.gui.model.IModel;
import lkhsattendance.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class SummarizedAttendanceController implements Initializable {
    
    @FXML
    private JFXComboBox<Clss> pickClass;
    @FXML
    private JFXListView<String> lstStudents;
    @FXML
    private JFXButton btnMoreInfo;
    @FXML
    private JFXButton logOutButton;
    
    private Clss selectedClass;
    private IModel model = new Model();
    private List<Student> students = new ArrayList();
    private ObservableList<Clss> classOverviewItems = FXCollections.observableArrayList();
    private List<Clss> clss= new ArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clss = model.getAllClasses();
        students = model.getAllStudentsWithAttendance();
        for (Clss clss : clss) {
            classOverviewItems.add(clss);
        }
        pickClass.setItems(classOverviewItems);
        pickClass.getSelectionModel().select(0);
        
        
        
        selectedClass = pickClass.getSelectionModel().getSelectedItem();
        setListView(selectedClass);
    }    

    @FXML
    private void clickPickClass(ActionEvent event) {
        selectedClass = pickClass.getSelectionModel().getSelectedItem();
        setListView(selectedClass);
    }

    private void setListView(Clss selectedClass) {
        
        lstStudents.getItems().clear();
            ObservableList<String> summarizedAttendance = FXCollections.observableArrayList();
            for (Student student : students) {
                if(student.getClassId()==selectedClass.getId()) {
                summarizedAttendance.add(student.getNameF() + " " + student.getNameL() + ", absence: " + student.getDaysAbsence().size() + ", attendance: " + student.getDaysAttendance().size());
                }
            }
        lstStudents.getItems().addAll(summarizedAttendance);
        System.out.println("rhello");
    }



    @FXML
    private void handleLogOut(ActionEvent event) {
        // method to log out to main menu
    }

    @FXML
    private void clickDateOverview(ActionEvent event) {
        //method to go back to standard teacher view
    }


    
}
