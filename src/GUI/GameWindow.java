package GUI;

import SocketServerAndClient.MyClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class GameWindow extends Thread implements GUI {

    private Pane root;
    private Label lblName, lblChat, lblNamePlayer, lblGridSize, lblX, lblY, lblXtoAttack, lblYtoAttack, lblStatus;
    private Button btnName, btnCheck, btnAttack;
    private TextField tfxName, tfxMessage;
    private TextArea taChat;
    private int socketPortNumber, countM, countD, gridSize, xM, yM, lifeCount, pixelX, pixelY;//countM contador para la nave madre, countD para las hijas
    private PrintStream send;
    private BufferedReader receive;
    private InetAddress address;
    private Socket socket;
    private GridGame gm;
    private GridPane gridPane, gridPaneToAttack;
    private ComboBox<String> combobox;
    private int placeOfShips[][];
    private Canvas canvas;
    private GraphicsContext gc;

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
        this.canvas = new Canvas(725, 625);
        this.lifeCount = 2;
        this.countD = 0;
        this.countD = 0;
        this.xM = 0;
        this.yM = 0;
        root = new Pane();
        this.combobox = new ComboBox();
        lblGridSize = new Label("Quantity of blocks");
        lblName = new Label("Insert the name of player");
        lblChat = new Label("Chat");
        lblNamePlayer = new Label("");
        tfxName = new TextField();
        tfxMessage = new TextField();
        lblXtoAttack = new Label();
        lblYtoAttack = new Label();
        lblStatus = new Label();
        lblX = new Label("X");
        lblY = new Label("Y");
        btnName = new Button("Play game");
        btnCheck = new Button("OK");
        btnAttack = new Button("Attack");
        taChat = new TextArea();
        this.combobox.getItems().add("3");
        this.combobox.getItems().add("5");
        this.taChat.setVisible(false);
        this.taChat.setEditable(false);
        this.lblChat.setVisible(false);
        this.tfxMessage.setVisible(false);
        lblXtoAttack.setVisible(false);
        lblYtoAttack.setVisible(false);
        lblX.setVisible(false);
        lblY.setVisible(false);
        this.btnCheck.setVisible(false);
        this.btnAttack.setVisible(false);
        this.lblNamePlayer.setFont(new Font("Arial", 30));
        this.lblStatus.setFont(new Font("Arial", 30));
        this.lblName.setFont(new Font("Arial", 15));
        this.lblGridSize.setFont(new Font("Arial", 15));
        try {
            address = InetAddress.getByName("192.168.43.93");
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
        btnAttack.relocate(1150, 300);
        taChat.relocate(40, 230);
        taChat.setPrefSize(250, 420);
        tfxMessage.setMinWidth(210);
        tfxMessage.setMaxWidth(210);
        tfxName.setPrefWidth(150);
        combobox.relocate(40, 140);
        this.lblXtoAttack.relocate(1060, 300);
        this.lblYtoAttack.relocate(1110, 300);
        this.lblXtoAttack.setPrefSize(40, 20);
        this.lblYtoAttack.setPrefSize(40, 20);
        this.lblX.relocate(1060, 285);
        this.lblY.relocate(1110, 285);
        lblStatus.relocate(700, 0);
        lblNamePlayer.relocate(500, 0);
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
            this.tfxMessage.setText("");

        });
        btnName.setOnAction((event -> {
            String name = this.tfxName.getText();
            String n = this.combobox.getValue();
            if (!name.equals("") && n != null) {
                int s = Integer.parseInt(n);
                this.placeOfShips = new int[s][s];
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++) {
                        this.placeOfShips[i][j] = 0;
                    }
                }
                this.gridSize = Integer.parseInt(n);
                this.lblNamePlayer.setText(name.toUpperCase());
                this.tfxName.setDisable(true);
                this.taChat.setVisible(true);
                this.lblChat.setVisible(true);
                this.tfxMessage.setVisible(true);
                this.btnCheck.setVisible(true);
                this.btnAttack.setVisible(true);
                Image img = new Image("file:/C:/Users/gmg/Desktop/Sprites/background.jpg");
                this.gridPane = gm.splitImage(img, Integer.parseInt(n));//, Integer.parseInt(this.tfxSize.getText()));
                this.gridPaneToAttack = gm.zoneToAttack(img, Integer.parseInt(n));
                lblXtoAttack.setVisible(true);
                lblYtoAttack.setVisible(true);
                this.lblX.setVisible(true);
                this.lblY.setVisible(true);
                if (this.gridSize == 5) {
                    this.gridPane.relocate(300, 50);
                    this.canvas.relocate(300, 50);
                    this.gridPaneToAttack.relocate(1050, 50);
                } else {
                    this.gridPane.relocate(300, 50);
                    this.canvas.relocate(300, 50);
                    this.gridPaneToAttack.relocate(1050, 300);
                    this.lblXtoAttack.relocate(1060, 450);
                    this.lblYtoAttack.relocate(1110, 450);
                    this.lblX.relocate(1060, 435);
                    this.lblY.relocate(1110, 435);
                    this.btnAttack.relocate(1150, 450);
                    lblStatus.relocate(700, 100);
                    lblNamePlayer.relocate(500, 100);
                }
                this.gc = this.canvas.getGraphicsContext2D();
                this.root.getChildren().addAll(this.gridPane, this.gridPaneToAttack, this.lblXtoAttack, this.lblYtoAttack, this.lblX, this.lblY, this.btnAttack, this.canvas);
                this.btnName.setDisable(true);
                this.combobox.setDisable(true);
                canvas.setOnMouseClicked((eventG) -> {
                    //  if (eventG.getButton() == MouseButton.PRIMARY) {
                    int ejeX = (int) Math.floor(eventG.getX() - 25);
                    int ejeY = (int) Math.floor(eventG.getY() - 28);
                    this.pixelX = 145;
                    this.pixelY = 125;
                    if (this.gridSize == 3) {
                        this.pixelX = 240;
                        this.pixelY = 207;
                    }
                    int x = (int) Math.floor(eventG.getX() / this.pixelX);
                    int y = (int) Math.floor(eventG.getY() / this.pixelY);
                    System.out.println("x: " + x + "  y: " + y);
                    if (this.countM == 0) {
                        this.draw(gc, ejeX, ejeY, new Image("file:/C:/Users/gmg/Desktop/Sprites/madre.png"));
                        this.xM = x;
                        this.yM = y;
                        this.placeOfShips[y][x] = 2;
                        ++countM;
                        this.lblStatus.setText("preparing");
                    } else if (countD < this.gridSize) {
                        this.draw(gc, ejeX, ejeY, new Image("file:/C:/Users/gmg/Desktop/Sprites/hija.png"));
                        this.placeOfShips[y][x] = 1;
                        ++countD;
                        if (countD == this.gridSize) {
                            this.lblStatus.setText("playing");
                        }
                    }
                });
                this.gridPaneToAttack.setOnMouseClicked((eventM) -> {
                    if (eventM.getButton() == MouseButton.PRIMARY) {
                        int ejeX = (int) Math.floor(eventM.getX() / 40);
                        int ejeY = (int) Math.floor(eventM.getY() / 40);
                        this.lblXtoAttack.setText(String.valueOf(ejeX));
                        this.lblYtoAttack.setText(String.valueOf(ejeY));
                    }
                });
            } else {
                System.err.println("FALTAN DATOS");
            }
        }));
        this.btnAttack.setOnAction((event -> {
            this.lblStatus.setText("waiting...");
            int xS = Integer.parseInt(this.lblXtoAttack.getText()) * this.pixelX;
            int yS = Integer.parseInt(this.lblYtoAttack.getText()) * this.pixelY;

            Element eCreate = new Element("Attack");
            eCreate.setAttribute("X", String.valueOf(xS));
            eCreate.setAttribute("Y", String.valueOf(yS));
            XMLOutputter xMLOutputter = new XMLOutputter(Format.getCompactFormat());
            String xmlStringStudentElement = xMLOutputter.outputString(eCreate);
            xmlStringStudentElement = xmlStringStudentElement.replace("\n", "");
            send.println(xmlStringStudentElement);

        }));

    }

    private void addMainMenu() {
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.lblName, this.btnName, this.tfxName, this.taChat, this.tfxMessage, this.btnCheck, this.lblChat, this.lblNamePlayer, this.lblGridSize, this.combobox, this.lblStatus);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Element eCreate = new Element("Empty");
                XMLOutputter xMLOutputter = new XMLOutputter(Format.getCompactFormat());
                String xmlStringStudentElement = xMLOutputter.outputString(eCreate);
                xmlStringStudentElement = xmlStringStudentElement.replace("\n", "");
                send.println(xmlStringStudentElement);

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
                    case "ATTACK":
                        int ejeX = Integer.parseInt(root.getAttributeValue("X"));
                        int ejeY = Integer.parseInt(root.getAttributeValue("Y"));
                        this.draw(gc, ejeX, ejeY, new Image("file:/C:/Users/gmg/Desktop/Sprites/clean.png"));
                        break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MyClient.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit() {
        System.out.println("entro a editar");
        this.lblName.setText("hilo");
        System.out.println("editó");
    }

    private void draw(GraphicsContext gc, int x, int y, Image a) {
        gc.clearRect(x, y, this.pixelX, this.pixelY);
        gc.drawImage(a, x, y);
    }
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}
