package ServerClient;

import DAO.BibliotecaDAO;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ClientStub implements MediaCenterSignatures {

    private Socket socket;
    //Socket socket = new Socket("localhost",12055);

    private PrintWriter out;
    private BufferedReader in;

    public ClientStub(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
    }

    private void reading1() throws IOException {
        out.println("READY");
        out.flush();
        String s = in.readLine();
        if (!s.equals("START")) throw new IOException("protocol failed 1");
    }

    private void writing1() throws IOException {
        String s = in.readLine();
        if (!s.equals("READY")) throw new IOException("protocol failed 2");
        out.println("START");
        out.flush();
    }

    @Override
    public void upload(String path, String nome, String col, String artista, String cat) throws IOException {
        writing1();
        out.println("upload"+"«"+path+"«"+nome+"«"+col+"«"+artista+"«"+cat);
    }

    @Override
    public boolean validaFich(String path) throws IOException {
        reading1();
        return Boolean.parseBoolean(in.readLine());
    }

    @Override
    public void reproduzirMedia(MediaKey key) {

    }

    @Override
    public void reproduz(String path) {

    }

    @Override
    public void setEmailOn(String email) {
        out.println("setEmailOn«"+email);
        out.flush();
    }

    @Override
    public void removePermissao() {
        out.println("removePermissao");
        out.flush();
    }

    @Override
    public void setPermissaoResidente() {
        out.println("setPermissaoResidente");
        out.flush();
    }

    @Override
    public void setPermissaoAdministrador() {
        out.println("setPermissaoAdministrador");
        out.flush();
    }

    @Override
    public void setPremissaoConvidado() {
        out.println("setPremissaoConvidado");
        out.flush();
    }

    @Override
    public void iniciarSessao(String email, String password) throws UtilizadorException, AdminException, PermissaoException {
        out.println("iniciarSessao"+"«"+email+"«"+password);
        out.flush();
    }

    @Override
    public void registaUtilizador(String nome, String email, String password) {
        out.println("registaUtilizador"+"«"+nome+"«"+email+"«"+password);
        out.flush();
    }

    @Override
    public String copiaFicheiro(String path) throws IOException {
        return null;
    }

    @Override
    public boolean eAdmin() throws IOException {
        reading1();
        return Boolean.parseBoolean(in.readLine());
    }

    @Override
    public boolean eUtilizador() throws IOException {
        reading1();
        return Boolean.parseBoolean(in.readLine());
    }


    @Override
    public boolean eConvidado() throws IOException {
        reading1();
        return Boolean.parseBoolean(in.readLine());
    }

    @Override
    public BibliotecaDAO getBibliotecas() throws IOException {
        reading1();
        return null;
    }

    @Override
    public Map<String, Utilizador> getUtilizadorDAO() throws IOException {
        reading1();
        return null;
    }

    @Override
    public String getEmailOn() throws IOException {
        reading1();
        return null;
    }

    @Override
    public Map<MediaKey, Media> getMediaDAO() throws IOException {
        reading1();
        return null;
    }
}
