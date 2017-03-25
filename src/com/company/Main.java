package com.company;
import javax.swing.*;

public class Main {

  //  private static GUI GUI;
    public static void main(String[] args) {
    	InputValidator validuojam = new InputValidator();
    	System.out.println(validuojam.validateString("input.txt"));
    	//System.out.println(validuojam.getStr());
    	
       /* JFrame frame = new JFrame("RM");
        GUI = new GUI();
        frame.setContentPane(GUI.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        GUI.redraw();*/
    }
}
