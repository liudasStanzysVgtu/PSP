package BC;

import ds.Block;
import ds.Bullet;
import ds.Tank;
import ds.TankEnemy;
import java.util.ArrayList;

public class BoardUtility {

    private static ArrayList<TankEnemy> enemy = new ArrayList<>();
    private static ArrayList<Block> blocks = new ArrayList<>();
    private static Tank tank;

    public static void loadBoardUtility(ArrayList<TankEnemy> enemy1, ArrayList<Block> blocks1, Tank tank1) {
        enemy = enemy1;
        blocks = blocks1;
        tank = tank1;
    }

    public static void spawnTankEnemy() {
        TankEnemy tankEnemy = new TankEnemy(14 * 16, 1 * 16);
        enemy.add(tankEnemy);
    }

    public static void updateBulletsTankEnemy() {
        for (TankEnemy tankEnemy : enemy) {
            ArrayList<Bullet> bullets = tankEnemy.getBullets();
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                if (b.isVisible()) {
                    b.move();
                } else if (b.isVisible() == false) {
                    bullets.remove(i);
                }
            }
        }
    }

    public static void updateBulletsTank() {
        ArrayList<Bullet> bullets = tank.getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            if (b.isVisible()) {
                b.move();
            } else if (b.isVisible() == false) {
                bullets.remove(i);
            }
        }
    }

    public static void updateBlocks() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.isVisible() == false) {
                blocks.remove(i);
            }
        }
    }

    public static void updateTank() {
        if (tank.isVisible()) {
            tank.move();
        }
    }

    public static void checkCollisions() {
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.addAll(tank.getBullets());
        for (TankEnemy tankEnemy : enemy) {
            bullets.addAll(tankEnemy.getBullets());
        }
        CollisionUtility.checkCollisionBulletsBlocks(bullets, blocks);
        CollisionUtility.checkCollisionBulletsTank(bullets, tank);
        CollisionUtility.checkCollisionBulletsTankEnemy(bullets, enemy);
        CollisionUtility.checkCollisionTankTankEnemy(enemy, tank);
    }
}
