package ds;

import BC.CollisionUtility;
import BC.GameMap;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Tank extends Sprite {

    private final int BOARD_WIDTH = GameMap.BOARD_WIDTH;
    private final int BOARD_HEIGHT = GameMap.BOARD_HEIGHT;
    private int dx;
    private int dy;
    private ArrayList<Bullet> bullets;
    public int direction;
    private long lastFired = 0;
    private int health = 2;

    public int getHealth() {
        return health;
    }

    public void downHealth() {
        this.health -= 1;
    }

    public Tank(int x, int y, int lives) {
        super(x, y);
        loadImage("image/player_tank_up.png");
        getImageDimensions();
        bullets = new ArrayList<>();
        direction = 0;
        this.health = lives;
    }

    public void move() {

        Rectangle theTank = new Rectangle(x + dx, y + dy, width, height);

        if (CollisionUtility.checkCollisionTankBlocks(theTank) == false) {
            x += dx;
            y += dy;
        }

        if (x > BOARD_WIDTH - width) {
            x = BOARD_WIDTH - width;
        }

        if (y > BOARD_HEIGHT - height) {
            y = BOARD_HEIGHT - height;
        }
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void fire() {
        Bullet aBullet;
        if (direction == 0) {
            aBullet = new Bullet(x + width / 3, y, 0, false);
        } else if (direction == 1) {
            aBullet = new Bullet(x + width - 3, y + height / 3, 1, false);
        } else if (direction == 2) {
            aBullet = new Bullet(x + width / 3, (y + height) - 3, 2, false);
        } else {
            aBullet = new Bullet(x, y + height / 3, 3, false);
        }
        bullets.add(aBullet);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {
        int time;
        int key = e.getKeyCode();
        time = 700;
        if (key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - lastFired) > time) {
            fire();
            lastFired = System.currentTimeMillis();
        } else if (key == KeyEvent.VK_LEFT) {
            dy = 0;
            dx = -2;
            ImageIcon ii = new ImageIcon("image/player_tank_left.png");
            image = ii.getImage();
            direction = 3;
        } else if (key == KeyEvent.VK_RIGHT) {
            dy = 0;
            dx = 2;
            ImageIcon ii = new ImageIcon("image/player_tank_right.png");
            image = ii.getImage();
            direction = 1;
        } else if (key == KeyEvent.VK_UP) {
            ImageIcon ii = new ImageIcon("image/player_tank_up.png");
            image = ii.getImage();
            dx = 0;
            dy = -2;
            direction = 0;
        } else if (key == KeyEvent.VK_DOWN) {
            ImageIcon ii = new ImageIcon("image/player_tank_down.png");
            image = ii.getImage();
            dy = 2;
            dx = 0;
            direction = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
