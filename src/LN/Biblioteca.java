/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import java.util.*;

public class Biblioteca {

    private Map<String,Colecao> colecoes;
    private String cod;
    private String nomeBiblio;

    public Biblioteca(Map<String,Colecao> colecoes, String cod, String nomeBiblio) {
        this.colecoes = colecoes;
        this.cod = cod;
        this.nomeBiblio = nomeBiblio;
    }

    /**
     *
     * @param cd
     */
    public void addColecaoNaBiblioteca(Set cd) {
        // TODO - implement Biblioteca.addColecaoNaBiblioteca
        throw new UnsupportedOperationException();
    }

    public Map<String,Colecao> getColecoes() {
        return this.colecoes;
    }

    public void operation() {
        // TODO - implement Biblioteca.operation
        throw new UnsupportedOperationException();
    }

}
