/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import test.Game.STATE;

/**
 *
 * @author 
 */
public class KeyManager implements KeyListener {
   
    public boolean jota;
    /**
     *
     */
    public boolean left;    // flag to move left the player

    /**
     *
     */
    public boolean right;   // flag to move right the player

    /**
     *
     */
    public boolean up;    // flag to move left the player

    /**
     *
     */
    public boolean down;   // flag to move right the player

    /**
     *
     */
    public boolean space;   //flag to shoot
    private boolean keys[];  // to store all the flags for every key

    /**
     *
     */
    public boolean p;

    /**
     *
     */
    public boolean entrar;

    /**
     *
     */
    public boolean salir;

    /**
     *
     */
    public boolean comprar;

    /**
     *
     */
    
    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }
    public boolean cargar;
    private Game game;
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        


       if(game.gameState== STATE.Game){

            if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_J){
                keys[e.getKeyCode()]=false;
            }else if (e.getKeyCode()!= KeyEvent.VK_P){
                keys[e.getKeyCode()] = true;
            }
       }
       
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                keys[e.getKeyCode()]=true;
                break;
            case KeyEvent.VK_J:
                keys[e.getKeyCode()]=true;
                break;                
            case KeyEvent.VK_P:
                keys[KeyEvent.VK_P] = !keys[KeyEvent.VK_P];
                break;
            default:
                keys[e.getKeyCode()]=false;
                break;
        }
        
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        space = keys[KeyEvent.VK_SPACE];
        p = keys[KeyEvent.VK_P];
        entrar = keys[KeyEvent.VK_ENTER];
        salir = keys[KeyEvent.VK_Q];
        cargar = keys[KeyEvent.VK_Z];
        keys[KeyEvent.VK_SPACE]=false;
        jota = keys[KeyEvent.VK_J];
        keys[KeyEvent.VK_J]=false;

    }
}
