package controller;

import model.Media;
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
        numeroC.setCellValueFactory( new PropertyValueFactory<>("numeroMedia"));
        NomeC.setCellValueFactory( new PropertyValueFactory<>("nomeMedia"));
        duracaoC.setCellValueFactory( new PropertyValueFactory<>("duracaoMedia"));
        AlbumC.setCellValueFactory( new PropertyValueFactory<>("albumMedia"));
        CategoriaC.setCellValueFactory( new PropertyValueFactory<>("categoriaMedia"));
        myTable.setItems(getMedia());

        TreeItem<String> rootItem = new TreeItem<> ("Biblioteca");

        rootItem.setExpanded(true);

        for (int i = 1; i < 3; i++) {
            TreeItem<String> item = new TreeItem<> ("Coleção" + i);
            rootItem.getChildren().add(item);
        }

        bibliotecaCascata.setRoot(rootItem);
    }

    public ObservableList<Media> getMedia(){
        ObservableList<Media> multimedia = FXCollections.observableArrayList();
        Media music1 = new Media(1, "Hello", 3.40,
                "Best Hits", "POP");
        Media music2 = new Media(2, "Maroon 5 - Memories", 2.50,
                "Best Hits", "Rock");
        Media music3 = new Media(3, "Meduza, Becky Hill, Goodboys - Lose Control", 4.00,
                "Best Hits", "Country");
        Media music4 = new Media(4, "Harry Styles - Lights Up", 2.56,
                "Best Hits", "Pop");
        Media music5 = new Media(5, "Fernando Daniel - Se Eu ft. Melim", 2.20,
                "Portugal Hits", "POP");
        multimedia.addAll(music1, music2, music3, music4, music5);
        return multimedia;
    }
}
