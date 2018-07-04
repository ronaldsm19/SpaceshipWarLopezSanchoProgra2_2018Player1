package SocketServerAndClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class MyServer extends Thread {

    private Socket socket1, socket2;
    private ArrayList<String> array = new ArrayList<String>();
    private String n;
    PrintStream sendP1, sendP2;
    BufferedReader receiveP1, receiveP2;

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
                info1 = receiveP1.readLine();
                SAXBuilder saxBuilder = new SAXBuilder();
                StringReader stringReader = new StringReader(info1);
                Document doc = saxBuilder.build(stringReader);
                Element root = doc.getRootElement();
                String action = root.getName();
                switch (action.toUpperCase()) {
                    case "CREATE":
                        break;
                    case "ATTACK":
                        sendP2.println(info1);
                        break;
                    case "MESSAGE":
                        sendP2.println(info1);
                        break;
                    case "EMPTY":
                        sendP2.println(info1);
                        break;
                    case "WIN":
                        sendP2.println(info1);
                        Element e = new Element("Lose");
                        XMLOutputter xm = new XMLOutputter(Format.getCompactFormat());
                        String m = xm.outputString(e);
                        m = m.replace("\n", "");
                        sendP1.println(m);
                        break;
                    case "INFORMRIGTH":
                        sendP2.println(info1);
                        break;
                    case "INFORMWRONG":
                        sendP2.println(info1);
                        break;
                }

                String info2 = null;
                info2 = receiveP2.readLine();
                SAXBuilder saxBuilder2 = new SAXBuilder();
                StringReader stringReader2 = new StringReader(info2);
                Document doc2 = saxBuilder.build(stringReader2);
                Element root2 = doc2.getRootElement();
                String action2 = root2.getName();
                switch (action2.toUpperCase()) {
                    case "CREATE":
                        break;
                    case "ATTACK":
                        sendP1.println(info2);
                        break;
                    case "MESSAGE":
                        sendP1.println(info2);
                        break;
                    case "EMPTY":
                        sendP1.println(info2);
                        break;
                    case "WIN":
                        sendP1.println(info2);
                        Element e = new Element("Lose");
                        XMLOutputter xm = new XMLOutputter(Format.getCompactFormat());
                        String m = xm.outputString(e);
                        m = m.replace("\n", "");
                        sendP2.println(m);
                        break;
                    case "INFORMRIGTH":
                        sendP1.println(info2);
                        break;
                    case "INFORMWRONG":
                        sendP1.println(info2);
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
