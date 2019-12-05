package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ControllerMediaUpload {

    @FXML
    private TextField nome;

    @FXML
    private TextField categoria;

    @FXML
    private TextField artista;

    @FXML
    private TextField album;

    @FXML
    private Button selecionar;

    @FXML
    void handleSelecionarButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        File selectedFile = fc.showOpenDialog(stage);
        try {
            String path = selectedFile.getPath();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Não selecionou nenhum ficheiro!");
            alert.showAndWait();
        }
    }

}