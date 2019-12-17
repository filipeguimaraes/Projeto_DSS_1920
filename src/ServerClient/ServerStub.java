package ServerClient;

import DAO.BibliotecaDAO;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.MediaCenter;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.*;
import java.util.Map;

public class ServerStub implements MediaCenterSignatures {

    private MediaCenter model = new MediaCenter();

    private PrintWriter out;
    private BufferedReader in;


    public ServerStub(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void upload(String path, String nome, String col, String artista, String cat) throws MediaException, IOException {

        //vai dar cagada devido ao copiar ficheiro que ainda nao esta implementado para o server/client
        model.upload(path, nome, col, artista, cat);
    }

    @Override
    public boolean validaFich(String path) {

        //deve dar bosta
        return model.validaFich(path);
    }

    @Override
    public void reproduzirMedia(MediaKey key) {

        //provavelment errado
        //reproduz(model.getMediaDAO().get(key).getPath());
    }

    @Override
    public void reproduz(String path) {

        //isto é tricky pq tenho de passar a musica ao user e depois ele pode ouvir
    }

    @Override
    public void setEmailOn(String email) {

        model.setEmailOn(email);
    }

    @Override
    public void removePermissao() {

        model.removePermissao();
    }

    @Override
    public void setPermissaoResidente() {

        model.setPermissaoResidente();
    }

    @Override
    public void setPermissaoAdministrador() {

        model.setPermissaoAdministrador();
    }

    @Override
    public void setPremissaoConvidado() {

        model.setPremissaoConvidado();
    }

    @Override
    public void iniciarSessao(String email, String password) throws UtilizadorException, AdminException, PermissaoException {

        model.iniciarSessao(email, password);
    }

    @Override
    public void registaUtilizador(String nome, String email, String password) {
        model.registaUtilizador(nome, email, password);
    }

    @Override
    public String copiaFicheiro(String path) throws IOException {
        return model.copiaFicheiro(path);//INCOMPLETE
    }

    @Override
    public boolean eAdmin() {
        return model.eAdmin();
    }

    @Override
    public boolean eUtilizador() {
        return model.eUtilizador();
    }

    @Override
    public boolean eConvidado() {
        return model.eConvidado();
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
        return model.getEmailOn();
    }

    @Override
    public Map<MediaKey, Media> getMediaDAO() {
        return null;
    }
}
