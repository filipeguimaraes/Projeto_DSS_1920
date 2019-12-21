/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package GUI.controller;

import LN.Exceptions.AdminException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.MediaCenter;
import ServerClient.ClientStub;
import ServerClient.MediaCenterSignatures;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    private static MediaCenterSignatures model = ClientStub.getInstance();

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    void handleLoginButton(ActionEvent event) {
        try {
            model.iniciarSessao(email.getText(), password.getText());
            if (model.eAdmin()) {
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene admin = new Scene(FXMLLoader.load(getClass()
                        .getResource("/GUI/views/CreateAccount.fxml")));
                admin.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
                window.setScene(admin);
                window.centerOnScreen();
                window.show();
            } else if (model.eUtilizador()) {
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene user = new Scene(FXMLLoader.load(getClass()
                        .getResource("/GUI/views/MainPage.fxml")));
                user.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
                window.setScene(user);
                window.centerOnScreen();
                window.show();
            }
        } catch (UtilizadorException | AdminException | PermissaoException u) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(u.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleVoltarButton(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene start = new Scene(FXMLLoader.load( getClass().getResource("/GUI/views/FirstMenu.fxml") ));
        start.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        stage.setScene(start);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setDefaultButton(true);
    }
}
