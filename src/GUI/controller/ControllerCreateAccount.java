package GUI.controller;

import LN.MediaCenter;
import ServerClient.ClientStub;
import ServerClient.MediaCenterSignatures;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCreateAccount {

    private static MediaCenterSignatures model = ClientStub.getInstance();


    @FXML
    void handleSairButton(MouseEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene ca = new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/FirstMenu.fxml")));
        ca.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        window.setScene(ca);
        window.centerOnScreen();
        window.show();
    }

}
