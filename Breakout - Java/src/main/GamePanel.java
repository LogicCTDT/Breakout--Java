package main;

import javax.swing.JPanel;
import java.awt.*;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16; //Size of tile
    final int scale = 3; //scale of tile


    public final int TileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = TileSize * maxScreenCol;
    final int screenHeight = TileSize * maxScreenRow;

    //FPS
    int FPS = 60;
    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, KeyH);

    TileManager tileM = new TileManager(this);
    int playerX = 100;

    int playerY = 100;

    int playerSpeed = 4;

    int scene = 0;
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    //Contains GameLoop
    @Override
    public void run() {

        //Used nano seconds so 1 second is 1 billion nano seconds. Divide this by FPS.
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval; //after drawInterval passes update the loot
        while (gameThread != null) {

            System.out.println("The game loop is running");

            long currentTime = System.nanoTime();
            //Update Information
            update();

            //Draw Screen with New Information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); //time till next draw time.
                remainingTime = remainingTime / 1000000; //divide by 1 million to get time in milliseconds, since sleep
                //only accepts long number

                //just in case we used too much time
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; //set next draw time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public void update() {
        player.update();
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.drawScene(g2);



        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}

