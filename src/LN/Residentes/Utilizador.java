/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçanlo Ferreira A84073
 */
package LN.Residentes;

import LN.Biblioteca;

public class Utilizador {

    private Biblioteca biblioteca;
    private String nome;
    private String email;
    private String password;


    public Utilizador(Biblioteca biblioteca, String nome, String email, String password) {
        this.biblioteca = biblioteca;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @param email
     */
    public void getBibliotecaUtilizador(String email) {
        // TODO - implement Utilizador.getBibliotecaUtilizador
        throw new UnsupportedOperationException();
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
