package BC;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainWindow extends JPanel implements ActionListener, KeyListener {
    public GameView theView;

    public MainWindow(GameView theView) {
        this.theView = theView;
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        addTimer();
    }

    private void addTimer() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawString("By: Liudas Stanžys", 10, 30);

        g.setColor(Color.BLUE);
        setFont(getFont().deriveFont(Font.PLAIN, 35));
        g.drawString("Battle City", 1200 / 2 - 60,500 / 2);

        g.setColor(Color.WHITE);
        g.drawString("PRESS SPACE",1200 / 2 - 80,500 * 4 / 5 + 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == e.VK_ENTER) {
            theView.getGamePanel().removeAll();
            Board panel = new Board();
            theView.getGamePanel().add(panel);
            panel.requestFocusInWindow();
            theView.setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            loadBoard();
        }
    }

    public void loadBoard() {
        theView.getGamePanel().removeAll();
        Board panel = new Board();
        panel.revalidate();
        panel.repaint();
        theView.getGamePanel().add(panel);
        panel.requestFocusInWindow();
        theView.setVisible(true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            loadBoard();
        }
    }
}
