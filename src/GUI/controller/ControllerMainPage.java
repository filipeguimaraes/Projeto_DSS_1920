/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package GUI.controller;

import LN.Media;
import LN.MediaCenter;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;
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

    private static MediaCenter model;

    @FXML
    private TableView<Media> tabelaMedias;

    @FXML
    private TableColumn<Media, String> nomeMedia;

    @FXML
    private TableColumn<Utilizador, String> artista;

    @FXML
    private TableColumn<Media, String> categoria;


    @FXML
    private ImageView upload;

    @FXML
    private Button logout;

    @FXML
    private ImageView play;

    @FXML
    void handleUploadButton(MouseEvent event) throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Fazer Upload");
        Image image = new Image("/images/upload.png");
        newWindow.getIcons().add(image);
        Scene start = new Scene(FXMLLoader.load( getClass().getResource("/GUI/views/MediaUpload.fxml") ));
        newWindow.setScene(start);
        newWindow.show();
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {
        Parent loginMenu = FXMLLoader.load(getClass().getResource("/GUI/views/FirstMenu.fxml"));
        Scene login = new Scene(loginMenu);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();
    }

    @FXML
    void handlePlayButton(MouseEvent event) {
        try {
            Media m = tabelaMedias.getSelectionModel().getSelectedItem();
            model.reproduzirMedia(m.getNomeMedia());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void populateTabela(){
        nomeMedia.setCellValueFactory(new PropertyValueFactory<>("nomeMedia"));
        artista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        String emailOn = model.getEmailOn();
        if (emailOn!=null) {
            //categoria.setCellValueFactory(new PropertyValueFactory<>("categorias"));

        }
        Map<MediaKey,Media> map = model.getMediaDAO();
        List<Media> list = new ArrayList<>(map.values());

        ObservableList<Media> l = FXCollections.observableArrayList();
        l.addAll(list);

        tabelaMedias.setItems(l);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populateTabela();
    }

    public static void init(MediaCenter model){
        ControllerMainPage.model=model;
    }
}
