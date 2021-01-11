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
public class Mina extends Item {
    private Game game;
    private int radio;
    private int dano;
    private int tipo;
    private Animation mina_animation;

    public Mina(int tipo,int x, int y, int width, int height,int radio,int dano,Game game) {
        super(x, y, width, height);
        this.tipo = tipo;
        this.dano = dano;
        this.radio = radio;
        if (tipo == 0){
            this.mina_animation = new Animation(Assets.mina,100 );
        }else{
            this.mina_animation = new Animation(Assets.mina_2,100 );
        }
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle (getX() + 10 , getY() + 10,getWidth() - 20 , getHeight() - 20);
    }

    @Override
    public void tick() {
        this.mina_animation.tick();
    }

    @Override
    public void render(Graphics g) {
        if(tipo==0){
             g.drawImage(mina_animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }        
        else if(tipo==1){
            g.drawImage(mina_animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
    
    
    //GETTERS AND SETTERS ===================================================================
        public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
