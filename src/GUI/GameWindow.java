/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Ronald Emilio
 */
public class GameWindow {
    private Pane root;
    private Label lblName,lblChat,lblNamePlayer;
    private Button btnName,btnCheck;
    private TextField tfxName,tfxMessage;
    private TextArea taChat;
    
    
    public Pane init(){
        initializeComponents();
        locateComponents();
        addEventActions();
        addMainMenu();
        return root;
    }
    public void initializeComponents(){
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
    public void locateComponents(){
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
    public void addEventActions(){
        btnCheck.setOnAction((event) -> {
        String message="";
        String chat="";
        message = "Me: "+this.tfxMessage.getText();
        this.taChat.setText(this.taChat.getText()+message+"\n");
        
        this.tfxMessage.setText("");
        
        });
        btnName.setOnAction((event ->{
            String name = this.tfxName.getText();
            this.lblNamePlayer.setText(name);
            this.tfxName.setText("");
            
        }));
    }
    
    private void addMainMenu(){
        this.root.getChildren().clear();
        this.root.getChildren().addAll(this.lblName,this.btnName,this.tfxName,this.taChat,this.tfxMessage,this.btnCheck,this.lblChat,this.lblNamePlayer);
    }
}
