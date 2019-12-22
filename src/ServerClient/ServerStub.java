package ServerClient;

import DAO.BibliotecaDAO;
import LN.Biblioteca;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.MediaCenter;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.*;
import java.util.List;
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
        model.upload(path, nome, col, artista, cat);
    }

    @Override
    public boolean validaFich(String path) {
        return model.validaFich(path);
    }

    @Override
    public void reproduzirMedia(MediaKey key) {

    }

    @Override
    public void reproduz(String path) {

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
    public Utilizador getUtilizador(String email) {
        return model.getUtilizador(email);
    }

    @Override
    public List<Media> getMedias() {
        return model.getMedias();
    }

    @Override
    public Biblioteca getBibliotecaByNome(String selectedItem) {
        return model.getBibliotecaByNome(selectedItem);
    }


    @Override
    public String getEmailOn() {
        return model.getEmailOn();
    }

    @Override
    public List<Biblioteca> getBibliotecas() {
        return model.getBibliotecas();
    }

    @Override
    public void alteraCategoria(String text, MediaKey mediaKey) {
        model.alteraCategoria(text, mediaKey);
    }
}
