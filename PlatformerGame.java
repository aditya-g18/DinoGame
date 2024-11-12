import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlatformerGame extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int playerX = 50, playerY = 200, playerVelY = 0;
    private boolean onGround = true;

    public PlatformerGame() {
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerY += playerVelY;
        if (playerY >= 200) {
            playerY = 200;
            playerVelY = 0;
            onGround = true;
        } else {
            playerVelY += 1;
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, 30, 30);  // Player character
        g.setColor(Color.BLACK);
        g.fillRect(0, 230, 800, 50);  // Ground
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && onGround) {
            playerVelY = -10;
            onGround = false;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Platformer Game");
        PlatformerGame game = new PlatformerGame();
        frame.add(game);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
