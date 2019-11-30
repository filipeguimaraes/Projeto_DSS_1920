package GUI.controller;

import LN.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainPage implements Initializable {


    @FXML private TableView<Media> myTable;

    @FXML private TableColumn<Media, Integer> numeroC;

    @FXML private TableColumn<Media, String> NomeC;

    @FXML private TableColumn<Media, Double> duracaoC;

    @FXML private TableColumn<Media, String> AlbumC;

    @FXML private TableColumn<Media, String> CategoriaC;

    @FXML private TreeView<String> bibliotecaCascata;

    @FXML
    private ImageView upload;

    @FXML
    void handleUploadButton(MouseEvent event) {
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        File selectedFile = fc.showOpenDialog(stage);
        System.out.println(selectedFile.getAbsolutePath());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }
}
