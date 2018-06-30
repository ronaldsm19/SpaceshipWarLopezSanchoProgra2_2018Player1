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
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author fabian
 */
public class MyServer extends Thread {

    //atributos
    //private int socketPortNumber;
    private Socket socket1, socket2;
    private ArrayList<String> array = new ArrayList<String>();
    private String n;
    PrintStream sendP1, sendP2;
    BufferedReader receiveP1, receiveP2;

    //constructor
    public MyServer(Socket socket1, Socket socket2) throws IOException {
        super("Server Thread");
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.sendP1 = new PrintStream(this.socket1.getOutputStream());
        this.sendP2 = new PrintStream(this.socket2.getOutputStream());
        this.receiveP1 = new BufferedReader(new InputStreamReader(this.socket1.getInputStream()));
        this.receiveP2 = new BufferedReader(new InputStreamReader(this.socket2.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String info1 = null;
                System.out.println("Esperando 1");
                if ((info1 = receiveP1.readLine()) != null) {
                    System.out.println("entro 1");
                    SAXBuilder saxBuilder = new SAXBuilder();
                    StringReader stringReader = new StringReader(info1);
                    Document doc = saxBuilder.build(stringReader);
                    Element root = doc.getRootElement();
                    String action = root.getName();
                    switch (action.toUpperCase()) {
                        case "CREATE":
                            break;
                        case "ATTACK":
                            break;
                        case "MESSAGE":
                            sendP2.println(info1);
                            break;
                    }
                }
                String info2 = null;
                System.out.println("salio 1 y espero 2");
                if ((info2 = receiveP2.readLine()) != null) {
                    System.out.println("entro 2");
                    SAXBuilder saxBuilder = new SAXBuilder();
                    StringReader stringReader = new StringReader(info2);
                    Document doc = saxBuilder.build(stringReader);
                    Element root = doc.getRootElement();
                    String action = root.getName();
                    switch (action.toUpperCase()) {
                        case "CREATE":
                            break;
                        case "ATTACK":
                            break;
                        case "MESSAGE":
                            sendP1.println(info2);
                            break;
                    }

                }
                System.out.println("salio 2");
            }
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
SAXBuilder saxBuilder = new SAXBuilder();
                            StringReader stringReader = new StringReader(infoResponse);
                            Document doc = saxBuilder.build(stringReader);
                            Element root = doc.getRootElement();
                            //se crea un estudiante
                            Student student = new Student();
                            //se le asignan todos los atirbutos
                            student.setIdentification(root.getAttributeValue("ID"));
                            student.setAdmissionGrade(Float.parseFloat(root.getChildText("Grade")));*/
