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
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.bll.IModel;
import lkhsattendance.bll.Model;

/**
 * FXML Controller Class
 *
 * @author LKHS
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        students = model.getAllStudents();
        teachers = model.getAllTeachers();

        //Pressing enter while in username field will swap to password field
        txtEmail.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    txtPassword.requestFocus();
                }
            }

        });

        //Pressing enter while in password field will press login button
        txtPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnLogin.fire();
                }
            }
        });
        
    }

    @FXML
    private void clickLogin(ActionEvent event) throws IOException {
        String inputEmail = txtEmail.getText();
        String inputPassword = txtPassword.getText();

        for (Student student : students) {
            if (student.getEmail().toLowerCase().equals(inputEmail.toLowerCase())) {
                if (student.getPassword().equals(inputPassword)) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/StudentView.fxml"));
                    stage.setScene(new Scene(loader.load()));
                    StudentViewController cont = loader.getController();
                    cont.setUp(student);
                    return;
                } else {
                    System.out.println("Wrong password! (LoginViewController, clickLogin, Student)");
                    return;
                }
            }
        }

        for (Teacher teacher : teachers) {
            if (teacher.getEmail().toLowerCase().equals(inputEmail.toLowerCase())) {
                if (teacher.getPassword().equals(inputPassword)) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/TeacherView.fxml"));
                    stage.setScene(new Scene(loader.load()));
                    TeacherViewController cont = loader.getController();
                    cont.setUp(teacher);
                    return;
                } else {
                    System.out.println("Wrong password! (LoginViewController, clickLogin, Teacher)");
                    return;
                }
            }
        }
        System.out.println("User not found! (LoginViewController, clickLogin)");
    }

}
