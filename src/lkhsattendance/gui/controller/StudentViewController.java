/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkhsattendance.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class StudentViewController implements Initializable {

    @FXML
    private Text txtWelcome;
    @FXML
    private ListView<?> listView;
    @FXML
    private PieChart pie;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnRequest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtnBack(ActionEvent event) {
    }

    @FXML
    private void btnHandleRequest(ActionEvent event) {
    }
    
}