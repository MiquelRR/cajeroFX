package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class loginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nif;

    @FXML
    private Label nif_label;

    @FXML
    private Label passwd_label;

    @FXML
    private PasswordField passwd;

    @FXML
    private void hideNifLabel() {
        check();
        nif_label.setText(null);
    }

    @FXML
    private void hidePwLabel() {
        check();
        passwd_label.setText(null);
    }

    @FXML
    void startSession(ActionEvent event) throws IOException  {
        App.setRoot("operations");

    }

    @FXML
    void check() {
        if (nif.getText()==null || nif.getText().equals("")) nif_label.setText("  NIF o NIE");
        else nif_label.setText(null);
        if (passwd.getText()==null || passwd.getText().equals("")) passwd_label.setText("  Clave de acceso");
        else passwd_label.setText(null);

    }

    @FXML
    void initialize() {
        nif_label.setText("  NIF o NIE");
        passwd_label.setText("  Clave de acceso");

    }

}