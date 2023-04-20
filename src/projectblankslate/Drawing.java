
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
import javax.swing.JColorChooser;
import javax.swing.JComponent;


/**
* Represents a component that allows the user to draw on it with various tools such as a pen, rectangle, or circle.
* Inherits from JComponent.
*/
public class Drawing extends JComponent {
    
    //Image we'll be drawing in
    private Image image;
    //Graphics2D object --> used to draw on
    private Graphics2D g2;
    //Mouse coordinates
    private int currentX, currentY, oldX, oldY, thickness = 3;
    
    /**
    * Flags indicating whether the user is drawing a rectangle or square.
    */
    public boolean isDrawingRectangle, isDrawingCircle = false;

    /**
    * The x-coordinate and y-coordinate of the starting point of the rectangle or circle.
    */
    private int startX, startY;

    /**
    * The x-coordinate and y-coordinate of the ending point of the rectangle or circle.
    */
    private int endX, endY;

    /**
    * Constructor for the Drawing object.
    * Initializes the object and adds mouse listeners to detect mouse events.
    */
    public Drawing() {
        setDoubleBuffered(false);
        
        addMouseListener(new MouseAdapter() {
            /**
            * Called when the user presses a mouse button.
            * Saves the x and y coordinates of the mouse and sets them as the old coordinates.
            * Also saves the starting x and y coordinates of the shape.
            * 
            * @param e The MouseEvent that triggered this method.
            */
            public void mousePressed(MouseEvent e) {
                //save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
                startX = e.getX();
                startY = e.getY();
            }
            
            /**
            * Called when the user releases a mouse button.
            * Saves the x and y coordinates of the mouse and sets them as the ending coordinates.
            * Then, if the user is drawing a rectangle or circle, draws the shape on the screen.
            * 
            * @param e The MouseEvent that triggered this method.
            */
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
            /**
            * Called when the user drags the mouse.
            * If the user is drawing a rectangle or circle, updates the ending coordinates of the shape.
            * Otherwise, draws a line connecting the old and new coordinates.
            * 
            * @param e The MouseEvent that triggered this method.
            */
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
    
        
    /**
    * Converts the given Image to a BufferedImage.
    * 
    * @param image The Image to convert.
    * @return The BufferedImage representation of the given Image.
    */
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
    
    /**
    * Paints this component by drawing the stored image and any shapes currently being drawn on top of it.
    * 
    * @param g The Graphics object to use for painting.
    */
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
            clear();
        }

        g.drawImage(image, 0, 0, null);

        if (isDrawingRectangle) {
            // draw the rectangle as the user drags the mouse
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY));
        } else if (isDrawingCircle) {
            // draw the rectangle as the user drags the mouse
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawOval(Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY));
        }
    } 
    
    /**
    * Prompts the user to choose a color and sets the pen color to the chosen color.
    */    
    public void colorChooser() {
        Color color = JColorChooser.showDialog(null, "Choose Color", Color.BLACK);
        g2.setPaint(color);
    }
    
    /**
    * Increases the thickness of the pen by 1.
    */    
    public void increaseThick() {
        g2.setStroke(new BasicStroke(thickness++));
        //Have to set stroke again, otherwise g2 will be one value of thickness behind
        g2.setStroke(new BasicStroke(thickness));
    }
    
    /**
    * Decreases the thickness of the pen by 1, unless the thickness is already 1, in which case it does nothing.
    */    
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
    
    /**
    * Clears the drawing area by filling it with white.
    */    
    public void clear() {  
        g2.setPaint(Color.white);
        //draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(1));
        repaint();
    }
    
    /**
    * Saves the current image as a JPEG file and allows user to create a name for file.
    */
    public void save() {
        try {
            BufferedImage bi = toBufferedImage(image);
            File outputfile = new File("test.jpg");
            ImageIO.write(bi, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }        
    } 
    
    /**
    * Sets the component to draw rectangles.
    */    
    public void drawRect(){
        isDrawingRectangle = true;
        isDrawingCircle = false;
    }
    
    /**
    * Sets the component to draw circles.
    */
    public void drawCircle(){
        isDrawingCircle = true;
        isDrawingRectangle = false;
    }
    
    /**
    * Sets the component to draw lines.
    */
    public void drawLine() {
        isDrawingRectangle = false;
        isDrawingCircle = false;
    }
}


   
