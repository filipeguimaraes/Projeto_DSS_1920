package ServerClient;

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
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ServerConnection implements Runnable {
    private Socket client_socket;

    private PrintWriter out;
    private BufferedReader in;


    private ReentrantLock server_lock;

    private MediaCenterSignatures model = new ServerStub(in,out);


    public ServerConnection(Socket client_socket, ReentrantLock server_lock) {
        this.client_socket = client_socket;
        this.server_lock = server_lock;
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


    private void getResponseFor(String client_request) throws MediaException, AdminException, UtilizadorException, PermissaoException, IOException {
        String[] split = client_request.split("«");
        switch (split[0]) {
            case "upload":
                server_lock.lock();
                try {
                    model.upload(split[1], split[2], split[3], split[4], split[5]);
                    writing1();
                } catch (MediaException | IOException e) {
                    server_lock.unlock();
                    throw new MediaException(e.getMessage());
                }
                server_lock.unlock();
                break;

            case "iniciarSessao":
                try {
                    model.iniciarSessao(split[1],split[2]);
                    writing1();
                } catch (UtilizadorException e) {
                    throw new UtilizadorException(e.getMessage());
                } catch (AdminException e) {
                    throw new AdminException(e.getMessage());
                } catch (PermissaoException e) {
                    throw new PermissaoException(e.getMessage());
                }
                break;

            case "validaFich":
                writing1();
                out.println(String.valueOf(model.validaFich(split[1]))); //r bool
                break;

            case "reproduzirMedia":
                writing1();
                model.reproduzirMedia((MediaKey) null);//tem de devovler a data para dar play
                break;
                //MediaKey key);

            case "reproduz":
                model.reproduz(split[1]);
                break;

            case "setEmailOn":
                model.setEmailOn(split[1]);
                break;

            case "removePermissao":
                model.removePermissao();
                break;

            case "setPermissaoResidente":
                model.setPermissaoResidente();
                break;

            case "setPermissaoAdministrador":
                model.setPermissaoAdministrador();
                break;

            case "setPremissaoConvidado":
                model.setPremissaoConvidado();
                break;

            case "registaUtilizador":
                model.registaUtilizador(split[1], split[2], split[3]);
                break;

            case "eAdmin":
                writing1();
                out.println(String.valueOf(model.eAdmin()));
                break;

            case "eUtilizador":
                writing1();
                out.println(String.valueOf(model.eUtilizador()));
                break;

            case "eConvidado":
                writing1();
                out.println(String.valueOf(model.eConvidado()));
                break;

            case "getEmailOn":
                writing1();
                out.println(model.getEmailOn());
                break;

                //este metodos usaram o serializable


            case "getUtilizador":
                writing1();
                outPutObject(model.getUtilizador(split[1]));
                break;

            case "getMedias":
                writing1();
                outPutObject(model.getMedias());
                break;

            case "getBibliotecaByNome":
                writing1();
                outPutObject(model.getBibliotecaByNome(split[1]));
                break;

            case "getBibliotecas":
                writing1();
                outPutObject(model.getBibliotecas());
                break;


            default:
                out.println("WRONG");
        }
    }

    @Override
    public void run() {
        try {
            ios = new ObjectInputStream(client_socket.getInputStream());
            oos = new ObjectOutputStream(client_socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            out = new PrintWriter(client_socket.getOutputStream());

            String client_request = in.readLine();

            while (!client_request.equals("quit")) {

                try {
                    getResponseFor(client_request);
                } catch (UtilizadorException | AdminException | PermissaoException | MediaException | IOException e) {
                    out.println(e.getMessage());
                }

                out.flush();

                client_request = in.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
