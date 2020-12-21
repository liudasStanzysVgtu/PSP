package ds;

import BC.CollisionUtility;
import BC.GameMap;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class TankEnemy extends Sprite {
    private final int BOARD_WIDTH = GameMap.BOARD_WIDTH;
    private final int BOARD_HEIGHT = GameMap.BOARD_HEIGHT;
    private ArrayList<Bullet> bullets;

    private int dx, dy;
    public int direction;
    private int dirUpdateInterval;
    private int dirTimer = 0;
    private int fireTimer = 0;
    private int fireNewInterval;
    private int health;

    private double speed;

    private String imageUp;
    private String imageDown;
    private String imageLeft;
    private String imageRight;

    public TankEnemy(int x, int y) {
        super(x, y);
        bullets = new ArrayList<>();
        direction = 0;
        this.vis = true;
        this.setUp();
        this.imageSetUp();
    }

    private void setUp() {
        this.health = 1;
        this.speed = 2;
        dirUpdateInterval = 30;
        fireNewInterval = 75;
    }

    private void imageSetUp() {
            loadImage("image/tank_enemy_up.png");
            getImageDimensions();
            this.imageUp = "image/tank_enemy_up.png";
            this.imageDown = "image/tank_enemy_down.png";
            this.imageLeft = "image/tank_enemy_left.png";
            this.imageRight = "image/tank_enemy_right.png";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void decreaseHP() {
        this.health -= 1;
    }

    public int getHealth() {
        return health;
    }

    public void action(Tank tank) {
        Random randomDir = new Random();
        if (this.dirTimer >= this.dirUpdateInterval) {
            int random = randomDir.nextInt(20);
            if (random % 4 == 0) {
                randomDir();
            } else {
                toTankDir(tank);
            }
            this.dirTimer = 0;
        } else {
            this.dirTimer++;
        }
        this.move();
        Rectangle theTank = new Rectangle(x + dx, y + dy, width, height);

        if (CollisionUtility.checkCollisionTankBlocks(theTank) == true) {
            if (randomDir.nextBoolean() && this.fireTimer < 3) {
                this.fire();
                this.fireTimer++;
            }
        }
        if (this.fireTimer >= this.fireNewInterval) {
            this.fire();
            this.fireTimer = 0;
        } else {
            this.fireTimer++;
        }
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

    public void fire() {
        Bullet bullet;
        switch (direction) {
            case 0:
                bullet = new Bullet(x + width / 3, y, 0, true);
                break;
            case 1:
                bullet = new Bullet(x + width, y + height / 3, 1, true);
                break;
            case 2:
                bullet = new Bullet(x + width / 3, y + height, 2, true);
                break;
            default:
                bullet = new Bullet(x, y + height / 3, 3, true);
                break;
        }
        bullets.add(bullet);
    }

    public void randomDir() {
        Random randomDir = new Random();
        this.direction = randomDir.nextInt(4);
        dirUpdate();
    }

    public void toTankDir(Tank tank) {
        int tankX = tank.getX();
        int tankY = tank.getY();
        Random randomDir = new Random();
        if (randomDir.nextBoolean()) {
            if (this.getY() > tankY) {
                this.direction = 0;
            } else {
                this.direction = 2;
            }
        } else if (this.getX() > tankX) {
            this.direction = 3;
        } else if (this.getX() < tankX) {
            this.direction = 1;
        } else {
            this.direction = 3;
        }
        dirUpdate();
    }

    private void dirUpdate() {
        ImageIcon ii;
        switch (this.direction) {
            case 0:
                ii = new ImageIcon(this.imageUp);
                image = ii.getImage();
                this.dx = (int) (0 * this.speed);
                this.dy = (int) (-1 * this.speed);
                break;
            case 1:
                ii = new ImageIcon(this.imageRight);
                image = ii.getImage();
                this.dx = (int) (1 * this.speed);
                this.dy = (int) (0 * this.speed);
                break;
            case 2:
                ii = new ImageIcon(this.imageDown);
                image = ii.getImage();
                this.dx = (int) (0 * this.speed);
                this.dy = (int) (1 * this.speed);
                break;
            case 3:
                ii = new ImageIcon(this.imageLeft);
                image = ii.getImage();
                this.dx = (int) (-1 * this.speed);
                this.dy = (int) (0 * this.speed);
                break;
        }
    }
}
