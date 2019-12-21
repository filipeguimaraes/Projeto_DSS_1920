package GUI.controller;

import LN.Media;
import LN.MediaCenter;
import ServerClient.ClientStub;
import ServerClient.MediaCenterSignatures;
import UTILITIES.MediaKey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAlterarCategoria {

    private static MediaCenterSignatures model = ClientStub.getInstance();

    private MediaKey mediaKey;

    @FXML
    private TextField novaCategoria;



    @FXML
    void handleAlterarCategoriaButton(ActionEvent event) {
        try {
            if (model.eUtilizador()) {
                model.alteraCategoria(novaCategoria.getText(), this.mediaKey);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Não tem permissões para realizar esta operação!");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMediaKey(MediaKey mediaKey) {
        this.mediaKey = mediaKey;
    }
}
