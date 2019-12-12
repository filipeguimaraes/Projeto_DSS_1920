package GUI.controller;

import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCreateAccount {

    private MediaCenter model = MediaCenter.getInstance();

    @FXML
    private Button sair;

    @FXML
    void handleSairButton(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene ca = new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/FirstMenu.fxml")));
        ca.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        window.setScene(ca);
        window.setMaximized(false);
        window.show();
    }

}
