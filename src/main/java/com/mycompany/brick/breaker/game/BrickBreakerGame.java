/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brick.breaker.game;

import javax.swing.JFrame;

public class BrickBreakerGame {

    public static void main(String[] args) {
        JFrame obj= new JFrame();
        GamePlay gameplay =new GamePlay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick Breaker Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
        
    }
}
