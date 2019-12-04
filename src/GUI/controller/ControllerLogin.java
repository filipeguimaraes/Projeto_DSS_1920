/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
package GUI.controller;

import LN.Exceptions.AdminException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {

    private static MediaCenter model;

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
            int permissao = model.getPermissao();
            if(permissao == 1) {
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/CreateAccount.fxml")))
                );
                window.show();
            } else if (permissao == 2){
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/MainPage.fxml")))
                );
                window.setMaximized(true);
                window.show();
            }

        } catch (UtilizadorException u){
            System.out.println("Deu merda:"+u);
        } catch (AdminException a){
            System.out.println("Deu merdinha:"+a);
        } catch (PermissaoException a){
        System.out.println("mens:"+a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(MediaCenter model){
        ControllerLogin.model=model;
    }


}
