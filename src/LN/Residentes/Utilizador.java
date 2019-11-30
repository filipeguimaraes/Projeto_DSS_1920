package LN.Residentes;

import LN.Biblioteca;

public class Utilizador {

    private Biblioteca biblioteca;
    private String nome;
    private String email;
    private String password;

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

    public void getPassword() {
        // TODO - implement Utilizador.getPassword
        throw new UnsupportedOperationException();
    }

}
