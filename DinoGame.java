import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DinoGame extends JPanel implements ActionListener, KeyListener {

    private int dinoX = 50, dinoY = 250, dinoVelY = 0;
    private int dinoWidth = 30, dinoHeight = 30;
    private boolean onGround = true;
    private Timer timer;
    private int cactusX = 800, cactusWidth = 20, cactusHeight = 30;
    private boolean gameOver = false;

    public DinoGame() {
        timer = new Timer(20, this); // Game update interval
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.CYAN); // Set background color to make it more visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Dino movement logic
            dinoY += dinoVelY;

            if (dinoY >= 250) {
                dinoY = 250;
                dinoVelY = 0;
                onGround = true;
            } else {
                dinoVelY += 1;  // Gravity effect
            }

            // Cactus movement logic
            cactusX -= 5; // Move cactus from right to left
            if (cactusX < 0) {
                cactusX = 800;  // Reset cactus position once it goes off-screen
            }

            // Collision detection between Dino and Cactus
            if (dinoX + dinoWidth > cactusX && dinoX < cactusX + cactusWidth &&
                dinoY + dinoHeight > 250) {
                if (cactusX > 40 && cactusX < 80) {
                    gameOver = true;  // Game Over if dino hits the cactus
                }
            }

            repaint();  // Redraw the game screen
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            // Drawing the dinosaur with a different color
            g.setColor(Color.GREEN);
            g.fillRect(dinoX, dinoY, dinoWidth, dinoHeight);  // Draw Dino

            // Drawing the cactus with a brown color
            g.setColor(Color.RED);
            g.fillRect(cactusX, 250, cactusWidth, cactusHeight);  // Draw Cactus

            // Display game instructions
            g.setColor(Color.BLACK);
            g.drawString("Press Space to Jump", 300, 50);  
        } else {
            g.setColor(Color.RED);
            g.drawString("GAME OVER! Press 'R' to Restart", 250, 150);  // Game Over message
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && onGround) {
            dinoVelY = -10;  // Make the dino jump
            onGround = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();  // Restart the game on pressing 'R'
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    // Restart the game by resetting values
    private void restartGame() {
        dinoX = 50;
        dinoY = 250;
        dinoVelY = 0;
        cactusX = 800;
        gameOver = false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dino Game");
        DinoGame game = new DinoGame();
        frame.add(game);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
