package tvgameboy.games.btd5;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tvgameboy.shared.Game;

public final class BTD5Game implements Game {
    private List<Tower> towers = new ArrayList<>();
    private List<Balloon> balloons = new ArrayList<>();
    private int money = 5000;
    private int lives = 20;
    private int round = 1;
    private boolean roundStarted = false;
    private GamePath gamePath;

    @Override
    public JComponent getView(Runnable returnToMenu) {
        // Create game path with loops and curves
        gamePath = new GamePath();
        // Right side climb
        gamePath.addPoint(20, 200);
        gamePath.addPoint(80, 180);
        gamePath.addPoint(140, 120);
        gamePath.addPoint(180, 80);
        // First loop up
        gamePath.addPoint(220, 60);
        gamePath.addPoint(260, 50);
        gamePath.addPoint(300, 60);
        gamePath.addPoint(320, 100);
        // Down and across
        gamePath.addPoint(340, 150);
        gamePath.addPoint(380, 200);
        gamePath.addPoint(420, 220);
        // Second loop down
        gamePath.addPoint(460, 230);
        gamePath.addPoint(500, 240);
        gamePath.addPoint(540, 230);
        gamePath.addPoint(560, 190);
        // Final stretch
        gamePath.addPoint(580, 140);
        gamePath.addPoint(620, 100);
        gamePath.addPoint(660, 80);
        gamePath.addPoint(700, 100);
        gamePath.addPoint(740, 150);
        gamePath.addPoint(780, 200);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(5, 5, 5));

        // Top Bar with Info and Buttons
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(5, 5, 5));
        topBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        menuButton.setBackground(new Color(0, 150, 0));
        menuButton.setForeground(new Color(255, 255, 255));
        menuButton.setFocusPainted(false);
        menuButton.addActionListener(event -> returnToMenu.run());

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(5, 5, 5));
        JLabel infoLabel = new JLabel("Money: $" + money + " | Lives: " + lives + " | Round: " + round);
        infoLabel.setForeground(new Color(255, 255, 255));
        infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        infoPanel.add(infoLabel);

        JButton startRoundButton = new JButton("Start Round");
        startRoundButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        startRoundButton.setBackground(new Color(200, 0, 0));
        startRoundButton.setForeground(new Color(255, 255, 255));
        startRoundButton.setFocusPainted(false);
        startRoundButton.addActionListener(event -> {
            if (!roundStarted) {
                roundStarted = true;
                startRoundButton.setEnabled(false);
                infoLabel.setText("Money: $" + money + " | Lives: " + lives + " | Round: " + round + " (RUNNING)");
                // Simulate round end after 5 seconds
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                        roundStarted = false;
                        round++;
                        money += 200;
                        startRoundButton.setEnabled(true);
                        infoLabel.setText("Money: $" + money + " | Lives: " + lives + " | Round: " + round);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        });

        topBar.add(menuButton, BorderLayout.WEST);
        topBar.add(infoPanel, BorderLayout.CENTER);
        topBar.add(startRoundButton, BorderLayout.EAST);

        // Game Canvas
        GameCanvas gameCanvas = new GameCanvas(this);

        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(gameCanvas, BorderLayout.CENTER);
        return mainPanel;
    }

    public void addTower(int x, int y) {
        if (money >= 1000) {
            towers.add(new Tower(x, y));
            money -= 1000;
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Balloon> getBalloons() {
        return balloons;
    }

    public GamePath getPath() {
        return gamePath;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int amount) {
        this.money = amount;
    }

    private static class GameCanvas extends JPanel {
        private BTD5Game game;

        GameCanvas(BTD5Game game) {
            this.game = game;
            setBackground(new Color(50, 100, 50));
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game.addTower(e.getX(), e.getY());
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw path with smooth curves
            if (game.gamePath != null && game.gamePath.getPoints().size() > 1) {
                List<int[]> points = game.gamePath.getPoints();
                
                // Create smooth path using quadratic Bezier curves
                Path2D path = new Path2D.Double();
                path.moveTo(points.get(0)[0], points.get(0)[1]);
                
                for (int i = 1; i < points.size(); i++) {
                    int[] p0 = points.get(i - 1);
                    int[] p1 = points.get(i);
                    
                    if (i < points.size() - 1) {
                        int[] p2 = points.get(i + 1);
                        // Calculate control point for smooth curve
                        int cpx = p1[0];
                        int cpy = p1[1];
                        // Endpoint of quadratic curve (midpoint between current and next)
                        int endx = (p1[0] + p2[0]) / 2;
                        int endy = (p1[1] + p2[1]) / 2;
                        path.quadTo(cpx, cpy, endx, endy);
                    } else {
                        // Last point - straight line to end
                        path.lineTo(p1[0], p1[1]);
                    }
                }
                
                g2d.setColor(new Color(139, 90, 43)); // Brown road color
                g2d.setStroke(new BasicStroke(40, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.draw(path);
            }
            
            // Draw towers
            g.setColor(new Color(200, 150, 0));
            for (Tower tower : game.getTowers()) {
                g.fillOval(tower.getX() - 15, tower.getY() - 15, 30, 30);
                g.setColor(new Color(255, 200, 0));
                g.drawOval(tower.getX() - 15, tower.getY() - 15, 30, 30);
                g.setColor(new Color(255, 200, 0));
                g.drawString("M", tower.getX() - 3, tower.getY() + 5);
                g.setColor(new Color(200, 150, 0));
            }
            
            // Draw instruction text
            g.setColor(new Color(200, 200, 200));
            g.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g.drawString("Click to place Monkey ($1000)", 10, getHeight() - 10);
        }
    }

    private static class GamePath {
        private List<int[]> points = new ArrayList<>();

        void addPoint(int x, int y) {
            points.add(new int[]{x, y});
        }

        List<int[]> getPoints() {
            return points;
        }
    }

    private static class Tower {
        private int x;
        private int y;

        Tower(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static class Balloon {
        private int x;
        private int y;
        private int pathIndex;
        private double progress; // 0 to 1 along current segment

        Balloon() {
            this.x = 0;
            this.y = 150;
            this.pathIndex = 0;
            this.progress = 0;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
