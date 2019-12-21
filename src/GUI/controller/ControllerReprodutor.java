package GUI.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerReprodutor implements Initializable {

    @FXML
    private MediaView player;


    @FXML
    void handlePauseButton(MouseEvent event) {
        player.getMediaPlayer().pause();
    }

    @FXML
    void handlePlayButton(MouseEvent event) {
        player.getMediaPlayer().play();
    }

    public void setPlayer(String source) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media("c:/Media/Britney Spears - Toxic.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        this.player.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnError(new Runnable() {
            @Override
            public void run() {
                String message = mediaPlayer.errorProperty().get().getMessage();
                System.out.println(message);
            }
        });
    }
}