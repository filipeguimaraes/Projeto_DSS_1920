package controller;

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

    @FXML
    private Button residente;

    @FXML
    private Button convidado;

    @FXML
    private Button administrador;

    @FXML
    void handleResidenteButton(ActionEvent event) throws IOException {
        if (event.getSource() == residente) {
            Parent proximoMenu = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
            System.out.println("chegou aqui");
            Scene scene = new Scene(proximoMenu);
            Stage menu = (Stage) ((Node) event.getSource()).getScene().getWindow();
            menu.setScene(scene);
            menu.show();
        } else {
            System.out.println("FUDEU");
        }
    }

}
/*
    @FXML
    private Button residente;
    @FXML
    private Button convidado;
    @FXML
    private Button administrador;


    private void handleResidenteButton(ActionEvent event) {
        Stage stage = new Stage();
        Parent proximoMenu = null;
        try {
            proximoMenu = FXMLLoader.load( getClass().getResource("views/Login.fxml") );
        } catch (IOException e) {
            System.out.println("Deu merda"+ e);
        }
        Scene scene = new Scene(proximoMenu);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

    }


    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent proximoMenu = FXMLLoader.load( getClass().getResource("views/FirstMenu.fxml") );

        if (event.getSource() == residente ){
            stage = (Stage) residente.getScene().getWindow();
            proximoMenu = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        } else if (event.getSource() == convidado){
            stage = (Stage) convidado.getScene().getWindow();
            proximoMenu = FXMLLoader.load(getClass().getResource("views/MainPage.fxml"));
        } else if (event.getSource() == administrador) {
            stage = (Stage) administrador.getScene().getWindow();
            proximoMenu = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        }

        Scene scene = new Scene(proximoMenu);
        stage.setScene(scene);
        stage.setTitle("MediaCenter");
        stage.show();
    }


}


 */