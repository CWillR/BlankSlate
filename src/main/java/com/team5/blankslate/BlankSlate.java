/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.blankslate;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sylbain Saurel
 */
public class BlankSlate {
    
    JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
    DrawArea drawArea;
    ActionListener actionListener = new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == blackBtn) {
                drawArea.black();
            } else if (e.getSource() == blueBtn) {
                drawArea.blue();
            } else if (e.getSource() == greenBtn) {
                drawArea.green();
            } else if (e.getSource() == redBtn) {
                drawArea.red();
            } else if (e.getSource() == magentaBtn) {
                drawArea.magenta();
            }
        }
    };
    
    public static void main(String[] args) {
        new BlankSlate().show();
    }
    
    public void show() {
        //create main frame
        JFrame frame = new JFrame("Blank Slate");
        Container content = frame.getContentPane();
        //set layour on content pane
        content.setLayout(new BorderLayout());
        //create draw area
        final DrawArea drawArea = new DrawArea();
        
        //add to content pane
        content.add(drawArea, BorderLayout.CENTER);
        
        //create controls to apply colors and call clear feature
        JPanel controls = new JPanel();
        
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        blackBtn = new JButton("Black");
        blackBtn.addActionListener(actionListener);
        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);
        greenBtn = new JButton("Green");
        greenBtn.addActionListener(actionListener);
        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);
        magentaBtn = new JButton("Magenta");
        magentaBtn.addActionListener(actionListener);
        
        //add to panel
        controls.add(greenBtn);
        controls.add(blueBtn);
        controls.add(blackBtn);
        controls.add(redBtn);
        controls.add(magentaBtn);
        controls.add(clearBtn);
        
        //add to content panel
        content.add(controls, BorderLayout.NORTH);
        
        frame.setSize(600, 600);
        //can close frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //show the blank slate result
        frame.setVisible(true);
        
        //now you can try out Blank Slate
    }
}
