/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class Ball {
    double xVel, yVel, x, y;
    
    public Ball() {
        x = 350;
        y = 250;
        xVel = getRandomSpeed() * getRandomDirection();
        yVel = getRandomSpeed() * getRandomDirection();
    }
    
    public double getRandomSpeed() {
        return (Math.random() * 3 + 2);
    }
    
    public int getRandomDirection() {
        int rand = (int)(Math.random() * 2);
        if (rand == 1)
            return 1;
        else
            return -1;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int)x - 10, (int)y - 10, 20, 20); // subtracting 10 because we want movement to be relevant to the center of the ball
    }
    
    public void checkPaddleCollision(Paddle p1, Paddle p2) { //checks if ball is within x range and y range of touching a paddle of a paddle 
        if (x <= 50) { // if ball is touching paddle on the x axis
            if (y >= p1.getY() && y <= p1.getY() + 80) //if ball is in the same x range as paddle
                xVel = -xVel; // bounces off paddle
        }
        else if(x >= 650) {
            if (y >= p2.getY() && y <= p2.getY() + 80)
                xVel = -xVel;
        }
    }
    
    public void move() {
        x += xVel;
        y += yVel;
        
        if (y < 10)
            yVel = -yVel; //tells ball to go in opposite way; "bounce" off wall
        if (y > 490)
            yVel = -yVel;
    }
    
    public int getX() {
        return (int)x;
    }
    
    public int getY() {
        return (int)y;
    }
}
