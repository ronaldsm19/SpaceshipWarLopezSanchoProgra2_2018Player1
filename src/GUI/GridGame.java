/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;

/**
 *
 * @author Kenneth López Porras
 */
public class GridGame {

    private PixelReader pixelReaded;
    private WritableImage writableImage;

    public GridGame() {

    }

    public GridPane splitImage(Image a,int s) {
        //tamaño de los cuadros para cortar
        int squareX = 100;
        int squareY = 100;
        //nuevo gridpane para cargarlo y retornarlo
        GridPane g = new GridPane();
        int x = 0;
        int y = 0;

        //ciclos para recorrre la matriz
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                //controla si ya se llego al ultimo cuadro en el que se puede dividir la imagen

                //recibe los pixeles de la imagen
                this.pixelReaded = a.getPixelReader();

                //recorta del tamaño que se necesita
                this.writableImage = new WritableImage(this.pixelReaded, x, y, 100, 100);//parte la imagen en el x,y del tamaño ingresado 
                //lo agrega al visor de imagenes
                ImageView imageS = new ImageView(writableImage);
                //se crea el HBox
                HBox hBox_outter = new HBox();
                String style_outter = "-fx-border-color: black;"
                        + "-fx-border-width: 0.5;";
                hBox_outter.setStyle(style_outter);
                //se agrega el visor de imagenes al HBox
                hBox_outter.getChildren().add(imageS);
                //agregar el hbox con el cuadro de imagen
                g.add(hBox_outter, i, j);

                g.setOnMouseClicked((event) -> {
                    //click izquierdo para guardar el cuadrito seleccionado y luego colocarlo en el mosaico
                    if (event.getButton() == MouseButton.PRIMARY) {
                        int ejeX = (int) Math.floor(event.getX() / 100);
                        int ejeY = (int) Math.floor(event.getY() / 100);

                        System.out.println("x=" + ejeX + "   y=" + ejeY);
                    }
                });
                //aumenta y en el tamaño del cuadro que hay que cortar
                y += 100;
            }
            //aumenta x  en el tamaño del cuadro que hay que cortar
            x += 100;
            //vuelve Y al inicio para volver a recortar desde arriba hacia abajo
            y = 0;
        }
        //devuelve el gridpane dividido con la imagen
        return g;
    }
}
