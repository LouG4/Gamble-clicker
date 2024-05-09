import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements Runnable, MouseListener {
    private int coinCount;
    private ImageIcon coinIcon;
    private Sound music;
    private ImageIcon background;
    private ImageIcon background2;

    private int GS;
    private ImageIcon SLOT;
    public Game() {
        background = new ImageIcon("Table.jpg");
    	coinCount = 0;
        coinIcon = new ImageIcon("chipp.png"); 
        background2 = new ImageIcon("SLOT.jpg");
        GS=0;
        new Thread(this).start();
        this.setFocusable(true);
        this.addMouseListener(this);
    }
    // switch case for screens
    public void Screen (Graphics g2d) {
    	switch(GS) {
    	
    	case 0:
    		g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    		 g2d.drawImage(coinIcon.getImage(), getWidth() / 2- 25, getHeight() / 2 - 25, 150, 150, this);
    	     g2d.setColor(Color.RED);
    	     g2d.fillRect(20,60,100,50); // button 1
    	     g2d.setFont(new Font("Arial", Font.BOLD, 20));
    	     g2d.setColor(Color.WHITE);
    	     g2d.drawString("gamble", 32, 90);
    	     g2d.setFont(new Font("Arial", Font.BOLD, 20));
    	        g2d.drawString("Coins: " + coinCount, 20, 30);
    		break;
    	
    	case 1:
    		g2d.clearRect(0, 0, getSize().width, getSize().height); 
    		g2d.drawImage(background2.getImage(), 0, 0, getWidth(), getHeight(), this);

    		
    		break;
    	}
    }
    public void run() {
        try {
            while (true) {
                Thread.sleep(100); 
                repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        // Draw coin icon
        Screen(g2d);
    }
    public void mouseClicked(MouseEvent e) {
        // Check if the mouse click is on the coin icon
        int mouseX = e.getX();
        int mouseY = e.getY();
        int coinX = getWidth() / 2 - 25; // X-coordinate of the coin icon
        int coinY = getHeight() / 2 - 25; // Y-coordinate of the coin icon
        
        int buttonX = 20; // X-coordinate of the button
        int buttonY = 60; // Y-coordinate of the button
        int buttonWidth = 100; // Width of the button
        int buttonHeight = 50; // Height of the button
   
        if (mouseX >= coinX && mouseX <= coinX + 150 && mouseY >= coinY && mouseY <= coinY + 150) {
            coinCount++;
        }
        
        if (mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
            GS = 1; // Transition to case 1
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
    }}
