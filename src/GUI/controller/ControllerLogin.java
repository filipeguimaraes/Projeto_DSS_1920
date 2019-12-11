/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package GUI.controller;

import LN.Exceptions.AdminException;
import LN.Exceptions.PasswordException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    private static MediaCenter model = MediaCenter.getInstance();

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    void handleLoginButton(ActionEvent event){
        try {
            model.iniciarSessao(email.getText(), password.getText());
            if(model.eAdmin()) {
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/CreateAccount.fxml")))
                );
                window.show();
            } else if (model.eUtilizador()){
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/MainPage.fxml")))
                );
                window.show();
            }
        } catch (UtilizadorException|AdminException|PermissaoException u){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(u.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setDefaultButton(true);
    }
}
