/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServerAndClient;

import Domain.Client;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author kenneth
 */
public class MainServer {

    private static ServerSocket mainServer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int c = 0;
        mainServer = new ServerSocket(12000);
        do {
            Socket s = mainServer.accept();
            c++;
            new MyServer(s, new Client("", String.valueOf(0), String.valueOf(s.getInetAddress().toString().substring(1)))).start();//c es la cantidad de clientes conectados
            
        } while (true);
    }
}
