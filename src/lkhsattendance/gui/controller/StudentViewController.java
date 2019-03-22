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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.bll.IModel;
import lkhsattendance.bll.Model;

/**
 * FXML Controller class
 *
 * @author LKHS
 */
public class StudentViewController implements Initializable {

    @FXML
    private Text txtWelcome;
    @FXML
    private ListView<String> listView;
    @FXML
    private PieChart pie;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnRequest;
    
    private Student student;
    
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
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/lkhsattendance/gui/view/LoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnHandleRequest(ActionEvent event) {
    }

    public void setUp(Student student) {
        this.student = student;
        System.out.println("Student: " + student.getNameF());
        txtWelcome.setText("Welcome, " + student.getNameF());
        List<String> yada = model.getUnattendedDays(student);
        Collections.reverse(yada);
        listView.getItems().addAll(yada);
    }
    
}

