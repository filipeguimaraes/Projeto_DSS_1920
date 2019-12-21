package ServerClient;

import DAO.BibliotecaDAO;
import LN.Biblioteca;
import LN.Exceptions.AdminException;
import LN.Exceptions.MediaException;
import LN.Exceptions.PermissaoException;
import LN.Exceptions.UtilizadorException;
import LN.Media;
import LN.Residentes.Utilizador;
import UTILITIES.MediaKey;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ClientStub implements MediaCenterSignatures {
    private static ClientStub inst = null;

    private Socket socket;
    //Socket socket = new Socket("localhost",12055);

    private PrintWriter out;
    private BufferedReader in;

    public static ClientStub getInstance() {
        if (inst == null) {
            try {
                inst = new ClientStub(new Socket("localhost", 12055));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inst;
    }

    public ClientStub(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(this.socket.getOutputStream());
        ios = new ObjectInputStream(this.socket.getInputStream());
        oos = new ObjectOutputStream(this.socket.getOutputStream());
    }

    private void reading1() throws IOException {
        out.println("READY");
        out.flush();
        String s = in.readLine();
        if (!s.equals("START")) throw new IOException("protocol failed 1 - "+s);
    }

    private void writing1() throws IOException {
        String s = in.readLine();
        if (!s.equals("READY")) throw new IOException("protocol failed 2 - "+s);
        out.println("START");
        out.flush();
    }

    @Override
    public void upload(String path, String nome, String col, String artista, String cat) throws IOException {
        writing1();
        out.println("upload" + "«" + path + "«" + nome + "«" + col + "«" + artista + "«" + cat);
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
        out.println("setEmailOn«" + email);
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
    public void iniciarSessao(String email, String password) throws UtilizadorException, AdminException, PermissaoException, IOException {
        out.println("iniciarSessao" + "«" + email + "«" + password);
        out.flush();
        reading1();
    }

    @Override
    public void registaUtilizador(String nome, String email, String password) {
        out.println("registaUtilizador" + "«" + nome + "«" + email + "«" + password);
        out.flush();
    }

    @Override
    public String copiaFicheiro(String path) throws IOException {


        return null;
    }

    @Override
    public boolean eAdmin() throws IOException {
        out.println("eAdmin");
        out.flush();

        reading1();
        return Boolean.parseBoolean(in.readLine());
    }

    @Override
    public boolean eUtilizador() throws IOException {
        out.println("eUtilizador");
        out.flush();

        reading1();
        return Boolean.parseBoolean(in.readLine());
    }


    @Override
    public boolean eConvidado() throws IOException {
        out.println("eConvidado");
        out.flush();

        reading1();
        return Boolean.parseBoolean(in.readLine());
    }

    @Override
    public Utilizador getUtilizador(String email) throws IOException {
        out.println("getUtilizador«"+email);
        out.flush();

        reading1();
        try {
            return (Utilizador) inPutObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Media> getMedias() throws IOException {
        out.println("getMedias");
        out.flush();

        reading1();
        try {
            return (List<Media>) inPutObject();
        } catch (ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Biblioteca getBibliotecaByNome(String selectedItem) throws IOException {
        out.println("getBibliotecaByNome«"+selectedItem);
        out.flush();

        reading1();
        try {
            return (Biblioteca) inPutObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


    @Override
    public String getEmailOn() throws IOException {
        out.println("getEmailOn");
        out.flush();

        reading1();
        try {
            return (String) inPutObject();
        } catch (ClassNotFoundException ignore) {
            return "";
        }
    }

    @Override
    public List<Biblioteca> getBibliotecas() throws IOException {
        out.println("getBibliotecas");
        out.flush();

        reading1();
        try {
            return (List<Biblioteca>) inPutObject();
        } catch (ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void alteraCategoria(String text, MediaKey mediaKey) {

    }


    private ObjectInputStream ios;
    private ObjectOutputStream oos;

    private void outPutObject(Object o) throws IOException {
        oos.writeObject(o);
        oos.flush();
    }

    private Object inPutObject() throws IOException, ClassNotFoundException {
        return ios.readObject();
    }
}
