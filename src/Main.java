import GUI.controller.ControllerFirstMenu;
import GUI.controller.ControllerLogin;
import GUI.controller.ControllerMainPage;
import GUI.controller.ControllerMediaUpload;
import LN.Media;
import LN.MediaCenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);
        Scene start = new Scene(FXMLLoader.load( getClass().getResource("/GUI/views/FirstMenu.fxml") ));
        start.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        stage.setScene(start);
        stage.show();
    }
}
