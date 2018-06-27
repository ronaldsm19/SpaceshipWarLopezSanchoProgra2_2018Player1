package SocketServerAndClient;

import Domain.Action;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;
//author @Kenneth Lopez Porras

public class MyClient extends Thread {

    //atributos
    private int socketPortNumber;
    Action action;

    public MyClient(int socketPortNumber) {
        super("Client thread");
        this.socketPortNumber = socketPortNumber;
    }

    @Override
    public void run() {
        InetAddress address;
        Socket socket;
        
        try {
            address = InetAddress.getLocalHost();
            socket = new Socket(address, this.socketPortNumber);
            PrintStream send = new PrintStream(socket.getOutputStream());
            BufferedReader receive = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            System.out.println(receive.readLine());//lee el mensaje del server
            String nombre = JOptionPane.showInputDialog(null, " Digite su nombre");//pregunta por el nombre
            String corde = JOptionPane.showInputDialog(null,"Digite sus cordenadas");
            send.println(nombre);//envia crear con el nombre
            Element eCreate = action.create(nombre, corde);
            
            
        } catch (Exception ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
