package ds;

import BC.GameMap;

public class Bullet extends Sprite {
    private final int BULLET_SPEED = 3;
    private final int BOARD_WIDTH = GameMap.BOARD_WIDTH;
    private final int BOARD_HEIGHT = GameMap.BOARD_HEIGHT;
    private int direction;
    public boolean isEnemy;

    public Bullet(int x, int y, int direction, boolean Enemy) {
        super(x, y);
        this.direction = direction;
        loadImage("image/bullet.png");
        isEnemy = Enemy;
        getImageDimensions();
    }

    public void move() {
        if (direction == 0) {
            y -= BULLET_SPEED;
        } else if (direction == 1) {
            x += BULLET_SPEED;
        } else if (direction == 2) {
            y += BULLET_SPEED;
        } else if (direction == 3) {
            x -= BULLET_SPEED;
        }
        if (x > BOARD_WIDTH + 100 + width) {
            vis = false;
        }
        if (x < -width - 100) {
            vis = false;
        }
        if (y > BOARD_HEIGHT + height + 100) {
            vis = false;
        }
        if (y < -height - 100) {
            vis = false;
        }
    }
}
