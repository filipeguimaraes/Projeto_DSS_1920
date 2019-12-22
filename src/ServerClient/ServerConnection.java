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
import java.util.Arrays;
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
                    model.upload(split[4]+" - "+split[2]+".mp3", split[2], split[3], split[4], split[5]);
//                    writing1();
                    String new_path = model.copiaFicheiro(split[4]+" - "+split[2]+".mp3");
                    inPutMusic(new_path);
                } catch (MediaException | IOException e) {
                    server_lock.unlock();
                    throw new MediaException(e.getMessage());
                }
                server_lock.unlock();
                break;

            case "reproduzirMedia":
  //              writing1();
                model.reproduzirMedia((MediaKey) null);//tem de devovler a data para dar play
                break;

            case "reproduz":
                model.reproduz(split[1]);
                break;

            case "iniciarSessao":
                try {
                    model.iniciarSessao(split[1],split[2]);
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


            case "setEmailOn":
                model.setEmailOn(split[1]);
                break;

            case "removePermissao":
                model.removePermissao();
                break;

            case "setPermissaoResidente":
                System.out.println("setPermissaoResidente");
                model.setPermissaoResidente();
                break;

            case "setPermissaoAdministrador":
                System.out.println("setPermissaoAdministrador");
                model.setPermissaoAdministrador();
                break;

            case "setPremissaoConvidado":
                System.out.println("setPremissaoConvidado");
                model.setPremissaoConvidado();
                break;

            case "registaUtilizador":
                model.registaUtilizador(split[1], split[2], split[3]);
                break;

            case "eAdmin":
                System.out.println("eAdmin");
                out.println(String.valueOf(model.eAdmin()));
                break;

            case "eUtilizador":
                System.out.println("eUtilizador");
                out.println(String.valueOf(model.eUtilizador()));
                break;

            case "eConvidado":
                System.out.println("eConvidado");
                out.println(String.valueOf(model.eConvidado()));
                break;

            case "getEmailOn":
                out.println((model.getEmailOn() == null?"NoEmail":model.getEmailOn()));
                break;

            case "getUtilizador":
//                writing1();
                outPutObject(model.getUtilizador(split[1]));
                break;

            case "getMedias":
  //              writing1();
                outPutObject(model.getMedias());
                break;

            case "getBibliotecaByNome":
    //            writing1();
                outPutObject(model.getBibliotecaByNome(split[1]));
                break;

            case "getBibliotecas":
      //          writing1();
                outPutObject(model.getBibliotecas());
                break;

            case "alteraCategoria":
                model.alteraCategoria(split[1],new MediaKey(split[2],split[3]));
                break;


            default:
                out.println("WRONG");
        }
    }

    private void inPutMusic(String new_path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(new_path)));
            int num_read = 1;
            char [] string_read = new char [1024];

            long max_read = Long.parseLong(in.readLine());

            num_read = in.read(string_read,0,1024);
            max_read -= num_read;

            while(1024 == num_read && max_read > 0){
                bw.write(string_read,0,num_read);
                bw.flush();
                num_read = in.read(string_read,0,1024);
                max_read -= num_read;
                if(num_read < 1024 && num_read > 0){
                    bw.write(string_read,0,num_read);
                    bw.flush();
                }
            }
            bw.close();

            /*
            int bytesRead =0;

            byte [] mybytearray  = new byte [1024];
    InputStream is = client_socket.getInputStream();
    FileOutputStream fos = new FileOutputStream(new_path);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    // thanks to A. Cdiz for the bug fix
    do {
       bytesRead =
          is.read(mybytearray, 0, (mybytearray.length));
       if(bytesRead >= 0){
        bos.write(mybytearray, 0 , bytesRead);
        bos.flush();
       }
    } while(bytesRead > -1);
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("saiu");
    }

    @Override
    public void run() {
        try {
            InputStream is = client_socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));
            out = new PrintWriter(client_socket.getOutputStream());
            oos = new ObjectOutputStream(this.client_socket.getOutputStream());
            oos.flush();


            String client_request;
            client_request = in.readLine();
            while (!client_request.equals("quit")) {
                client_request = in.readLine();

                try {
                    getResponseFor(client_request);
                } catch (UtilizadorException | AdminException | PermissaoException | MediaException | IOException e) {
                    out.println(e.getMessage());
                }

                out.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
