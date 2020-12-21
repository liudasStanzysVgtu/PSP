package BC;

import ds.Block;
import ds.Brick;
import ds.Bullet;
import ds.Tank;
import ds.TankEnemy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Tank tank;

    private ArrayList<TankEnemy> enemy = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();

    private final int INIT_PLAYER_X = 10 * 16;
    private final int INIT_PLAYER_Y = (GameMap.map.length - 3) * 16;
    private final int B_WIDTH = GameMap.BOARD_WIDTH;
    private final int B_HEIGHT = GameMap.BOARD_HEIGHT;
    private final int DELAY = 15;
    public static boolean gameOver = false;

    public Board() {
        timer = new Timer(DELAY, this);
        timer.start();
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        tank = new Tank(INIT_PLAYER_X, INIT_PLAYER_Y, 4);

        initBlocks();
        CollisionUtility.loadCollisionUtility(blocks);
        BoardUtility.loadBoardUtility(enemy, blocks, tank);
    }

    public void initBlocks() {
        int[][] map = GameMap.getMap();
        int type;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                type = map[x][y];
                BlockTypeEnum bType = BlockTypeEnum.getTypeFromInt(type);
                switch (bType) {
                    case BRICK:
                        blocks.add(new Brick(y * 16, x * 16));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void checkGameOver() {
        if (tank.getHealth() < 0) {
            setEndGame();
        }
    }

    private void drawObjects(Graphics g) {
        for (TankEnemy tankEnemy : enemy) {
            if (tankEnemy.isVisible()) {
                g.drawImage(tankEnemy.getImage(), tankEnemy.getX(), tankEnemy.getY(), this);
            }
        }

        if (tank.isVisible()) {
            g.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
        }

        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.addAll(tank.getBullets());
        for (TankEnemy tankEnemy : enemy) {
            bullets.addAll(tankEnemy.getBullets());
        }

        for (Bullet b : bullets) {
            if (b.isVisible()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
        for (Block a : blocks) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }
    }

    public void updateSprites() {
        spawnTankEnemy();
        updateTank();
        updateTankEnemy();
        updateBullets();
        updateBlocks();
        updateBlocks();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            timer.stop();
            return;
        }
        updateSprites();
        checkCollisions();
        checkGameOver();

        repaint();
    }

    private void updateBlocks() {
        BoardUtility.updateBlocks();
    }

    private void updateTank() {
        BoardUtility.updateTank();
    }

    private void updateTankEnemy() {
        for (TankEnemy tankEnemy : enemy) {
            if (tankEnemy.isVisible()) {
                tankEnemy.action(this.tank);
            }
        }
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i).vis == false) {
                enemy.remove(i);
            }
        }
    }

    private void spawnTankEnemy() {
        if (enemy.size() < 4) {
            BoardUtility.spawnTankEnemy();
        } else {
            return;
        }
    }

    private void updateBulletsTank() {
        BoardUtility.updateBulletsTank();
    }

    private void updateBulletsTankEnemy() {
        BoardUtility.updateBulletsTankEnemy();
    }

    private void updateBullets() {
        updateBulletsTank();
        updateBulletsTankEnemy();
    }

    public void checkCollisions() {
        BoardUtility.checkCollisions();
    }

    public static void setEndGame() {
        System.out.println("Game Over!");
        gameOver = true;
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }
    }

}
