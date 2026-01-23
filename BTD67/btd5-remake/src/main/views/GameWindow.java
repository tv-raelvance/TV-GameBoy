import javax.swing.JFrame;

public class GameWindow {
    private JFrame frame;

    public GameWindow() {
        frame = new JFrame("Balloon Tower Defense 5 Remake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void addGamePanel(GamePanel gamePanel) {
        frame.add(gamePanel);
    }
}