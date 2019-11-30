import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);
        Scene FirstMenu = new Scene( FXMLLoader.load( getClass().getResource("GUI/views/FirstMenu.fxml") ) );
        //Scene MainPage = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/MainPage.fxml")));

        /*
        Scene Login = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/Login.fxml")),600,400);
        Scene ErrorBox = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/ErrorBox.fxml")));
        Scene SuccessBox = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/SuccessBox.fxml")));
        Scene CreateAccount = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/CreateAccount.fxml")));
        Scene PathPage = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/PathPage.fxml")));
        Scene AddFriend = new Scene(FXMLLoader.load(getClass().getResource("GUI.GUI.views.views/AddFriend.fxml")));
        if(stage.getScene().equals(MainPage)) stage.setMaximized(true);
         */
        stage.setScene(FirstMenu);
        stage.show();
    }
}
