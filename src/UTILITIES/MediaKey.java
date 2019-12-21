package UTILITIES;

import java.io.Serializable;
import java.util.Objects;

public class MediaKey implements Serializable {
        private String nome;
        private String artista;

    public MediaKey(String nome, String artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        MediaKey mediaKey = (MediaKey) o;
        return mediaKey.nome.equals(this.nome) &&
                mediaKey.artista.equals(this.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, artista);
    }
}
