/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import java.util.Set;

public class Media {

    private String categoria;
    private String nomeMedia;
    private String path;
    private String artista;
    private Set<String> proprietarios;

    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<String> getProprietarios() {
        // TODO - implement Media.getProprietarios
        throw new UnsupportedOperationException();
    }

    public void numeroDonos() {
        // TODO - implement Media.numeroDonos
        throw new UnsupportedOperationException();
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

    public String getCategoria() {
        return categoria;
    }

    public void setNomeMedia(String nomeMedia) {
        this.nomeMedia = nomeMedia;
    }

    public String getArtista() {
        return artista;
    }

    public void setProprietarios(Set<String> proprietarios) {
        this.proprietarios = proprietarios;
    }
}
