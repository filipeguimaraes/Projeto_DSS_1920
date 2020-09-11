/**
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
package LN.Residentes;

import DAO.UtilitarioDAO;

public class Administrador {

    private String email;
    private String passAdmin;

    public Administrador() {
        this.email = UtilitarioDAO.getInstance().getEmailAdmin();
        this.passAdmin = UtilitarioDAO.getInstance().getPassAdmin();
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