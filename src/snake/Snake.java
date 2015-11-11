/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Evan
 */
public class Snake implements ActionListener, KeyListener {
    
    // Variables
    public JFrame frame;
    public RenderPanel panel;
    public static Snake snake;
    public Timer timer = new Timer(20, this);
    public ArrayList<Point> snakeParts = new ArrayList<>();
    public int ticks = 0 , direction = DOWN, score, tailLength = 10, time = 0;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
    public Point head, cherry;
    public Random rndm;
    public Dimension dim;
    public boolean over = false, paused;
    
    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(797, 700);
        frame.setResizable(false);
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 
                - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(panel = new RenderPanel());
        frame.addKeyListener(this);
        startGame();
    }
    
    public void startGame() {
        over = false;
        paused = false;
        score = 0;
        tailLength = 0;
        time = 0;
        direction = DOWN;
        head = new Point(30, 20);
        rndm = new Random();
        snakeParts.clear();
        cherry = new Point(rndm.nextInt(78), rndm.nextInt(66));
        timer.start();  
        for(int i = 0; i < tailLength; i++) {
            snakeParts.add(new Point(head.x, head.y));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        panel.repaint();
        ticks++;
        if(ticks % 2 == 0 && head != null && !over && !paused) {  
            time++;
            snakeParts.add(new Point(head.x, head.y));
            if(snakeParts.size() > tailLength)
                snakeParts.remove(0);
            if(direction == UP) 
                if(head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                    head = new Point(head.x, head.y - 1);
                else
                    over = true;
            if(direction == DOWN) 
                if(head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
                    head = new Point(head.x, head.y + 1);
                else
                    over = true;
            if(direction == LEFT) 
                if(head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                    head = new Point(head.x - 1, head.y);
                else
                    over = true;
            if(direction == RIGHT) 
                if(head.x + 1 < 79 && noTailAt(head.x + 1, head.y))
                    head = new Point(head.x + 1, head.y);
                else
                    over = true;
            if(cherry != null) {
                if(head.equals(cherry)) {
                    score += 10;
                    tailLength++;
                    cherry.setLocation(1 + rndm.nextInt(78), 
                            rndm.nextInt(66));
                }
            }
        }
    }
    
    private boolean noTailAt(int x, int y) {
        for(Point point : snakeParts) {
            if(point.equals(new Point(x, y)))
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void keyTyped(KeyEvent ke) {      
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int i = ke.getKeyCode();
        if(i == KeyEvent.VK_A && direction != RIGHT) 
            direction = LEFT;
        if(i == KeyEvent.VK_D && direction != LEFT)
            direction = RIGHT;
        if(i == KeyEvent.VK_W && direction != DOWN)
            direction = UP;
        if(i == KeyEvent.VK_S && direction != UP)
            direction = DOWN;
        if(i == KeyEvent.VK_SPACE)
            if(over)
                startGame();
            else
                paused = !paused;
    }   

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}