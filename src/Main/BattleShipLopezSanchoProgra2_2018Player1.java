package Main;

import GUI.GameWindow;
import SocketServerAndClient.MyClient;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//author @Kenneth Lopez Porras

public class BattleShipLopezSanchoProgra2_2018Player1 extends Application {

    public static void main(String[] args) throws IOException {
        MyClient myClient = new MyClient(12000);
        myClient.start();
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new GameWindow().init(), 1300, 700);

        primaryStage.setTitle("JUEGO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
