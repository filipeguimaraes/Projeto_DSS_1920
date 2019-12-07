/**
 *
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN.Residentes;

public class Administrador {

    private String email;
    private String passAdmin;

    public Administrador(String email, String passAdmin) {
        this.email = email;
        this.passAdmin = passAdmin;
    }

    public Administrador() {
        this.email = "ola";
        this.passAdmin = "ola";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }
}