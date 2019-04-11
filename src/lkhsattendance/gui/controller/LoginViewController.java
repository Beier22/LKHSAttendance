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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lkhsattendance.be.Student;
import lkhsattendance.be.Teacher;
import lkhsattendance.gui.model.IModel;
import lkhsattendance.gui.model.Model;

/**
 * FXML Controller Class
 *
 * @author LKHS
 */
public class LoginViewController implements Initializable {

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

    private Preferences prefs = Preferences.userNodeForPackage(this.getClass());
    private IModel model = new Model();
    private List<Student> students = new ArrayList();
    private List<Teacher> teachers = new ArrayList();
    private LocalDate localDate = LocalDate.now();
    private Date date = java.sql.Date.valueOf(localDate);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        students = model.getAllStudentsWithAttendance();
        teachers = model.getAllTeachersWithClassesAndSubjects();

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

        Date date = Date.valueOf(LocalDate.now());
        model.unattendance(date); //HER ER METODEN SOM SØRGER FOR AT GØR STUDENTS ABSENT

        if (prefs.getBoolean("selected", true)) {
            txtEmail.setText(prefs.get("lastEmail", null));
            txtPassword.setText(prefs.get("lastPassword", null));
            rememberMe.setSelected(true);
        }
    }

    @FXML
    private void clickLogin(ActionEvent event) throws IOException {
        String inputEmail = txtEmail.getText();
        String inputPassword = txtPassword.getText();
        logIn(inputEmail, inputPassword);
    }

    private void setPrefs(String email, String password) {
        if (rememberMe.isSelected()) {
            prefs.put("lastEmail", email);
            prefs.put("lastPassword", password);
            prefs.putBoolean("selected", true);
        } else {
            prefs.putBoolean("selected", false);
        }
    }

    private Object logIn(String user, String password) throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("User not found!");

        if (user.toLowerCase().equals("admin")) {

            if (password == null) {
                return user;
            } else if (password.equals("")) {
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/AdminView.fxml"));
                stage.setScene(new Scene(loader.load()));
                setPrefs(user, password);
                return user;
            } else {
                alert.setContentText("Wrong password!");
                System.out.println("Incorrect password!");
            }
        }

        for (Student student : students) {
            if (student.getEmail().toLowerCase().equals(user.toLowerCase())) {
                if (student.getPassword().equals(password)) {
                    model.login(student.getId(), date); //HER ER LOG IND METODEN KALDET
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/StudentView.fxml"));
                    stage.setScene(new Scene(loader.load()));
                    StudentViewController cont = loader.getController();
                    cont.setUp(student, null);
                    setPrefs(user, password);
                    return student;
                } else if (password == null) {
                    return student;
                } else {
                    alert.setContentText("Wrong password!");
                    System.out.println("Wrong password! (LoginViewController, clickLogin, Student)");
                }
            }
        }

        for (Teacher teacher : teachers) {
            if (teacher.getEmail().toLowerCase().equals(user.toLowerCase())) {
                if (teacher.getPassword().equals(password)) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/lkhsattendance/gui/view/TeacherView.fxml"));
                    stage.setScene(new Scene(loader.load()));
                    TeacherViewController cont = loader.getController();
                    cont.setUp(teacher);
                    setPrefs(user, password);
                    return teacher;
                } else if (password == null) {
                    return teacher;
                } else {
                    alert.setContentText("Wrong password!");
                    System.out.println("Wrong password! (LoginViewController, clickLogin, Teacher)");
                }
            }
        }
        System.out.println("User not found!");
        alert.showAndWait();
        return null;
    }

    @FXML
    private void clickForgot(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password");
        alert.setHeaderText(null);

        Object user = logIn(txtEmail.getText(), null);
        if(user == null)
            return;

        if (user.getClass() == Student.class) {
            Student student = (Student) user;
            alert.setContentText("The password for " + student.getEmail() + " is:\n" + student.getPassword());
        } else if (user.getClass() == Teacher.class) {
            Teacher teacher = (Teacher) user;
            alert.setContentText("The password for " + teacher.getEmail() + " is:\n" + teacher.getPassword());
        } else if (user.getClass() == String.class){
            alert.setContentText("Admin does not have a password");
        }

        alert.showAndWait();
    }

}
