import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);

        Scene FirstMenu = new Scene( FXMLLoader.load( getClass().getResource("Views/FirstMenu.fxml") ) );
        /*
        Scene Login = new Scene(FXMLLoader.load(getClass().getResource("Views/Login.fxml")),600,400);
        Scene ErrorBox = new Scene(FXMLLoader.load(getClass().getResource("Views/ErrorBox.fxml")));
        Scene SuccessBox = new Scene(FXMLLoader.load(getClass().getResource("Views/SuccessBox.fxml")));
        Scene MainPage = new Scene(FXMLLoader.load(getClass().getResource("Views/MainPage.fxml")));
        Scene CreateAccount = new Scene(FXMLLoader.load(getClass().getResource("Views/CreateAccount.fxml")));
        Scene PathPage = new Scene(FXMLLoader.load(getClass().getResource("Views/PathPage.fxml")));
        Scene AddFriend = new Scene(FXMLLoader.load(getClass().getResource("Views/AddFriend.fxml")));
        if(stage.getScene().equals(MainPage)) stage.setMaximized(true);
         */
        stage.setScene(FirstMenu);
        stage.show();
    }
}
