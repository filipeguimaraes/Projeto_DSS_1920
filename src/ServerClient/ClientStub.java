package ServerClient;

import DAO.BibliotecaDAO;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.IOException;
import java.util.Map;

public class ClientStub implements MediaCenterSignatures {

    @Override
    public void upload(String path, String nome, String col, String artista, String cat) throws MediaException, IOException {
    }

    @Override
    public boolean validaFich(String path) {
        return false;
    }

    @Override
    public void reproduzirMedia(MediaKey key) {

    }

    @Override
    public void reproduz(String path) {

    }

    @Override
    public void setEmailOn(String email) {

    }

    @Override
    public void removePermissao() {

    }

    @Override
    public void setPermissaoResidente() {

    }

    @Override
    public void setPermissaoAdministrador() {

    }

    @Override
    public void setPremissaoConvidado() {

    }

    @Override
    public void iniciarSessao(String email, String password) throws UtilizadorException, AdminException, PermissaoException {

    }

    @Override
    public void registaUtilizador(String nome, String email, String password) {

    }

    @Override
    public String copiaFicheiro(String path) throws IOException {
        return null;
    }

    @Override
    public boolean eAdmin() {
        return false;
    }

    @Override
    public boolean eUtilizador() {
        return false;
    }

    @Override
    public boolean eConvidado() {
        return false;
    }

    @Override
    public BibliotecaDAO getBibliotecas() {
        return null;
    }

    @Override
    public Map<String, Utilizador> getUtilizadorDAO() {
        return null;
    }

    @Override
    public String getEmailOn() {
        return null;
    }

    @Override
    public Map<MediaKey, Media> getMediaDAO() {
        return null;
    }
}
