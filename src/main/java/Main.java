import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);
        Scene start = new Scene(FXMLLoader.load(getClass().getResource("/views/FirstMenu.fxml")));
        start.getStylesheets().add(getClass().getResource("/sheet.css").toExternalForm());
        stage.setScene(start);
        stage.centerOnScreen();
        stage.show();
    }
}
