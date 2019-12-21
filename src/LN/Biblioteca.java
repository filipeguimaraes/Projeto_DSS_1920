/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN;

import DAO.ColecaoDAO;

import java.util.Map;

public class Biblioteca {

    private ColecaoDAO colecoes;
    private String cod;
    private String nomeBiblio;

    public Biblioteca(String cod, String nomeBiblio) {
        this.cod = cod;
        this.nomeBiblio = nomeBiblio;
        this.colecoes = ColecaoDAO.getInstance();
    }

    /**
     *
     * @param colecao
     */
    public void addColecaoNaBiblioteca(Colecao colecao) {
        this.colecoes.put(colecao.getNomeCol(), colecao);
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

    public Colecao getColecao(String cod) {
        return this.colecoes.getByBiblioteca(this.cod, cod);
    }

    public Colecao getColecaoByNome(String nome) {
        String codigo = this.colecoes.getCodCol(nome, this.cod);
        return this.colecoes.getByBiblioteca(this.cod, codigo);
    }

    public Map<String, Colecao> getColecoes() {
        return this.colecoes.getByBiblioteca(this.cod);
    }

    public void adicionaColecao(Colecao col) {
        this.colecoes.putOnBiblioteca(col.getCodCol(), col, this.cod);
    }


    public void criaColecao(String nome) {
        String novoCodigo = String.valueOf((this.colecoes.size() * 10) + 10);
        Colecao nova = new Colecao(novoCodigo, nome);
        adicionaColecao(nova);
    }


}
