package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.model.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    void startSession(ActionEvent event) throws IOException {
        Client cli = Client.valideClient(nif.getText(), passwd.getText());
        if (cli != null) {
            Client.setLoggedClient(cli);
            App.setRoot("operations");
        } else {
            Alert alert = new Alert(AlertType.ERROR); // WARNING, ERROR
            alert.setTitle("ERROR DE ACCESO");
            alert.setHeaderText("Nif o clave incorrecta"); // ó null si no queremos cabecera
            alert.setContentText("Error");
            alert.showAndWait();
        }
        
    }

    @FXML
    void check() {
        if (nif.getText() == null || nif.getText().equals(""))
            nif_label.setText("  NIF o NIE");
        else
            nif_label.setText(null);
        if (passwd.getText() == null || passwd.getText().equals(""))
            passwd_label.setText("  Clave de acceso");
        else
            passwd_label.setText(null);

    }

    @FXML
    void initialize() {
        nif_label.setText("  NIF o NIE");
        passwd_label.setText("  Clave de acceso");

    }

}