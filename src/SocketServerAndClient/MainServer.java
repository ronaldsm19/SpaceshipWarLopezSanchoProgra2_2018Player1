/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fabian
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
            c++;
            new MyServer(mainServer.accept(), c).start();//c es la cantidad de clientes conectados
        } while (true);
    }
}
