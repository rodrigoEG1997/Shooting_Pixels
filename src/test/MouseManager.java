/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import test.Game.STATE;

/**
 *
 * @author 
 */
public class MouseManager implements MouseListener, MouseMotionListener{
    private boolean izquierdo;
    private boolean derecho;
    private int x;
    private int y;
    private Game game;
  
    /**
     *
     */
    public MouseManager(){
        
    }

    /**
     *
     * @return
     */
    public int getX(){
        return x;
    }
    
    /**
     *
     * @return
     */
    public int getY(){
        return y;
    }
    
    /**
     *
     * @return
     */
    public boolean isIzquierdo(){
        return izquierdo;
    }
    
    /**
     *
     * @return
     */
    public boolean isDerecho(){
        return derecho;
    }
    
    /**
     *
     * @param izquierdo
     */
    public void setIzquierdo(boolean izquierdo){
        this.izquierdo = izquierdo;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double mx = e.getX();
        double my = e.getY();
        
      Toolkit tk = Toolkit.getDefaultToolkit();
        
        int x = (int) tk.getScreenSize().getWidth() ;
        int y = (int) tk.getScreenSize().getHeight() ;


        double xpor= mx/(x * .75);
        double ypor= my/(y * .9);

        
         //Playbutton
        System.out.println(xpor + " " + ypor);
        
        if(xpor>=0.36505612493899464 && xpor <= 0.6354319180087847 ){
            if(ypor >=0.47887731481481477 && ypor <=0.5787037037037037){
                //pressed play button
                System.out.println("ENTRE AL GAME STATE");
               Game.gameState = Game.gameState.Game;
            }
            
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            izquierdo = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        izquierdo = true;
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
