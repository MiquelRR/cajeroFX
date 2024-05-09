package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.model.Client;
import com.example.model.Cuenta;
import com.example.model.Factura;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class operationsController {

    static Cuenta cta;
    static Client cli;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> account;

    @FXML
    private Label name;

    @FXML
    private Button payButton;

    @FXML
    private Button takeoutButton;

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void closeSession(ActionEvent event) throws IOException {
        Client cli = null;
        Client.setLoggedClient(cli);
        App.setRoot("login");

    }

    @FXML
    void pay(ActionEvent event) {
        String res = "";
        boolean ok = true;
        List<String> choices = Factura.getNumByNif(cli.getNif());
        if (choices.size() > 0) {
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("Pagar Factura");
            dialog.setHeaderText("Saldo disponible: " + cta.getSaldoString() + "\n Elija la Factura a pagar :");
            dialog.setContentText("Facturas: ");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                res = result.get();
            }
        }
        Factura fac = Factura.getFactByNum(res);
        if (fac.getImporte() <= cta.getSaldo()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Pagar Factura");
            alert.setHeaderText("Saldo disponible: " + cta.getSaldoString() +
                    "\n La factura núm: " + fac.getNum_fra() +
                    "\n Tiene un importe de: " + fac.getImporteString());

            alert.setContentText("¿desea pagar la factura?");
            ButtonType buttonTypeOne = new ButtonType("PAGAR");
            ButtonType buttonTypeCancel = new ButtonType("NO ME VIENE BIEN", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == buttonTypeOne) {
                    alert = new Alert(AlertType.INFORMATION); // WARNING, ERROR
                    alert.setTitle("Pagar Factura");
                    alert.setHeaderText("Mensaje"); // ó null si no queremos cabecera
                    alert.setContentText("OPERACION REALIZADA\n su nuevo saldo es "+cta.afterTakeOut(fac.getImporte()));
                    alert.showAndWait();
                    Factura.delete(fac.getNum_fra().toString(0));
                } else {
                    System.out.println("ESTO CREO QUENO SE EJECUTA NUNCA");
                }
            }

        } else {            
                    Alert alert = new Alert(AlertType.WARNING); // WARNING, ERROR
                    alert.setTitle("Pagar Factura");
                    alert.setHeaderText("Advertencia"); // ó null si no queremos cabecera
                    alert.setContentText("No tienes saldo para pagar esta factura");
                    alert.showAndWait();
        }
    }

    @FXML
    void accountChange(ActionEvent event) {
        if (account.getValue() != null) {
            takeoutButton.setDisable(false);
            payButton.setDisable(false);
            cta = Cuenta.getCuentaByNum(account.getValue().toString());
        }
    }

    @FXML
    void takeout(ActionEvent event) {
        String res = "";
        boolean ok = true;
        TextInputDialog dialog = new TextInputDialog("100"); // Por defecto
        dialog.setTitle("Sacar Dinero");
        dialog.setHeaderText("Dinero disponible" + cta.getSaldoString());
        dialog.setContentText("Cantidad a retirar:");
        Optional<String> result = dialog.showAndWait(); // Obteniendo el resultado
        if (result.isPresent()) {
            res = result.get();
        }

        int cents = 0;
        try {
            cents = Integer.parseInt(res) * 100;
        } catch (Exception e) {
            cents = 0;
        }
        if (cents == 0) {
            res = "No es una cantidad válida " + res;
            ok = false;
        }
        if (cents > cta.getSaldo()) {
            res = "No tienes suficiente saldo \n te quedan " + cta.getSaldoString();
            ok = false;
        }

        if (cents > 1000 * 100) {
            res = "Excedes el máximo por operación \n" + Cuenta.getMoneyString(1000 * 100) + " al día";
            ok = false;
        }
        if (ok) {

            Alert alert = new Alert(AlertType.WARNING); // WARNING, ERROR
            alert.setTitle("Sacar Dinero");
            alert.setHeaderText("Mensaje"); // ó null si no queremos cabecera
            alert.setContentText("Operacion Realizada,\n su nuevo saldo es :" + cta.afterTakeOut(cents));
            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.WARNING); // WARNING, ERROR
            alert.setTitle("Sacar Dinero");
            alert.setHeaderText("Advertencia"); // ó null si no queremos cabecera
            alert.setContentText(res);
            alert.showAndWait();
        }

    }

    @FXML
    void initialize() {
        cli = Client.getLoggedClient();
        account.getItems().addAll(Cuenta.getClientAccounts(cli.getNif()));
        name.setText(cli.getNombre() + " " + cli.getApellidos());
        takeoutButton.setDisable(true);
        payButton.setDisable(true);
    }

}