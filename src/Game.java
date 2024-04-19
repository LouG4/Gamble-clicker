import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements Runnable, MouseListener {
    private int coinCount;
    private ImageIcon coinIcon;

    public Game() {
        coinCount = 0;
        coinIcon = new ImageIcon("chip.png"); // Replace "chip.png" with the path to your coin image

        new Thread(this).start();
        this.setFocusable(true);
        this.addMouseListener(this);
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(100); // Adjust sleep time as needed
                repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw coin count
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Coins: " + coinCount, 20, 30);

        // Draw coin icon
        g2d.drawImage(coinIcon.getImage(), getWidth() / 2 - 25, getHeight() / 2 - 25, 100, 100, this);
    }

    public void mouseClicked(MouseEvent e) {
        // Check if the mouse click is on the coin icon
        int mouseX = e.getX();
        int mouseY = e.getY();
        int coinX = getWidth() / 2 - 25; // X-coordinate of the coin icon
        int coinY = getHeight() / 2 - 25; // Y-coordinate of the coin icon

        if (mouseX >= coinX && mouseX <= coinX + 50 && mouseY >= coinY && mouseY <= coinY + 50) {
            coinCount++;
        }
    }

    // Unused mouse listener methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Coin Clicker Game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
