/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import DAO.ColecaoDAO;

import java.util.*;

public class Biblioteca {

    private Map<String,Colecao> colecoes;
    private String cod;
    private String nomeBiblio;

    public Biblioteca(String cod, String nomeBiblio) {
        this.cod = cod;
        this.nomeBiblio = nomeBiblio;
        ArrayList<String> codColecoes = new ArrayList<>(ColecaoDAO.getInstance().getByBiblioteca(cod));
        this.colecoes = new HashMap<>();
        for (String key : codColecoes){
            Colecao c = ColecaoDAO.getInstance().get(key);
            this.colecoes.put(key, c);
        }
        //this.colecoes = ColecaoDAO.getInstance().getByBiblioteca(this.cod);
    }

    /**
     *
     * @param colecao
     */
    public void addColecaoNaBiblioteca(Colecao colecao) {
        colecoes.put(colecao.getNomeCol(),colecao);
    }

    public Map<String,Colecao> getColecoes() {
        return this.colecoes;
    }

    public void setColecoes(Map<String, Colecao> colecoes) {
        this.colecoes = colecoes;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNomeBiblio() {
        return nomeBiblio;
    }

    public void setNomeBiblio(String nomeBiblio) {
        this.nomeBiblio = nomeBiblio;
    }
}
