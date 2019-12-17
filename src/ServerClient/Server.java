package ServerClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server_socket = new ServerSocket(12055);
            ReentrantLock lock = new ReentrantLock();
            while(true){
                Socket new_client = server_socket.accept();
                Thread server_connection = new Thread(new ServerConnection(new_client,lock));
                server_connection.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
