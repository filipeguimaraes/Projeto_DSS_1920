/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
package CONTROLLERS;

import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerFirstMenu {

    private static MediaCenter model = MediaCenter.getInstance();

    @FXML
    private Button residente;

    @FXML
    private Button convidado;

    @FXML
    private Button administrador;

    @FXML
    void handleResidenteButton(ActionEvent event) throws IOException {
        model.setPermissaoResidente();
        Scene login = new Scene(FXMLLoader.load(getClass().getResource("/views/Login.fxml")));
        login.getStylesheets().add(getClass().getResource("/sheet.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void handleAdminButton(ActionEvent event) throws IOException {
        model.setPermissaoAdministrador();
        Scene login = new Scene(FXMLLoader.load(getClass().getResource("/views/Login.fxml")));
        login.getStylesheets().add(getClass().getResource("/sheet.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void handleConvButton(ActionEvent event) throws IOException {
        model.setPremissaoConvidado();
        Scene login = new Scene(FXMLLoader.load(getClass().getResource("/views/MainPage.fxml")));
        login.getStylesheets().add(getClass().getResource("/sheet.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.centerOnScreen();
        window.show();
    }

}
