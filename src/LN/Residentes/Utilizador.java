/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN.Residentes;

import DAO.BibliotecaDAO;
import DAO.UtilitarioDAO;
import DAO.UtilizadorDAO;
import LN.Biblioteca;
import LN.Media;

import java.util.Map;


public class Utilizador {

    private Biblioteca biblioteca;
    private String nome;
    private String email;
    private String password;
    private Map<Media,String> categorias;


    public Utilizador(String codBiblioteca, String nome, String email, String password) {
        this.biblioteca = BibliotecaDAO.getInstance().get(codBiblioteca);
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.categorias = UtilizadorDAO.getInstance().categoriasByUtilizador(email);
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @param emailDep
     */
    public void setEmail(String emailDep) {
        this.email = emailDep;
    }

    /**
     *
     * @param pass
     */
    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
