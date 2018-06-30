/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServerAndClient;

import Domain.Client;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author kenneth
 */
public class MainServer {

    private static ServerSocket mainServer;

    public static void main(String[] args) throws IOException {
        int c = 0;
        mainServer = new ServerSocket(12000);
        do {
            Socket player1 = mainServer.accept();
            System.out.println("jugador 1");
            Socket player2 = mainServer.accept();
            System.out.println("jugador 2");
            new MyServer(player1, player2).start();
            System.out.println("Empieza juego");
        } while (true);
    }
}
