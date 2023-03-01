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
 
public class DrawingTest{
 
  JButton clearBtn, colorChooser;
  Drawing drawing;
  ActionListener actionListener = new ActionListener() {
 
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawing.clear();
      } else if (e.getSource() == colorChooser) {
        drawing.colorChooser();
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
 
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    colorChooser = new JButton("Change Color");
    colorChooser.addActionListener(actionListener);
 
    // add to panel
    controls.add(colorChooser);
    controls.add(clearBtn);
 
    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(600, 600);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
  }
}
