package GUI.controller;

import LN.Media;
import LN.MediaCenter;
import UTILITIES.MediaKey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAlterarCategoria {

    private static MediaCenter model = MediaCenter.getInstance();

    private MediaKey mediaKey;

    @FXML
    private TextField novaCategoria;



    @FXML
    void handleAlterarCategoriaButton(ActionEvent event) {
        if (model.eUtilizador()) {
            model.alteraCategoria(novaCategoria.getText(), this.mediaKey);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Não tem permissões para realizar esta operação!");
            alert.showAndWait();
        }
    }

    public void setMediaKey(MediaKey mediaKey) {
        this.mediaKey = mediaKey;
    }
}
