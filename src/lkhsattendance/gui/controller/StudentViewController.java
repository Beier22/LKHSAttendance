/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.gui.model.IModel;
import lkhsattendance.gui.model.Model;

/**
 * FXML Controller class
 *
 * @author LKHS
 */
public class StudentViewController implements Initializable {

    @FXML private Text txtWelcome;
    @FXML private ListView<String> listView;
    @FXML private PieChart pie;
    @FXML private Button btnBack;
    @FXML private Button btnRequest;
    
    private Student student;
    private Teacher teacher;
    private IModel model = new Model();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBack.setText("Log out");
    }    

    @FXML
    private void handleBtnBack(ActionEvent event) throws IOException {
        if(teacher == null) {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/lkhsattendance/gui/view/LoginView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/TeacherView.fxml"));
            stage.setScene(new Scene(loader.load()));
            TeacherViewController cont = loader.getController();
            cont.setUp(teacher);
        }
    }

    @FXML
    private void btnHandleRequest(ActionEvent event) {
        
    }
    
    /*
    public void setUp(Student student) {
        this.student = student;
        System.out.println("Student: " + student.getNameF());
        txtWelcome.setText("Welcome, " + student.getNameF());
        List<String> yada = model.getUnattendedDays(student);
        Collections.reverse(yada);
        listView.getItems().addAll(yada);
    }
    */
    
    public void setUp(Student student, Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
        System.out.println("Student: " + student.getNameF());
        txtWelcome.setText("Welcome, " + student.getNameF());
        List<Date> absentDays = this.student.getDaysAbsence();
        List<String> toString = new ArrayList();
        Calendar cal = Calendar.getInstance();
        for (Date date : absentDays) {
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK)==6 || cal.get(Calendar.DAY_OF_WEEK)==7)
            {
                //
            }
            else
            {
                String stringDate = date+"";
                toString.add(stringDate);
            }
        }
        listView.getItems().addAll(toString);
        ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                    new PieChart.Data("Attendance", student.getDaysAttendance().size()),
                    new PieChart.Data("Absence", toString.size())
                );
            pie.setData(pieChartData);
        
        if(teacher != null)
            btnBack.setText("Back");
    }

    @FXML
    private void clickWeekly(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/WeekdayView.fxml"));
        stage.setScene(new Scene(loader.load()));
        WeekdayViewController cont = loader.getController();
        cont.setUp(student);
        cont.setTeacher(teacher);
    }
    
    
    
    
}

