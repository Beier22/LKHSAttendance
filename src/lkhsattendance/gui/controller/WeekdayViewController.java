/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lkhsattendance.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class WeekdayViewController implements Initializable {
    @FXML private JFXButton btnBack;
    @FXML private Text totalAbsence;
    
    private CategoryAxis weekdayAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    @FXML private BarChart<String, Number> barChart = new BarChart<>(weekdayAxis, yAxis);
  
    private Teacher teacher;
    private Student student;
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    

    public void setUp(Student student) {
        this.student = student;
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        List<Date> absenceDays = student.getDaysAbsence();
        Calendar cal = Calendar.getInstance();
        
        for (Date date : absenceDays) {
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK)==1)
                a++;
            else if (cal.get(Calendar.DAY_OF_WEEK)==2)
                b++;
            else if (cal.get(Calendar.DAY_OF_WEEK)==3)
                c++;
            else if (cal.get(Calendar.DAY_OF_WEEK)==4)
                d++;
            else if (cal.get(Calendar.DAY_OF_WEEK)==5)
                e++;
        }
        System.out.println(absenceDays);
        String abs = a+b+c+d+e+" days of absence";
        totalAbsence.setText(abs);
        
        barChart.setTitle("Summarized attendance over weekdays");
        
        weekdayAxis.setLabel("Weekday");
        weekdayAxis.setCategories(FXCollections.<String>observableArrayList(
            Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")));
        yAxis.setLabel("Number of days");
        
        XYChart.Series series1 = new XYChart.Series<String, Number>();
        series1.setName(student.getNameF() + " " + student.getNameL());
        series1.getData().add(new XYChart.Data("Monday", a));
        series1.getData().add(new XYChart.Data("Tuesday", b));
        series1.getData().add(new XYChart.Data("Wednesday", c));
        series1.getData().add(new XYChart.Data("Thursday", d));
        series1.getData().add(new XYChart.Data("Friday", e));
        barChart.getData().addAll(series1);
        
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(btnBack.getScene());
        stage.show();
    }   
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @FXML
    private void handleBtnBack(ActionEvent event) {
        try {
                Stage stage = (Stage) btnBack.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/StudentView.fxml"));
                stage.setScene(new Scene(loader.load()));
                StudentViewController cont = loader.getController();
                cont.setUp(student, teacher);
        } catch (IOException ex) {
            Logger.getLogger(WeekdayViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
