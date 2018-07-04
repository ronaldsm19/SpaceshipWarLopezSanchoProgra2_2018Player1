package client;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile extends Thread {

    private int x, y;
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    GraphicsContext g;
    private boolean exe = true;
    int type;
    int yDes;

    public Missile(int x, int y, int type, int yDes) {
        this.type = type;
        this.yDes = yDes;
        this.x = x;
        this.y = y;
        this.image = new Image("file:/C:/Users/gmg/Desktop/Sprites/fireBall.png");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(GraphicsContext g) {
        this.g = g;
        this.g.clearRect(getX(), getY(), 10, 10);
        this.g.drawImage(getImage(), getX(), getY());

    }

    public boolean isExe() {
        return exe;
    }

    @Override
    public void run() {
        while (true) {
            if (type == 0) {
                if (y != 0) {
                    this.y++;
                } else {
                    this.exe = false;
                }
            } else {
                if (y != 350) {
                    this.y--;

                } else {
                    this.exe = false;
                }
            }
        }
    }

}
