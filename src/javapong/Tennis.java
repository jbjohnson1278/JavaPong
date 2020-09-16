/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javapong;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle p1;
    AIPaddle p2;
    Ball b1;
    boolean startGame;
    Graphics gfx;
    Image img;
    
    public void init() {
        this.resize(WIDTH, HEIGHT);
        startGame = false;
        this.addKeyListener(this);
        p1 = new HumanPaddle(1);
        b1 = new Ball();
        p2 = new AIPaddle(2, b1);
        img = createImage(WIDTH, HEIGHT); //creates an image of the applet
        gfx = img.getGraphics(); //takes the graphics of the image so that the drawing doesn't cause the applet to "flicker"; gfx paints offscreen
        thread = new Thread(this);
        thread.start();
    }
    
    public void paint(Graphics g) {
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
        if(b1.getX() < -10 || b1.getX() > 710) {
            gfx.setColor(Color.red);
            gfx.drawString("Game Over", 350, 250);
        } else {
            p1.draw(gfx);
            b1.draw(gfx);
            p2.draw(gfx);
        }
        if (!startGame) {
            gfx.setColor(Color.white);
            gfx.drawString("Java Pong", 340, 100);
            gfx.drawString("Press Enter to Start!", 310, 130);
        }
        g.drawImage(img, 0, 0, this); //draws offscreen img to onscreen g
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    public void run() { //keeps the game running
        while(true) {
            if (startGame) {
                p1.move();
                p2.move();
                b1.move();
                b1.checkPaddleCollision(p1, p2);
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {    
                e.printStackTrace();
            }
        }
    }
    
    

    // TODO overwrite start(), stop() and destroy() methods

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            startGame = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(false);
        }
    }
}
