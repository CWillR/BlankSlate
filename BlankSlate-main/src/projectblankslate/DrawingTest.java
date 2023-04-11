/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectblankslate;
 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class DrawingTest {
 
  JButton clearBtn, colorChooser, save, increaseThick, decreaseThick, rectangle,lineBtn, circle;
  Drawing drawing;
  
  ActionListener actionListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == colorChooser) {
            drawing.colorChooser();
        } else if (e.getSource() == increaseThick) {
            //drawing.isDrawingRectangle = false;
            drawing.increaseThick();
        } else if (e.getSource() == decreaseThick) {
            //drawing.isDrawingRectangle = false;
            drawing.decreaseThick();
        } else if (e.getSource() == clearBtn) {
            drawing.clear();
        } else if (e.getSource() == save) {
            drawing.save();
        } else if (e.getSource() == rectangle) {
            drawing.drawRect();
        } else if (e.getSource() == lineBtn) {
            drawing.drawLine();
        } else if (e.getSource() == circle) {
            drawing.drawCircle();
        }  
    }
};

 
  public static void main(String[] args) {
    new DrawingTest().show();
  }
 
  public void show() {
    // create main frame
    JFrame frame = new JFrame("Blank Slate");
    Container content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
    drawing = new Drawing();
 
    // add to content pane
    content.add(drawing, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
    
    colorChooser = new JButton("Change Color");
    colorChooser.addActionListener(actionListener);
    increaseThick = new JButton("Thicker Pen");
    increaseThick.addActionListener(actionListener);
    decreaseThick = new JButton("Thinner Pen");
    decreaseThick.addActionListener(actionListener); 
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    save = new JButton("Save Drawing");
    save.addActionListener(actionListener);
    rectangle = new JButton("Rectangle");
    rectangle.addActionListener(actionListener);
    lineBtn = new JButton("Line");
    lineBtn.addActionListener(actionListener);
    circle = new JButton("Circle");
    circle.addActionListener(actionListener);
     
    // add to panel
    controls.add(lineBtn);
    controls.add(rectangle);
    controls.add(circle);
    controls.add(colorChooser);
    controls.add(increaseThick);
    controls.add(decreaseThick);
    controls.add(clearBtn);
    controls.add(save);

    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(800, 600);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
  }
}