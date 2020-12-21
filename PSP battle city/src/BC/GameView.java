package BC;

import javax.swing.JPanel;

public class GameView extends javax.swing.JFrame {

    public GameView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        gamePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle City");
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 500));

        gamePanel.setMinimumSize(new java.awt.Dimension(500, 500));
        gamePanel.setSize(new java.awt.Dimension(528, 448));
        gamePanel.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }

    private javax.swing.JPanel gamePanel;

    public JPanel getGamePanel() {
        return gamePanel;
    }

}
