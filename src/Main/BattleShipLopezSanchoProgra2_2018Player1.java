package Main;

import GUI.GameWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BattleShipLopezSanchoProgra2_2018Player1 extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameWindow g = new GameWindow();
        Scene scene = new Scene(g.init(12000), 1300, 700);
        primaryStage.setTitle("JUEGO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
