package BC;

import ds.Block;
import ds.Bullet;
import ds.Tank;
import ds.TankEnemy;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionUtility {
    private static ArrayList<Block> blocks;
    private static int[] enemyTankNum = {0, 0, 0, 0};

    static public void loadCollisionUtility(ArrayList<Block> inblocks) {
        blocks = inblocks;
    }

    public static void CollisionBulletsBlocksHelper(Bullet bullet, Block block) {
        BlockTypeEnum type = BlockTypeEnum.getTypeFromInt(block.getType());
        if (type.equals(BlockTypeEnum.BRICK)) {
            block.lowerHealth();
            bullet.setVisible(false);
        }
        if (block.getHealth() == 0) {
            block.vis = false;
        }
        if (block.getHealth() == 0) {
            block.vis = false;
        }
    }

    public static boolean checkCollisionTankBlocks(Rectangle r3) {
        for (Block block : blocks) {
            Rectangle r2 = block.getBounds();
            if (r3.intersects(r2)) {
                return true;
            }
        }
        return false;
    }

    public static void checkCollisionBulletsBlocks(ArrayList<Bullet> bullets, ArrayList<Block> blocks) {
        for (int x = 0; x < bullets.size(); x++) {
            Bullet b = bullets.get(x);
            Rectangle r1 = b.getBounds();

            for (int i = 0; i < blocks.size(); i++) {
                Block aBlock = blocks.get(i);
                Rectangle r2 = aBlock.getBounds();

                if (r1.intersects(r2)) {
                    CollisionBulletsBlocksHelper(b, aBlock);
                }
            }
        }
    }

    public static void checkCollisionBulletsTank(ArrayList<Bullet> bullets, Tank tank) {
        Rectangle r2 = tank.getBounds();
        for (int x = 0; x < bullets.size(); x++) {
            Bullet b = bullets.get(x);
            Rectangle r1 = b.getBounds();
            if (r1.intersects(r2) && b.isEnemy == true) {
                b.vis = false;
                tank.downHealth();
                resetTankPosition(tank, 1);
            }
        }
    }

    public static void checkCollisionBulletsTankEnemy(ArrayList<Bullet> bullets, ArrayList<TankEnemy> tankEnemies) {
        for (int x = 0; x < bullets.size(); x++) {
            Bullet b = bullets.get(x);
            Rectangle r1 = b.getBounds();

            for (int i = 0; i < tankEnemies.size(); i++) {
                TankEnemy tankEnemy = tankEnemies.get(i);
                Rectangle r2 = tankEnemy.getBounds();

                if (r1.intersects(r2) && b.isEnemy == false) {
                    tankEnemy.decreaseHP();
                    b.vis = false;
                    if (tankEnemy.getHealth() < 1) {
                        incrementNum();
                        tankEnemy.vis = false;
                    }
                }
            }
        }
    }

    public static void incrementNum() {
        enemyTankNum[0] += 1;
    }

    public static void resetTankPosition(Tank tank, int type) {
        tank.x = 10 * 16;
        tank.y = (GameMap.map.length - 3) * 16;
    }

    public static void checkCollisionTankTankEnemy(ArrayList<TankEnemy> tankEnemies, Tank atank) {
        Rectangle r1 = atank.getBounds();
        for (int i = 0; i < tankEnemies.size(); i++) {
            TankEnemy tankEnemy = tankEnemies.get(i);
            Rectangle r2 = tankEnemy.getBounds();
            if (r1.intersects(r2)) {
                atank.downHealth();
                resetTankPosition(atank, 1);
            }
        }
    }
}
