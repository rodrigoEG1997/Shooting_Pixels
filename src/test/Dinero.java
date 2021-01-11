/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rodrigo
 */
public class Dinero extends Item {
    private Game game;
    private int cantidad;
    private ArrayList<Zombie> zombies;
    private Animation coins_animation;
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param cantidad
     * @param game
     */
    public Dinero(int x, int y, int width, int height,int cantidad, Game game){
        super(x,y,width,height);
        this.game = game;
        this.cantidad = cantidad;
        this.coins_animation = new Animation(Assets.coins,100 );
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

    /**
     *
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param vida
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public void tick() {
        this.coins_animation.tick();
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(coins_animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); // coins animation
    }
    
    
    
}
