/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import DAO.ColecaoMediaDAO;
import UTILITIES.MediaKey;

import java.util.Map;

public class Colecao {

    private String codCol;
    private ColecaoMediaDAO mediasCol;
    private String nomeCol;

    public Colecao(String codCol, String nomeCol) {
        this.codCol = codCol;
        this.mediasCol = ColecaoMediaDAO.getInstance();
        this.nomeCol = nomeCol;
    }

    /**
     * @param nome
     */
    public void criaColecaoDefault(String nome) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param nome
     */
    public void criaColecaoRandom(String nome) {
        // TODO - implement Colecao.criaColecaoRandom
        throw new UnsupportedOperationException();
    }

    /**
     * @param artista
     * @param nome
     */
    public void criaColecaoArtista(String artista, String nome) {
        // TODO - implement Colecao.criaColecaoArtista
        throw new UnsupportedOperationException();
    }

    /**
     * @param categoria
     * @param nome
     */
    public void criaColecaoCategoria(String categoria, String nome) {
        // TODO - implement Colecao.criaColecaoCategoria
        throw new UnsupportedOperationException();
    }

    /**
     * @param m media que pertende adicionar
     */
    public void adicionaMedia(Media m) {
        this.mediasCol.add(new MediaKey(m.getNomeMedia(), m.getArtista()), m, this.codCol);
    }

    public String getCodCol() {
        return codCol;
    }

    public void setCodCol(String codCol) {
        this.codCol = codCol;
    }

    public void setMediasCol(ColecaoMediaDAO mediasCol) {
        this.mediasCol = mediasCol;
    }

    public String getNomeCol() {
        return nomeCol;
    }

    public Map<MediaKey, Media> getMedias() {
        return this.mediasCol.get(this.codCol);
    }

    public void setNomeCol(String nomeCol) {
        this.nomeCol = nomeCol;
    }
}
