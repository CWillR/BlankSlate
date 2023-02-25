/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectblankslate;

/**
 *
 * @author roger
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingTest extends JPanel {
    //Image we'll be drawing in
    private Image image;
    //Graphics2D object --> used to draw on
    private Graphics2D g2;
    //Mouse coordinates
    private int currentX, currentY, oldX, oldY;

    public DrawingTest() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
            

            public void mouseReleased(MouseEvent e) {
                //coord x,y when drag mouse
                currentX = e.getX();
                currentY = e.getY();
                if (g2 != null) {
                    //draw line if g2 context not null
                    g2.drawLine(oldX, oldY, currentX, currentY);
                    //refresh draw area to repaint
                    repaint();
                    //store current coords x,y as old x,y
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        if (image == null) {
            //image to draw null --> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            //enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            //clear draw area
            clear();
        }
        
        g.drawImage(image, 0, 0, null);
    }
    
    public void clear() {  
        g2.setPaint(Color.white);
        //draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing Canvas");
        DrawingTest canvas = new DrawingTest();
        canvas.setBackground(Color.WHITE);
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
