import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MediaCenter extends Application {

    public Stage window;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        window.getIcons().add(image);

        window.setScene( new Scene(FXMLLoader.load(getClass().getResource("Views/FirstMenu.fxml")),600,400));

        window.show();
    }
}
