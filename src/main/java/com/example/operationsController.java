package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.model.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;


public class operationsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> account;

    @FXML
    private Label name;

    @FXML
    private Button payButton;

    @FXML
    private Button takeoutButton;

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void pay(ActionEvent event) {

    }

    @FXML
    void takeout(ActionEvent event) throws IOException {
        Client.setLoggedClient(null);
        App.setRoot("login");

    }

    @FXML
    void initialize() {
        assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'operations.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'operations.fxml'.";
        assert payButton != null : "fx:id=\"payButton\" was not injected: check your FXML file 'operations.fxml'.";
        assert takeoutButton != null : "fx:id=\"takeoutButton\" was not injected: check your FXML file 'operations.fxml'.";

    }

}

