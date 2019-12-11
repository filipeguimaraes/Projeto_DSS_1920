/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import java.util.*;

public class Colecao {

    private String codCol;
    private List<Media> mediasCol;
    private String nomeCol;

    public Colecao(String codCol, List<Media> mediasCol, String nomeCol) {
        this.codCol = codCol;
        this.mediasCol = mediasCol;
        this.nomeCol = nomeCol;
    }

    /**
     *
     * @param nome
     */
    public void criaColecaoDefault(String nome) {
        // TODO - implement Colecao.criaColecaoDefault
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nome
     */
    public void criaColecaoRandom(String nome) {
        // TODO - implement Colecao.criaColecaoRandom
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param artista
     * @param nome
     */
    public void criaColecaoArtista(String artista, String nome) {
        // TODO - implement Colecao.criaColecaoArtista
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param categoria
     * @param nome
     */
    public void criaColecaoCategoria(String categoria, String nome) {
        // TODO - implement Colecao.criaColecaoCategoria
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param m
     */
    public void add(Media m) {
        // TODO - implement Colecao.add
        throw new UnsupportedOperationException();
    }

    public void setNomeCol(String nomeCol) {
        this.nomeCol = nomeCol;
    }

    public List<Media> getMediasCol() {
        return mediasCol;
    }

    public void setMediasCol(List<Media> mediasCol) {
        this.mediasCol = mediasCol;
    }

    public String getNomeCol() {
        return nomeCol;
    }

    public String getCodCol() {
        return codCol;
    }

    public void setCodCol(String codCol) {
        this.codCol = codCol;
    }
}
