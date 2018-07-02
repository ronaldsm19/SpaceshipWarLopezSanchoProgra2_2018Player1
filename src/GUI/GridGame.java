/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Kenneth López Porras
 */
public class GridGame {

    private PixelReader pixelReaded;
    private WritableImage writableImage;
    private int squareX;
    private int squareY;

    public GridGame() {
        this.squareX = 145;
        this.squareY = 125;
    }

    public GridPane splitImage(Image a, int s) {
        //tamaño de los cuadros para cortar
        //nuevo gridpane para cargarlo y retornarlo
        GridPane g = new GridPane();
        if (s == 3) {
            this.squareX = 240;
            this.squareY = 207;
        }
        int x = 0;
        int y = 0;

        //ciclos para recorrre la matriz
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                //controla si ya se llego al ultimo cuadro en el que se puede dividir la imagen

                //recibe los pixeles de la imagen
                this.pixelReaded = a.getPixelReader();

                //recorta del tamaño que se necesita
                this.writableImage = new WritableImage(this.pixelReaded, x, y, squareX, squareY);//parte la imagen en el x,y del tamaño ingresado 
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

                //aumenta y en el tamaño del cuadro que hay que cortar
                y += squareY;
            }
            //aumenta x  en el tamaño del cuadro que hay que cortar
            x += squareX;
            //vuelve Y al inicio para volver a recortar desde arriba hacia abajo
            y = 0;
        }
        //devuelve el gridpane dividido con la imagen
        return g;
    }

    public GridPane zoneToAttack(Image a, int s) {
        //tamaño de los cuadros para cortar
        //nuevo gridpane para cargarlo y retornarlo
        GridPane g = new GridPane();
        int x = 0;
        int y = 0;
        this.setSquareX(40);
        this.setSquareY(40);
        //ciclos para recorrre la matriz
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                //controla si ya se llego al ultimo cuadro en el que se puede dividir la imagen

                //recibe los pixeles de la imagen
                this.pixelReaded = a.getPixelReader();

                //recorta del tamaño que se necesita
                this.writableImage = new WritableImage(this.pixelReaded, x, y, squareX, squareY);//parte la imagen en el x,y del tamaño ingresado 
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

                //aumenta y en el tamaño del cuadro que hay que cortar
                y += squareY;
            }
            //aumenta x  en el tamaño del cuadro que hay que cortar
            x += squareX;
            //vuelve Y al inicio para volver a recortar desde arriba hacia abajo
            y = 0;
        }
        //devuelve el gridpane dividido con la imagen
        return g;
    }

    /**
     * @param squareX the squareX to set
     */
    public void setSquareX(int squareX) {
        this.squareX = squareX;
    }

    /**
     * @param squareY the squareY to set
     */
    public void setSquareY(int squareY) {
        this.squareY = squareY;
    }
}
