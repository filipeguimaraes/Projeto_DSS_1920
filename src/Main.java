import GUI.controller.ControllerFirstMenu;
import GUI.controller.ControllerLogin;
import GUI.controller.ControllerMainPage;
import LN.Media;
import LN.MediaCenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static MediaCenter model = new MediaCenter();

    public static void main(String[] args){ launch(args); }

    public void init(){
        //teste de media
        Media m = new Media();
        m.setNome("Hello");
        m.setPath("c:\\Media\\Adele - Hello.mp3");
        model.adicionaMedia(m);
        model.registaUtilizador("ola","ola","ola");
        ControllerFirstMenu.init(model);
        ControllerLogin.init(model);
        ControllerMainPage.init(model);
    }

    @Override
    public void start(Stage stage) throws IOException {
        init();
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);
        Scene start = new Scene(FXMLLoader.load( getClass().getResource("/GUI/views/FirstMenu.fxml") ));
        start.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());
        stage.setScene(start);
        stage.show();
    }
}
