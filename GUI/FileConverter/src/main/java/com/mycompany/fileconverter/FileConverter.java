/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileconverter;

import javax.swing.JFrame;

/**
 *
 * @author krishnabhandari
 */
public class FileConverter {

 public static void main(String[] args) {
      System.out.println("Hello World!");
        FileConverterUI fileConverterUI = new FileConverterUI();
        
        JFrame frame = new JFrame("File Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(fileConverterUI);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
