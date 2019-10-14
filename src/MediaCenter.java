import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MediaCenter extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);

        Scene FirstMenu = new Scene(FXMLLoader.load(getClass().getResource("Views/FirstMenu.fxml")),600,400);
        Scene Login = new Scene(FXMLLoader.load(getClass().getResource("Views/Login.fxml")),600,400);
        Scene AdminLogin = new Scene(FXMLLoader.load(getClass().getResource("Views/AdminLogin.fxml")),600,400);
        Scene ErrorBox = new Scene(FXMLLoader.load(getClass().getResource("Views/ErrorBox.fxml")),400,200);
        Scene MainPage = new Scene(FXMLLoader.load(getClass().getResource("Views/MainPage.fxml")));


        stage.setScene(MainPage);
        if(stage.getScene().equals(MainPage)) stage.setMaximized(true);
        stage.show();
    }
}
