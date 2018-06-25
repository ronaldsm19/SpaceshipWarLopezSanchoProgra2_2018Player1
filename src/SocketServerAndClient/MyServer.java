/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServerAndClient;

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
    private ArrayList array = new ArrayList<>();
    private int n;

    //constructor
    public MyServer(Socket socket, int n) {
        super("Server Thread");
        this.socket = socket;
        this.n = n;
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
                System.out.println(receive1.readLine());//imprime lo que envia el cliente en este caso el nombre
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
