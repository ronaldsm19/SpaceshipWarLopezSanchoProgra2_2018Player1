package GUI;

import SocketServerAndClient.MyClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Ronald Emilio
 */
public class GameWindow extends Thread implements GUI {

    private Pane root;
    private Label lblName, lblChat, lblNamePlayer;
    private Button btnName, btnCheck;
    private TextField tfxName, tfxMessage;
    private TextArea taChat;
    private int socketPortNumber;

    public Pane init(int socketPort) {
        this.socketPortNumber = socketPort;

        initializeComponents();
        locateComponents();
        addEventActions();
        addMainMenu();
        this.start();
        return root;
    }

    public void initializeComponents() {
        root = new Pane();
        lblName = new Label("Insert the name of player");
        lblChat = new Label("Chat");
        lblNamePlayer = new Label("");
        tfxName = new TextField();
        tfxMessage = new TextField();
        btnName = new Button("Play game");
        btnCheck = new Button("OK");
        taChat = new TextArea();

    }

    public void locateComponents() {
        lblName.relocate(40, 50);
        lblChat.relocate(40, 200);
        tfxName.relocate(40, 80);
        tfxMessage.relocate(40, 660);
        btnName.relocate(200, 80);
        btnCheck.relocate(258, 660);
        taChat.relocate(40, 230);
        taChat.setPrefSize(250, 420);
        tfxMessage.setMinWidth(210);
        tfxMessage.setMaxWidth(210);
        tfxName.setPrefWidth(150);
        lblNamePlayer.relocate(650, 20);
    }

    public void addEventActions() {
        btnCheck.setOnAction((event) -> {
            String message = "";
            String chat = "";
            message = "Me: " + this.tfxMessage.getText();
            this.taChat.setText(this.taChat.getText() + message + "\n");

            this.tfxMessage.setText("");

        });
        btnName.setOnAction((event -> {
            String name = this.tfxName.getText();
            this.lblNamePlayer.setText(name);
            this.tfxName.setText("");

        }));
    }

    private void addMainMenu() {
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.lblName, this.btnName, this.tfxName, this.taChat, this.tfxMessage, this.btnCheck, this.lblChat, this.lblNamePlayer);
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
