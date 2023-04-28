package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.collisionOn = true;
        setDefaultValues();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        solidArea = new Rectangle();
        solidArea.x = 100;
        solidArea.y = 100;
        solidArea.height = gp.TileSize;
        solidArea.width = gp.TileSize;
        //tweak solidAre.x,y,height,width etc for collision.

    }
    public void update() {
        if (keyH.upPressed == true) {
            y -= speed;
            solidArea.y -= speed;
        }
        else if (keyH.downPressed == true) {
            y += speed;
            solidArea.y += speed;
        }
        else if (keyH.leftPressed == true) {
            x -= speed;
            solidArea.x -= speed;
        }
        else if (keyH.rightPressed == true) {
            x += speed;
            solidArea.x += speed;
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);

        g2.fillRect(x, y, gp.TileSize, gp.TileSize);
    }
}
