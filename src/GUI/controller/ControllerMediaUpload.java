package GUI.controller;

import LN.Exceptions.MediaException;
import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ControllerMediaUpload {

    private static MediaCenter model;

    @FXML
    private TextField nome;

    @FXML
    private TextField categoria;

    @FXML
    private TextField artista;

    @FXML
    private TextField col;

    @FXML
    private Button selecionar;

    @FXML
    void handleSelecionarButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(stage);
        try {
            String path = selectedFile.getPath();
            model.validaFich(path);
            model.upload(path,nome.getText(),col.getText(),artista.getText(),categoria.getText());
        } catch (IOException|MediaException m){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(m.getMessage());
            alert.showAndWait();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não selecionou nenhum ficheiro!");
            alert.showAndWait();
        }
        stage.close();
    }

    public static void init(MediaCenter model){
        ControllerMediaUpload.model=model;
    }
}