/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author mads_
 */
public class LKHSAttendance extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/lkhsattendance/gui/view/LoginView.fxml"));
        
        Scene scene = new Scene(root);
        
        Image icon = new Image("/lkhsattendance/gui/images/Icon.png");
        stage.getIcons().add(icon);
        
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
