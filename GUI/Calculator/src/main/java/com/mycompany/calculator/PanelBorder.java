/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author krishnabhandari
 */
public class PanelBorder extends JPanel {
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast to Graphics2D for more advanced graphics operations
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smoother curves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the background color
        g2d.setColor(getBackground());

        // Draw the rounded rectangle
        int arcWidth = 20;
        int arcHeight = 20;
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // Dispose the Graphics2D object to free up resources
        g2d.dispose();
    }
} 
    

