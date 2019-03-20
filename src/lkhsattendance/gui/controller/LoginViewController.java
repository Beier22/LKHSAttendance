/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.bll.IModel;
import lkhsattendance.bll.Model;

/**
 *
 * @author mads_
 */
public class LoginViewController implements Initializable {

    private Label label;

    IModel model = new Model();

    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXToggleButton rememberMe;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Text txt;

    List<Student> students = new ArrayList();
    List<Teacher> teachers = new ArrayList();

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        students = model.getAllStudents();
        teachers = model.getAllTeachers();
    }

    @FXML
    private void clickLogin(ActionEvent event) throws IOException {
        String inputEmail = txtEmail.getText();
        String inputPassword = txtPassword.getText();

        for (Student student : students) {
            if (student.getEmail().equals(inputEmail)) {
                if (student.getPassword().equals(inputPassword)) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();//new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/StudentView.fxml"));
                    stage.setHeight(stage.getHeight());
                    stage.setWidth(stage.getWidth());
                    stage.setMinHeight(600);
                    stage.setMinWidth(800);
                    stage.setScene(new Scene(loader.load()));
                    StudentViewController cont = loader.getController();
                    cont.setUp(student);
                    
                }
            }
        }

        for (Teacher teacher : teachers) {
            if (teacher.getEmail().equals(inputEmail)) {
                if (teacher.getPassword().equals(inputPassword)) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();//new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/TeacherView.fxml"));
                    stage.setHeight(stage.getHeight());
                    stage.setWidth(stage.getWidth());
                    stage.setMinHeight(600);
                    stage.setMinWidth(800);
                    stage.setScene(new Scene(loader.load()));
                    TeacherViewController cont = loader.getController();
                    cont.setUp(teacher);
                    
                }
            }
        }
    }

}
