/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
package GUI.controller;

import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerFirstMenu {

    private static MediaCenter model;

    @FXML
    private Button residente;

    @FXML
    private Button convidado;

    @FXML
    private Button administrador;

    @FXML
    void handleResidenteButton(ActionEvent event) throws IOException {
        model.setPermissaoResidente();
        Parent loginMenu = FXMLLoader.load(getClass().getResource("/GUI/views/Login.fxml"));
        Scene login = new Scene(loginMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();
    }

    @FXML
    void handleAdminButton(ActionEvent event) throws IOException {
        model.setPermissaoAdministrador();
        Parent loginMenu = FXMLLoader.load(getClass().getResource("/GUI/views/Login.fxml"));
        Scene login = new Scene(loginMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();
    }

    @FXML
    void handleConvButton(ActionEvent event) throws IOException {
        model.setPremissaoConvidado();
        Parent loginMenu = FXMLLoader.load(getClass().getResource("/GUI/views/MainPage.fxml"));
        Scene login = new Scene(loginMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.setMaximized(true);
        window.show();
    }

    public static void init(MediaCenter model){
        ControllerFirstMenu.model=model;
    }

}
