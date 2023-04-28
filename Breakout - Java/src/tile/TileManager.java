package tile;

import main.GamePanel;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TileManager {
    GamePanel gp;
    private final Color[] color;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        color = new Color[] {Color.black, Color.gray, Color.white, Color.red, Color.green, Color.blue};
        Tile[] tile = new Tile[10]; //initalize new array of tiles with size 10.
    }
    public void draw(Graphics2D g2) {

    }
    public void loadMapData(Graphics2D g2) {
        //gives an array of 16 length and 12 height that is the mapdata from file

    }
    public void InitializeTiles() {
        tile[0] = new Tile();
        tile[0].type = 1; //type 1 tile should be grey wall
        tile[1] = new Tile();
        tile[1].type = 2; //type 2 tile should be white paddle
        tile[2] = new Tile();
        tile[2].type = 3; //type 3 should be red brick
        tile[2].breakable = true;
        tile[3] = new Tile();
        tile[3].type = 4; //type 4 should be green brick
        tile[3].breakable = true;
        tile[4] = new Tile();
        tile[4].type = 5; //type 5 should be blue brick
        tile[4].breakable = true;
        tile[5] = new Tile();
        tile[5].type = 0;
        tile[5].collision = false;

    }
    public Tile[][] intToTile() {
        InitializeTiles();
        int[] intMapData = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        Tile[][] mapData = new Tile[12][];
        Tile[] linemapData = new Tile[16];
        int x = 0;
        int i = 0;
        int j = 0;
        while (j < 12) {
            while (i < 16) {
                if (intMapData[x] == 1) {
                    linemapData[i] = tile[0];
                }
                else if (intMapData[x] == 2) {
                    linemapData[i] = tile[1];
                }
                else if (intMapData[x] == 3) {
                    linemapData[i] = tile[2];
                }
                else if (intMapData[x] == 4) {
                    linemapData[i] = tile[3];
                }
                else if (intMapData[x] == 5) {
                    linemapData[i] = tile[4];
                }
                else if (intMapData[x] == 0) {
                    linemapData[i] = tile[5];
                }
                i += 1;
                x += 1;
            }
            i = 0;
            mapData[j] = linemapData;
        }

        return mapData;
    }
    public void drawScene(Graphics2D g2) {
        //given an array, draw the scene.
        Tile[][] mapData = intToTile();
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            drawRectangle(gp, g2, x, y, mapData[row][col].type); // make tile classes and store the draw rectangle command in each
            col++;
            x += gp.TileSize;
            if (col == gp.maxScreenCol) {
                x = 0;
                col = 0;
                row++;
                y += gp.TileSize;
            }
        }
    }
    void drawRectangle(GamePanel gp, Graphics2D g2, int x, int y, int colornum) {
        Color col = color[colornum];
        g2.setColor(col);
        g2.fillRect(x, y, gp.TileSize, gp.TileSize);


    }
}
