/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package GUI.controller;

import DAO.ColecaoDAO;
import LN.Biblioteca;
import LN.Colecao;
import LN.Media;
import LN.MediaCenter;
import UTILITIES.MediaKey;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerMainPage implements Initializable {

    private static MediaCenter model = MediaCenter.getInstance();

    @FXML
    private Label bemVindo;

    @FXML
    private TableView<Media> tabelaMedias;

    @FXML
    private TableColumn<Media, String> nomeMedia;

    @FXML
    private TableColumn<Media, String> artista;

    @FXML
    private TableColumn<Media, String> categoria;

    @FXML
    private TreeView<String> bibliotecas;

    @FXML
    private ImageView upload;

    @FXML
    private Button logout;

    @FXML
    private ImageView play;

    @FXML
    private ImageView refresh;

    @FXML
    void handleRefreshButton(MouseEvent event) {
        tabelaMedias.getItems().clear();
        populateTabelaGeral();
    }

    @FXML
    void handleUploadButton(MouseEvent event) throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Fazer Upload");
        Image image = new Image("/images/upload.png");
        newWindow.getIcons().add(image);
        Scene start = new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/MediaUpload.fxml")));
        start.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        newWindow.setScene(start);
        newWindow.show();
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {
        Parent loginMenu = FXMLLoader.load(getClass().getResource("/GUI/views/FirstMenu.fxml"));
        Scene login = new Scene(loginMenu);
        login.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();
    }

    @FXML
    void handlePlayButton(MouseEvent event) {
        try {
            Media m = tabelaMedias.getSelectionModel().getSelectedItem();
            MediaKey key = new MediaKey(m.getNomeMedia(), m.getArtista());
            model.reproduzirMedia(key);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void populateColunaCategoria() {
        String email = model.getEmailOn();
        if (email != null) {
            System.out.println("hey " + email);
            categoria.setCellValueFactory(
                    c -> new SimpleStringProperty(c.getValue().getCategoriaPorUtilizador(email)));
        }
    }

    public void populateTabelaGeral() {
        nomeMedia.setCellValueFactory(new PropertyValueFactory<>("nomeMedia"));
        artista.setCellValueFactory(new PropertyValueFactory<>("artista"));

        //populateColunaCategoria();

        Map<MediaKey, Media> map = model.getMediaDAO();
        List<Media> list = new ArrayList<>(map.values());
        ObservableList<Media> l = FXCollections.observableArrayList();
        l.addAll(list);

        tabelaMedias.setItems(l);
    }

    public void populateTabela(String item) {
        nomeMedia.setCellValueFactory(new PropertyValueFactory<>("nomeMedia"));
        artista.setCellValueFactory(new PropertyValueFactory<>("artista"));

        //populateColunaCategoria();

        Map<MediaKey, Media> map = model.getMediaDAO();
        List<Media> list = new ArrayList<>(map.values());
        ObservableList<Media> l = FXCollections.observableArrayList();
        l.addAll(list);

        tabelaMedias.setItems(l);
    }

    public void populateArvore() {
        TreeItem<String> rootitem = new TreeItem<>("Bibliotecas -0");
        rootitem.setExpanded(true);
        List<Biblioteca> bib = (ArrayList<Biblioteca>) model.getBibliotecas().values();
/*
        for (Biblioteca b : bib) {
            TreeItem<String> item = new TreeItem<>(b.getNomeBiblio() + " -" + b.getCod());
            rootitem.getChildren().add(item);
            //List<Colecao> col = new ArrayList<Colecao>(ColecaoDAO.getInstance().getByBiblioteca(b.getCod()));

            for (Colecao c : col) {
                TreeItem<String> node = new TreeItem<>(c.getNomeCol() + " -" + c.getCodCol());
                item.getChildren().add(node);
            }
        }

 */
        try {
            this.bibliotecas.setRoot(rootitem);
            this.bibliotecas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possivel apresentar as bibliotecas.");
            alert.showAndWait();
        }

    }


    @FXML
    private void handleMouseClicked(MouseEvent mouseEvent) {
        String item = bibliotecas.getSelectionModel().getSelectedItem().getValue();
        System.out.println(item);
        //System.out.println("Selected Text : " + item.getValue());
        //tabelaMedias.getItems().clear();
        //populateTabela(item.getValue());
    }

    public void setBemVindo() {
        if (model.eUtilizador()) {
            bemVindo.setText("Bem Vindo/a, " + model.getUtilizadorDAO().get(model.getEmailOn()).getNome() + "!");
        } else bemVindo.setText("Bem Vindo, Convidado!");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setBemVindo();
        populateTabelaGeral();
        //populateArvore();
    }

}
