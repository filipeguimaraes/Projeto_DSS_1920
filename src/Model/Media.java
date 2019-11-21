package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Media {
    private SimpleIntegerProperty numeroMedia;
    private SimpleStringProperty nomeMedia;
    private SimpleDoubleProperty duracaoMedia;
    private SimpleStringProperty albumMedia;
    private SimpleStringProperty categoriaMedia;

    public Media (int numeroMedia, String nomeMedia, double duracaoMedia, String albumMedia,
                String categoriaMedia){
        this.numeroMedia = new SimpleIntegerProperty(numeroMedia);
        this.nomeMedia = new SimpleStringProperty(nomeMedia);
        this.duracaoMedia = new SimpleDoubleProperty(duracaoMedia);
        this.albumMedia = new SimpleStringProperty(albumMedia);
        this.categoriaMedia = new SimpleStringProperty(categoriaMedia);
    }

    public int getNumeroMedia() {
        return numeroMedia.get();
    }

    public SimpleIntegerProperty numeroMediaProperty() {
        return numeroMedia;
    }

    public void setNumeroMedia(int numeroMedia) {
        this.numeroMedia.set(numeroMedia);
    }

    public String getNomeMedia() {
        return nomeMedia.get();
    }

    public SimpleStringProperty nomeMediaProperty() {
        return nomeMedia;
    }

    public void setNomeMedia(String nomeMedia) {
        this.nomeMedia.set(nomeMedia);
    }

    public double getDuracaoMedia() {
        return duracaoMedia.get();
    }

    public SimpleDoubleProperty duracaoMediaProperty() {
        return duracaoMedia;
    }

    public void setDuracaoMedia(double duracaoMedia) {
        this.duracaoMedia.set(duracaoMedia);
    }

    public String getAlbumMedia() {
        return albumMedia.get();
    }

    public SimpleStringProperty albumMediaProperty() {
        return albumMedia;
    }

    public void setAlbumMedia(String albumMedia) {
        this.albumMedia.set(albumMedia);
    }

    public String getCategoriaMedia() {
        return categoriaMedia.get();
    }

    public SimpleStringProperty categoriaMediaProperty() {
        return categoriaMedia;
    }

    public void setCategoriaMedia(String categoriaMedia) {
        this.categoriaMedia.set(categoriaMedia);
    }

}
