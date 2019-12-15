package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientStub {







    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",12055);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            Scanner scan = new Scanner(System.in);
            String client_request = "";
            String server_response;

            while(scan.hasNextLine() && !client_request.equals("quit")){
                client_request = scan.nextLine();

                out.println("request by client");
                out.flush();

                server_response = in.readLine();
                //give info to controller view
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
