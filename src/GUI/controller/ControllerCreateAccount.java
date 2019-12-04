package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCreateAccount {
    @FXML
    private Button sair;

    @FXML
    void handleSairButton(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/FirstMenu.fxml")))
        );
        window.setMaximized(false);
        window.show();
    }

}
