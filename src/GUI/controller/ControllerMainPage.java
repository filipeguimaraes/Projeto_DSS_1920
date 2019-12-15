/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package GUI.controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private ListView<String> bibliotecas;

    @FXML
    private ListView<String> colecoes;


    @FXML
    void handleRefreshButton() {
        List<Media> list = new ArrayList<>();
        setTabelaMedia(list);
        setBibliotecas();
    }


    @FXML
    void handleUploadButton() throws IOException {
        if (model.eConvidado()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Para realizar esta operação precisa de ter sessão iniciada!");
            alert.showAndWait();
        } else {
            Stage newWindow = new Stage();
            newWindow.setTitle("Fazer Upload");
            Image image = new Image("/images/upload.png");
            newWindow.getIcons().add(image);
            Scene start = new Scene(FXMLLoader.load(getClass().getResource("/GUI/views/MediaUpload.fxml")));
            start.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
            newWindow.setScene(start);
            newWindow.centerOnScreen();
            newWindow.show();
        }
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {
        model.removePermissao();
        Parent loginMenu = FXMLLoader.load(getClass().getResource("/GUI/views/FirstMenu.fxml"));
        Scene login = new Scene(loginMenu);
        login.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void handlePlayButton() {
        try {
            Media m = tabelaMedias.getSelectionModel().getSelectedItem();
            MediaKey key = new MediaKey(m.getNomeMedia(), m.getArtista());
            model.reproduzirMedia(key);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nenhuma media selecionada!");
            alert.showAndWait();
        }
    }

    public void showMedia(){
        Biblioteca biblioteca = model.getBibliotecas()
                .getByNome(bibliotecas.getSelectionModel().getSelectedItem());
        String nomeColecao = colecoes.getSelectionModel().getSelectedItem();
        Colecao colecao = biblioteca.getColecaoByNome(nomeColecao);
        if(colecao!=null) {
            List<Media> list = new ArrayList<>(colecao.getMedias().values());
            setTabelaMedia(list);
        }

    }


    public void setBemVindo() {
        if (model.eUtilizador()) {
            bemVindo.setText("Bem Vindo/a, " + model.getUtilizadorDAO().get(model.getEmailOn()).getNome() + "!");
        } else bemVindo.setText("Bem Vindo, Convidado!");

    }

    public void setColunaCategoria() {
        String email = model.getEmailOn();
        if (email != null) {
            categoria.setCellValueFactory(
                    c -> new SimpleStringProperty(
                            c.getValue().getCategoriaPorUtilizador(email)));
        }
    }

    public void setTabelaMedia(List<Media> list) {
        tabelaMedias.getItems().clear();
        nomeMedia.setCellValueFactory(new PropertyValueFactory<>("nomeMedia"));
        artista.setCellValueFactory(new PropertyValueFactory<>("artista"));

        setColunaCategoria();
        ObservableList<Media> l = FXCollections.observableArrayList();
        l.addAll(list);
        tabelaMedias.setItems(l);
    }

    public void setBibliotecas() {
        List<String> bib = model.getBibliotecas().values().stream()
                .map(Biblioteca::getNomeBiblio).collect(Collectors.toList());
        ObservableList<String> l = FXCollections.observableArrayList();
        l.addAll(bib);
        bibliotecas.setItems(l);
}

    public void setColecoes() {
        Biblioteca b = model.getBibliotecas().getByNome(bibliotecas.getSelectionModel().getSelectedItem());
        if (b != null) {
            List<String> col = b.getColecoes().values().stream()
                    .map(Colecao::getNomeCol).collect(Collectors.toList());
            ObservableList<String> l = FXCollections.observableArrayList();
            l.addAll(col);
            colecoes.setItems(l);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBemVindo();
        List<Media> list = new ArrayList<>(model.getMediaDAO().values());
        setTabelaMedia(list);
        setBibliotecas();
    }

}
