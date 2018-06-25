package Main;

import SocketServerAndClient.MyClient;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
//author @Kenneth Lopez Porras

public class BattleShipLopezSanchoProgra2_2018Player1 extends Application {

    public static void main(String[] args) throws IOException {
        MyClient myClient = new MyClient(12000);
        myClient.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
