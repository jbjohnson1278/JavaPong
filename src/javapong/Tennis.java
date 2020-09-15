/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Owner
 */
public class Tennis extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    final int WIDTH = 700, HEIGHT = 500;
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.resize(WIDTH, HEIGHT);
    }
    
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
    
    public void update(Graphics g) {
        paint(g);
    }

    // TODO overwrite start(), stop() and destroy() methods
}
