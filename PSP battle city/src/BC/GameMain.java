package BC;

public class GameMain {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameView theView = new GameView();
                MainWindow mainWindow = new MainWindow(theView);
                theView.getGamePanel().add(mainWindow);
                theView.setVisible(true);
            }
        });
    }

}
