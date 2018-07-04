package SocketServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    private static ServerSocket mainServer;

    public static void main(String[] args) throws IOException {
        int c = 0;
        mainServer = new ServerSocket(12000);
        do {
            Socket player1 = mainServer.accept();
            Socket player2 = mainServer.accept();
            new MyServer(player1, player2).start();
        } while (true);
        
    }
    
}
