/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ALBER
 */
public class Escalera extends Item {
    private Game game;
    private int piso;
    private int id;
    
    public Escalera(int x, int y, int width, int height,int piso,int id, Game game){
        super(x,y,width,height);
        this.game = game;
        this.piso = piso;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
    
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }

    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
