/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

/**
 *
 * @author ALBER
 */
public class Tower extends Item {
    private Game game;
    private double vida;
    private double vidaEntera=100;
    private Animation tower_animation;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param vida
     * @param game
     */
    public Tower(int x, int y, int width, int height,double vida, Game game){
        super(x,y,width,height);
        this.game = game;
        this.vida = vida;
        this.tower_animation = new Animation(Assets.tower,100 );
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
    
    @Override
     public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }

    /**
     *
     * @return
     */
    public double getVida() {
        return vida;
    }

    /**
     *
     * @param vida
     */
    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getVidaEntera() {
        return vidaEntera;
    }

    public void setVidaEntera(double vidaEntera) {
        this.vidaEntera = vidaEntera;
    }
    
    
    
    @Override
    public void tick() {
        this.tower_animation.tick();
    }
    
    @Override
    public void render(Graphics g){
       // g.setColor(Color.green);
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
         g.drawImage(tower_animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); // coins animation
    }
    
    
    
}
