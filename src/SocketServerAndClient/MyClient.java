package SocketServerAndClient;

import Domain.Action;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class MyClient extends Thread {

    private int socketPortNumber;
    private Stage stage;
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
            String msj = JOptionPane.showInputDialog(null, "Mensaje");
//            send.println(nombre);//envia crear con el nombre
            Element eCreate = new Element(nombre);
            eCreate.setAttribute("Message", msj);

            XMLOutputter xMLOutputter = new XMLOutputter(Format.getCompactFormat());
            String xmlStringStudentElement = xMLOutputter.outputString(eCreate);
            xmlStringStudentElement = xmlStringStudentElement.replace("\n", "");

//            XMLOutputter xmOut = new XMLOutputter();
////            new XMLOutputter().outputString(eCreate);
//            String info = "Name :" + xmOut.outputString(eCreate);
            System.out.println(xmlStringStudentElement);
            send.println(xmlStringStudentElement);

        } catch (Exception ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
