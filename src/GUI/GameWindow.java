package GUI;

import SocketServerAndClient.MyClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class GameWindow extends Thread implements GUI {

    private Pane root;
    private Label lblName, lblChat, lblNamePlayer, lblGridSize;
    private Button btnName, btnCheck;
    private TextField tfxName, tfxMessage, tfxSize;
    private TextArea taChat;
    private int socketPortNumber;
    private PrintStream send;
    private BufferedReader receive;
    private int clientNumber;
    private InetAddress address;
    private Socket socket;
    private GridGame gm;
    private GridPane gridPane;
    private ComboBox<String> combobox;

    @Override
    public Pane init(int socketPort) {
        this.socketPortNumber = socketPort;
        initializeComponents();
        locateComponents();
        addEventActions();
        addMainMenu();
        this.start();
        return root;
    }

    @Override
    public void initializeComponents() {
        this.gm = new GridGame();
        root = new Pane();
        this.combobox = new ComboBox();
        lblGridSize = new Label("Quantity of blocks");
        lblName = new Label("Insert the name of player");
        lblChat = new Label("Chat");
        lblNamePlayer = new Label("");
        tfxName = new TextField();
        tfxMessage = new TextField();
        tfxSize = new TextField();
        btnName = new Button("Play game");
        btnCheck = new Button("OK");
        taChat = new TextArea();
        this.combobox.getItems().add("3");
        this.combobox.getItems().add("5");
        this.taChat.setVisible(false);
        this.taChat.setEditable(false);
        this.lblChat.setVisible(false);
        this.tfxMessage.setVisible(false);
        this.btnCheck.setVisible(false);
        this.lblNamePlayer.setFont(new Font("Arial", 30));
        this.lblName.setFont(new Font("Arial", 15));
        this.lblGridSize.setFont(new Font("Arial", 15));
        try {
            address = InetAddress.getByName("192.168.1.18");
        } catch (UnknownHostException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket = new Socket(address, this.socketPortNumber);
        } catch (IOException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.send = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.receive = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
        } catch (IOException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.clientNumber = 2;
    }

    @Override
    public void locateComponents() {
        lblName.relocate(40, 50);
        lblChat.relocate(40, 200);
        lblGridSize.relocate(40, 110);
        tfxName.relocate(40, 80);
        tfxMessage.relocate(40, 660);
        btnName.relocate(200, 80);
        btnCheck.relocate(258, 660);
        taChat.relocate(40, 230);
        taChat.setPrefSize(250, 420);
        tfxMessage.setMinWidth(210);
        tfxMessage.setMaxWidth(210);
        tfxName.setPrefWidth(150);
        lblNamePlayer.relocate(1000, 20);
        combobox.relocate(40, 140);
    }

    @Override
    public void addEventActions() {
        btnCheck.setOnAction((event) -> {
            String message = "";
            String chat = "";
            message = this.tfxMessage.getText();
            this.taChat.setText(this.taChat.getText() + "Me: " + message + "\n");
            Element eCreate = new Element("Message");
            eCreate.setAttribute("M", this.tfxName.getText() + ": " + message);
            XMLOutputter xMLOutputter = new XMLOutputter(Format.getCompactFormat());
            String xmlStringStudentElement = xMLOutputter.outputString(eCreate);
            xmlStringStudentElement = xmlStringStudentElement.replace("\n", "");
            send.println(xmlStringStudentElement);
            this.btnCheck.setDisable(true);
            this.tfxMessage.setText("");

        });
        btnName.setOnAction((event -> {
            String name = this.tfxName.getText();
            String n = this.combobox.getValue();
            if (!name.equals("") && n != null) {
                this.lblNamePlayer.setText(name.toUpperCase());
                this.tfxName.setDisable(true);
                this.taChat.setVisible(true);
                this.lblChat.setVisible(true);
                this.tfxMessage.setVisible(true);
                this.btnCheck.setVisible(true);

                Image img = new Image("file:/C:/Users/gmg/Documents/NetBeansProjects/SpaceshipWarLopezSanchoProgra2_2018Player1/src/background.jpg");
                this.gridPane = gm.splitImage(img, Integer.parseInt(n));//, Integer.parseInt(this.tfxSize.getText()));
                this.gridPane.relocate(300, 100);
                this.root.getChildren().add(this.gridPane);
                this.btnName.setDisable(true);
                this.combobox.setDisable(true);
            } else {
                System.err.println("FALTAN DATOS");
            }
        }));
    }

    private void addMainMenu() {
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.lblName, this.btnName, this.tfxName, this.taChat, this.tfxMessage, this.btnCheck, this.lblChat, this.lblNamePlayer, this.lblGridSize, this.combobox);
    }

    @Override
    public void run() {
        try {
            while (true) {
                SAXBuilder saxBuilder = new SAXBuilder();
                StringReader stringReader = new StringReader(this.receive.readLine());
                Document doc = saxBuilder.build(stringReader);
                Element root = doc.getRootElement();
                String action = root.getName();
                switch (action.toUpperCase()) {
                    case "MESSAGE":
                        this.taChat.setText(this.taChat.getText() + root.getAttributeValue("M") + "\n");
                        this.btnCheck.setDisable(false);
                        break;
                    case "CHECK":
                        break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
