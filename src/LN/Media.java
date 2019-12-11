/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;


import DAO.MediaDAO;
import LN.Residentes.Utilizador;

import java.util.Map;

public class Media {

    private String nomeMedia;
    private String path;
    private String artista;
    private Map<Utilizador,String> categorias;

    public Media( String nomeMedia, String path, String artista) {
        this.nomeMedia = nomeMedia;
        this.path = path;
        this.artista = artista;
        this.categorias = MediaDAO.getInstance().categoriasByUtilizador(nomeMedia,artista);
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

    public void setNomeMedia(String nomeMedia) {
        this.nomeMedia = nomeMedia;
    }

    public Map<Utilizador, String> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<Utilizador, String> categorias) {
        this.categorias = categorias;
    }

    public String getCategoriaPorUtilizador(String email){
        return this.categorias.get(email);
    }
}
