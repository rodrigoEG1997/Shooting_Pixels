/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Rodrigo
 */
public class CuadrosDeControl extends Item {
    private Game game;
    private int vida;
    private Animation tower_animation;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public CuadrosDeControl(int x, int y, int width, int height, Game game){
        super(x,y,width,height);
        this.game = game;
    }

    /**
     *
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     *
     * @return
     */
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }

    
    
    @Override
    public void tick() {
        this.tower_animation.tick();
    }
    
    @Override
    public void render(Graphics g){
         g.drawImage(Assets.cuadros, getX(), getY(), getWidth(), getHeight(), null); 
    }
    
    
    
}