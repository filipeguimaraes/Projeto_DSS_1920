/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;


public class Media {

    private String nomeMedia;
    private String path;
    private String artista;

    public Media( String nomeMedia, String path, String artista) {
        this.nomeMedia = nomeMedia;
        this.path = path;
        this.artista = artista;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nomeMedia=nome;
    }

    /**
     *
     * @param nome
     */
    public void setArtista(String nome) {
        this.artista = nome;
    }

    /**
     *
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

}
