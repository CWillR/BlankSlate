/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectblankslate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Drawing extends JComponent {
    
    //Image we'll be drawing in
    private Image image;
    //Graphics2D object --> used to draw on
    private Graphics2D g2;
    //Mouse coordinates
    private int currentX, currentY, oldX, oldY, thickness = 3;
    
    // NOTE: NEW CODE
    public boolean isDrawingRectangle, isDrawingCircle = false;
    private int startX, startY, endX, endY;

    public Drawing() {
        setDoubleBuffered(false);
        
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                //save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
                startX = e.getX();
                startY = e.getY();
            }
            
            public void mouseReleased(MouseEvent e) {
                // save coordinates of end point
                endX = e.getX();
                endY = e.getY();
                int shapeX = Math.min(startX, endX);
                int shapeY = Math.min(startY, endY);
                int shapeWidth = Math.abs(startX - endX);
                int shapeHeight = Math.abs(startY - endY);
                    if(isDrawingRectangle){
                        // draw the rectangle
                        g2.fillRect(shapeX, shapeY, shapeWidth, shapeHeight);
                        repaint();
                    } else if(isDrawingCircle){
                        // draw the ellipse
                        g2.fillOval(shapeX, shapeY, shapeWidth, shapeHeight);
                        repaint();
                    }
            }
    
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                if (isDrawingRectangle) {
                    // update coordinates of rectangle end point
                    endX = e.getX();
                    endY = e.getY();
                    repaint();
                } else if (isDrawingCircle) {
                    // update coordinates of rectangle end point
                    endX = e.getX();
                    endY = e.getY();
                    repaint();
                } else {
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
            } 
        }); 
    }
    // END NEW CODE
            
    public static BufferedImage toBufferedImage(Image image) {
        if(image instanceof BufferedImage) {
            return (BufferedImage) image;
        }   
        BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);   
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();  
        return bimage;
        }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            //image to draw null --> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            //enable antialiasing
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(5));
            clear();
        }

        g.drawImage(image, 0, 0, null);

        if (isDrawingRectangle) {
            // draw the rectangle as the user drags the mouse
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY));
        } else if (isDrawingCircle) {
            // draw the rectangle as the user drags the mouse
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY));
        }
    } 
    
    //Change the color of the pen (g2) to that of the user's choice
    public void colorChooser() {
        Color color = JColorChooser.showDialog(null, "Choose Color", Color.BLACK);
        g2.setPaint(color);
    }
    
    //Increases the thickness of the pen by 1
    public void increaseThick() {
        g2.setStroke(new BasicStroke(thickness++));
        //Have to set stroke again, otherwise g2 will be one value of thickness behind
        g2.setStroke(new BasicStroke(thickness));
    }
    
    //Decreases the thickness of the pen by 1 unless it already equals 1, in which it does nothing to prevent errors
    public void decreaseThick() {
        if (thickness==1) { 
            g2.setStroke(new BasicStroke(thickness));
        }
        else {
            g2.setStroke(new BasicStroke(thickness--));
        }
        //Have to set stroke again, otherwise g2 will be one value of thickness behind
        g2.setStroke(new BasicStroke(thickness));
    }
    
    //Define what the buttons do here
    public void clear() {  
        g2.setPaint(Color.white);
        //draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(3));
        thickness = 3;
        drawLine();
        repaint();
    }
    
    public void save() {
        String fileName;
        JFrame namePopup = new JFrame();
        fileName = JOptionPane.showInputDialog(namePopup,"Name your drawing (leaving the response blank or naming file 'null' will not save your drawing)", "Save",JOptionPane.INFORMATION_MESSAGE);
        File tmpDir = new File("Drawings//" + fileName + ".jpg");
        boolean exists = tmpDir.exists();
        if (exists == true){
            int i = 1;
            while (i==1){
                JFrame overwritePopup = new JFrame();
                int result = JOptionPane.showConfirmDialog(overwritePopup, 
                    "That file name already exists. Would you like to overwrite it?",
                    "Overwrite File?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    i = 0;
                }
                if(result == JOptionPane.NO_OPTION) {
                    fileName = JOptionPane.showInputDialog(namePopup,"Name file");
                    tmpDir = new File("Drawings//" + fileName + ".jpg");
                    exists = tmpDir.exists();
                    if (exists == true) {
                        i = 1;
                    }
                    if (exists == false) {
                        i = 0;
                    }
                }
            }
        }
        if ((fileName != null) && (fileName.length()>0)) {
            try {
                BufferedImage bi = toBufferedImage(image);
                File outputfile = new File("Drawings//" + fileName + ".jpg");
                ImageIO.write(bi, "jpg", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }  
    } 
    
    // Draw rectangle
    public void drawRect(){
        isDrawingRectangle = true;
        isDrawingCircle = false;
    }
    
    // Draw Cirlce
    public void drawCircle(){
        isDrawingCircle = true;
        isDrawingRectangle = false;
    }
    
    // Draw Line
    public void drawLine() {
        isDrawingRectangle = false;
        isDrawingCircle = false;
    }
    //Help button pop up.
    public static void help() {
        JOptionPane.showMessageDialog(null, """
                                                                       Welcome to Blank Slate!
                                            This tutorial will help you understand the features present in this application 
                                            Line: This tool draws lines anywhere on the canvas by holding down on the left 
                                            mouse button.
                                            Rectangle: This tool draws a rectangle infilled with selected color starting 
                                            from the point you click the left mouse button and connecting to where you 
                                            release the left mouse button. Also has a preview available for the rectangle 
                                            you will draw upon release. Furthermore this is the deafault tool.
                                            Circle: This tool draws a circle infilled with selected color starting from the 
                                            point you click the left mouse button and looping to where you release the left
                                            mouse button. Also has a preview available for the circle you will draw upon 
                                            release.
                                            Change Color: This button opens the color changing menu complete with swatches, 
                                            RGB, CMYK and more. Also Has a preview available for color selection. 
                                            Default is black.
                                            Thicker Pen: Thickens pen width by 1. Default is 3.
                                            Thinner Pen: Thins pen width by 1. Default is 3.
                                            Clear: Returns tool, color and pen to default as well as clearing canvas. 
                                            Save Drawing: Captures your masterpiece in its entirety and saves it to the 
                                            drawings folder inside of the black slate app.
                                            Help: This page right here.""" , "Tool Tips", JOptionPane.INFORMATION_MESSAGE);
    }
}

