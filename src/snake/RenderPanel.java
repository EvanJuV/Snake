/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Evan
 */
public class RenderPanel extends JPanel{

    public static Color black = new Color(0);
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(black);
        grphcs.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;
        grphcs.setColor(Color.GREEN);
        for(Point point : snake.snakeParts) {
            grphcs.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, 
                    Snake.SCALE, Snake.SCALE);
        }
        grphcs.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, 
                    Snake.SCALE, Snake.SCALE);
        grphcs.setColor(Color.RED);
        grphcs.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * 
                Snake.SCALE, Snake.SCALE, Snake.SCALE);
        String str = "Score: " + snake.score + " Length : " + snake.tailLength 
                + " Time: " + snake.time / 20; 
        grphcs.setColor(Color.white);
        grphcs.drawString(str, 0, 10);
    } 
}
