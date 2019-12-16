package ServerClient;

import LN.MediaCenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class ServerStub implements Runnable{
    private Socket client_socket;
    private PrintWriter out;
    private BufferedReader in;

    private ReentrantLock server_lock;

    private static MediaCenter model = MediaCenter.getInstance();
    //isto é um problema significa que todas as conecções ao sv vão partilhar o mesmo email on e perms.

    public ServerStub(Socket client_socket, ReentrantLock server_lock) {
        this.client_socket = client_socket;
        this.server_lock = server_lock;
    }

    private String getResponseFor(String client_request) {
        String response = "No Response";

        return response;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            out = new PrintWriter(client_socket.getOutputStream());

            String client_request = in.readLine();
            String server_response;

            while (!client_request.equals("quit")){

                server_response = getResponseFor(client_request);

                out.println(server_response);
                out.flush();

                client_request = in.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
