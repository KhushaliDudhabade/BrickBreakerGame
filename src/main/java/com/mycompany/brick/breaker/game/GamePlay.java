/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brick.breaker.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    
    private boolean play=false;
    private int score=0;
    private int totalBricks =21;
    private Timer Timer;
    private int delay=8;
    private int playerX =310;
    private int ballposX=120;
    private int ballposY= 350;
    private int ballXDir =-1;
    private int ballYDir=-2;
    private MapGenerator map;
    
    public GamePlay(){
        map= new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer =new Timer(delay, this);
        Timer.start();    
    }
   public void paint(Graphics g){
       g.setColor(Color.black);
       g.fillRect(1, 1, 692, 592);
       map.draw((Graphics2D) g);
       g.setColor(Color.yellow);
       g.fillRect(0,0,3,592);
       g.fillRect(0,0,692,3);
       g.fillRect(691,0,3,592);
       
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,25));
       g.drawString(""+score, 590, 30);
       g.setColor(Color.yellow);
       g.fillRect(playerX, 550, 100, 8);
       
       g.setColor(Color.GREEN);
       g.fillOval(ballposX, ballposY, 20, 20);
       
       if(ballposY>570){
           play=false;
           ballXDir=0;
           ballYDir=0;
           g.setColor(Color.red);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("Game Over Score:"+score, 190, 300);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("Press Enter to Restart", 190, 340);
           
       }
       
       if(totalBricks==0){
           play=false;
           ballYDir=-2;
           ballXDir=-1;
           g.setColor(Color.red);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("Game Over Score:"+score, 190, 300);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("Press Enter to Restart", 190, 340);
           
       }
       g.dispose();
   } 
   
   
   @Override
   public void actionPerformed(ActionEvent e){
       Timer.start();
     
       if(play){
           if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
               ballYDir=-ballYDir;
               
           }
           A:
           for(int i=0; i<map.map.length; i++){
               for(int j=0; j<map.map[0].length; j++){
                   if(map.map[i][j]>0){
                       int brickX=j*map.brickwidth+80;
                       int brickY =i*map.brickheight+50;
                       int brickwidth=map.brickwidth;
                       int brickheight= map.brickheight;
                       
                       Rectangle rect= new Rectangle(brickX, brickY, brickwidth,brickheight);
                       Rectangle ballrect = new Rectangle(ballposX,ballposY,20,20);
                       
                       Rectangle brickrect= rect;
                       if(ballrect.intersects(brickrect)){
                           map.setBrickValue(0, i, j);
                           totalBricks--;
                           score+=5;
                           if(ballposX +19<=brickrect.x||ballposX+1>=brickrect.x+brickwidth){
                               ballXDir=-ballXDir;
                               
                           }
                           else{
                               ballYDir=-ballYDir;
                           }
                           break A;
                       }
                   }
               }
           }
           
           ballposX+=ballXDir;
           ballposY+=ballYDir;
           if(ballposX<0){
               ballXDir=-ballXDir;
               
           }
           if(ballposY<0){
               ballYDir=-ballYDir;
           }
           if(ballposX>670){
               ballXDir=-ballXDir;
           }
       }
       repaint();
   }
   
   
   public void kayPressed(KeyEvent e){
       if(e.getKeyCode()==KeyEvent.VK_RIGHT){
           if(playerX>=600){
               playerX=600;
           }
           else{
               moveRight();
           }
       }
       
       if(e.getKeyCode()==KeyEvent.VK_LEFT){
           if(playerX<10){
               playerX=10;
           }
           else{
               moveLeft();
           }
       }
       if(e.getKeyCode()==KeyEvent.VK_ENTER){
           if(!play){
               ballposX=120;
               ballposY=350;
               ballXDir=-1;
               ballYDir=-2;
               score=0;
               playerX=310;
               totalBricks =21;
               map =new MapGenerator(3,7);
               repaint();
           }
       }
   }
   
   public void moveRight(){
     play= true;
     playerX+=20;
   }
   public void moveLeft(){
     play= true;
     playerX-=20;  
   }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
