/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServerAndClient;

import Domain.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabian
 */
public class MyServer extends Thread {

    //atributos
    //private int socketPortNumber;
    private Socket socket;
    private ArrayList array = new ArrayList<Client>();
    private String n;

    //constructor
    public MyServer(Socket socket, Client client) {
        super("Server Thread");
        this.socket = socket;
        this.n = client.getIp();

    }

    @Override
    public void run() {
        try {
            do {
                PrintStream send1 = new PrintStream(this.socket.getOutputStream());
                BufferedReader receive1 = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

                send1.println(this.n + " es su numero");//envia n indicandole cual cliente es
                switch (receive1.readLine().toUpperCase()) {
                    case "CREATE":
                        
                        System.out.println("case create");
                        break;
                    case "ATTACK":
                        System.out.println("case attack");
                        break;
                }
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
