package GUI.controller;



import LN.Media;
import LN.MediaCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainPage implements Initializable {

    private static MediaCenter model;

    @FXML private TableView<Media> myTable;

    @FXML private TableColumn<Media, Integer> numeroC;

    @FXML private TableColumn<Media, String> NomeC;

    @FXML private TableColumn<Media, Double> duracaoC;

    @FXML private TableColumn<Media, String> AlbumC;

    @FXML private TableColumn<Media, String> CategoriaC;

    @FXML private TreeView<String> bibliotecaCascata;

    @FXML private ImageView upload;

    @FXML private Button logout;

    @FXML private ImageView play;

    @FXML
    void handleUploadButton(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        File selectedFile = fc.showOpenDialog(stage);
        try {
            System.out.println(selectedFile.getPath());
            } catch (Exception e){
                Parent error = FXMLLoader.load(getClass().getResource("/GUI/views/ErrorBox.fxml"));
                Scene errorScene = new Scene(error);
                Stage newWindow = new Stage();
                Image image = new Image("/images/error.png");
                newWindow.getIcons().add(image);
                newWindow.setTitle("Não selecionou nenhum ficheiro!");
                newWindow.setScene(errorScene);
                newWindow.show();
            }
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
        model.reproduzirMedia("Hello");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }

    public static void init(MediaCenter model){
        ControllerMainPage.model=model;
    }
}
