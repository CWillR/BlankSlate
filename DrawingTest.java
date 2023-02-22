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
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingTest extends JPanel {
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private boolean drawingLine = false;
    private Point startPoint;
    private Point endPoint;
    private Color currentColor = Color.BLACK;

    public DrawingTest() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startPoint = new Point(e.getX(), e.getY());
                drawingLine = true;
            }

            public void mouseReleased(MouseEvent e) {
                endPoint = new Point(e.getX(), e.getY());
                drawingLine = false;
                points.add(startPoint);
                points.add(endPoint);
                colors.add(currentColor);
                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < points.size(); i += 2) {
            g.setColor(colors.get(i / 2));
            Point start = points.get(i);
            Point end = points.get(i + 1);
            if (start.x == end.x && start.y == end.y) {
                g.drawLine(start.x, start.y, end.x + 1, end.y + 1);
            } else {
                g.drawRect(Math.min(start.x, end.x), Math.min(start.y, end.y),
                        Math.abs(end.x - start.x), Math.abs(end.y - start.y));
            }
        }
    }

    public void setCurrentColor(Color c) {
        currentColor = c;
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
