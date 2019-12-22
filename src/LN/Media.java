/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;


import DAO.CategoriaDAO;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

public class Media {

    private String nomeMedia;
    private String path;
    private String artista;
    private CategoriaDAO categorias;

    public Media(String nomeMedia, String path, String artista) {
        this.nomeMedia = nomeMedia;
        this.path = path;
        this.artista = artista;
        this.categorias = CategoriaDAO.getInstance();
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nomeMedia = nome;
    }

    /**
     * @param nome
     */
    public void setArtista(String nome) {
        this.artista = nome;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    public String getNomeMedia() {
        return nomeMedia;
    }

    public String getPath() {
        return path;
    }

    public String getArtista() {
        return artista;
    }

    public void setNomeMedia(String nomeMedia) {
        this.nomeMedia = nomeMedia;
    }


    public String getCategoriaPorUtilizador(String email) {
        return this.categorias.getCategoria(email, new MediaKey(this.nomeMedia, this.artista));
    }

    public void alteraCategoriaPorUtilizador(Utilizador user, String categoria) {
        MediaKey mediaKey = new MediaKey(this.nomeMedia, this.artista);
        this.categorias.putCategoria(user.getEmail(), categoria, mediaKey);
    }
}
