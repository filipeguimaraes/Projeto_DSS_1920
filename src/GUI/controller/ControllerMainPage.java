package GUI.controller;

import LN.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }
}
