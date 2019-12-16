package ServerClient;

import LN.Exceptions.MediaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class ServerConnection implements Runnable {
    private Socket client_socket;
    private PrintWriter out;
    private BufferedReader in;

    private ReentrantLock server_lock;

    private MediaCenterSignatures model = new ServerStub();


    public ServerConnection(Socket client_socket, ReentrantLock server_lock) {
        this.client_socket = client_socket;
        this.server_lock = server_lock;
    }

    private String getResponseFor(String client_request) throws MediaException {
        String response = "No Response";
        String[] split = client_request.split("«");
        switch (split[0]) {
            case "upload":
                server_lock.lock();
                try {
                    model.upload(split[1], split[2], split[3], split[4], split[5]);
                } catch (MediaException | IOException e) {
                    throw new MediaException("UPLOAD DEU CAGADA");
                }
                server_lock.unlock();
                break;

            case "iniciarSessao":


            default:
                return response;
        }
        return response;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            out = new PrintWriter(client_socket.getOutputStream());

            String client_request = in.readLine();
            String server_response;

            while (!client_request.equals("quit")) {

                try {
                    server_response = getResponseFor(client_request);
                    out.println(server_response);
                } catch (MediaException e) {
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
