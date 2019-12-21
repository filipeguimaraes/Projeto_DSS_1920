
import GUI.controller.ControllerAlterarCategoria;
import GUI.controller.ControllerReprodutor;
import UTILITIES.MediaKey;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTest extends Application {

    public static void main(String[] args) {
        /*
        Map<String, Utilizador> users = UtilizadorDAO.getInstance();
        Utilizador fr = users.get("francisco@email.com");
        System.out.println("nome: "+fr.getNome());
        System.out.println("email: "+fr.getEmail());
        System.out.println("pass: "+fr.getPassword());
        System.out.println(users.containsKey("rita@email.com"));

         */
        //MediaKey key = new MediaKey("ola","ola");
        /*
        Map<String, Colecao> ola = new HashMap<>();
        ola.put("key1", ColecaoDAO.getInstance().get("10"));
        ola.put("Key2",ColecaoDAO.getInstance().get("20"));

        System.out.println(ola.toString());
         */

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Media Center");
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);

        FXMLLoader l = new FXMLLoader(getClass().getResource("/GUI/views/Reprodutor.fxml"));
        Scene reprod = new Scene(l.load());

        //ControllerReprodutor c = l.getController();
        //String source = "c:/Media/Britney Spears - Toxic.mp3";
        //c.setPlayer(source);
        reprod.getStylesheets().add(getClass().getResource("/GUI/sheet.css").toExternalForm());

        stage.setScene(reprod);
        stage.centerOnScreen();
        stage.show();
    }
}
