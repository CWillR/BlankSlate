package projectblankslate;
 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
 
/**
* This class represents a simple drawing application with a graphical user interface.
* It allows the user to draw and edit shapes on a blank canvas, with options to change
* the color, thickness, and shape of the drawing tool.
*/
public class DrawingTest {

    JButton clearBtn, colorChooser, save, increaseThick, decreaseThick, rectangle,lineBtn, circle, help, load, undo;
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
          } else if (e.getSource() == help) {
              drawing.help(); 
          }else if (e.getSource() == load) {
              //drawing.load(); 
          }else if (e.getSource() == undo) {
              //drawing.undo(); 
          }
      }
      };

      /**
      * The main method creates a new instance of DrawingTest and calls the show() method to display the GUI.
      * 
      * @param args command line arguments (not used)
      */
      public static void main(String[] args) {
        new DrawingTest().show();
      }

      /**
      * The show() method sets up the GUI and displays the Drawing object in the center of the window.
      */
      public void show() {
      // create main frame
      ImageIcon icon;
      icon = new ImageIcon(".//res//icon.jpg");
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
      controls.setBackground(new Color(25,25,25));

      colorChooser = new JButton("Change Color");
      colorChooser.setBackground(new Color(255, 190, 140));
      colorChooser.setForeground(Color.BLACK);
      colorChooser.setFocusPainted(false);
      colorChooser.setFont(new Font("MS Mincho", Font.BOLD, 12));
      colorChooser.addActionListener(actionListener);
      increaseThick = new JButton("Thicker Pen");
      increaseThick.setBackground(new Color(255, 244, 140));
      increaseThick.setForeground(Color.BLACK);
      increaseThick.setFocusPainted(false);
      increaseThick.setFont(new Font("MS Mincho", Font.BOLD, 12));
      increaseThick.addActionListener(actionListener);
      decreaseThick = new JButton("Thinner Pen");
      decreaseThick.setBackground(new Color(198, 255, 140));
      decreaseThick.setForeground(Color.BLACK);
      decreaseThick.setFocusPainted(false);
      decreaseThick.setFont(new Font("MS Mincho", Font.BOLD, 12));
      decreaseThick.addActionListener(actionListener); 
      clearBtn = new JButton("Clear");
      clearBtn.setBackground(new Color(168, 5, 5));
      clearBtn.setForeground(Color.WHITE);
      clearBtn.setFocusPainted(false);
      clearBtn.setFont(new Font("MS Mincho", Font.BOLD, 12));
      clearBtn.addActionListener(actionListener);
      save = new JButton("Save");
      save.setBackground(new Color(59, 89, 182));
      save.setForeground(Color.WHITE);
      save.setFocusPainted(false);
      save.setFont(new Font("MS Mincho", Font.BOLD, 12));
      save.addActionListener(actionListener);
      rectangle = new JButton("Rectangle");
      rectangle.setBackground(new Color(190, 140, 255));
      rectangle.setForeground(Color.BLACK);
      rectangle.setFocusPainted(false);
      rectangle.setFont(new Font("MS Mincho", Font.BOLD, 12));
      rectangle.addActionListener(actionListener);
      lineBtn = new JButton("Line");
      lineBtn.setBackground(new Color(140, 163, 255));
      lineBtn.setForeground(Color.BLACK);
      lineBtn.setFocusPainted(false);
      lineBtn.setFont(new Font("MS Mincho", Font.BOLD, 12));
      lineBtn.addActionListener(actionListener);
      circle = new JButton("Circle");
      circle.setBackground(new Color(255, 140, 238));
      circle.setForeground(Color.BLACK);
      circle.setFocusPainted(false);
      circle.setFont(new Font("MS Mincho", Font.BOLD, 12));
      circle.addActionListener(actionListener);
      help = new JButton("Help");
      help.setBackground(new Color(59, 89, 182));
      help.setForeground(Color.WHITE);
      help.setFocusPainted(false);
      help.setFont(new Font("MS Mincho", Font.BOLD, 12));
      help.addActionListener(actionListener);
      load = new JButton("Load");
      load.setBackground(new Color(59, 89, 182));
      load.setForeground(Color.WHITE);
      load.setFocusPainted(false);
      load.setFont(new Font("MS Mincho", Font.BOLD, 12));
      load.addActionListener(actionListener);
      undo = new JButton("Undo");
      undo.setBackground(new Color(176, 156, 5));
      undo.setForeground(Color.WHITE);
      undo.setFocusPainted(false);
      undo.setFont(new Font("MS Mincho", Font.BOLD, 12));
      undo.addActionListener(actionListener);

      // add to panel
      controls.add(lineBtn);
      controls.add(rectangle);
      controls.add(circle);
      controls.add(colorChooser);
      controls.add(increaseThick);
      controls.add(decreaseThick);
      controls.add(clearBtn);
      //controls.add(undo);
      controls.add(save);
      //controls.add(load);
      controls.add(help);

      // add to content pane
      content.add(controls, BorderLayout.NORTH);

      frame.setSize(950, 600);
      frame.setIconImage(icon.getImage());
      // can close frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // show the swing paint result
      frame.setVisible(true);
      Drawing.help();
    }
}
