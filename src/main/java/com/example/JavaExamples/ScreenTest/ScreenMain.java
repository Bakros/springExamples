package com.example.JavaExamples.ScreenTest;

import javax.swing.*;

public class ScreenMain {

    public static void main(String[] args) {
        JFrame windows = new JFrame("Simple windows");
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

    }
}
