/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import static test.Assets.bomba_hielo;
import static test.Assets.spriteSheet_Bomba_hielo;

/**
 *
 * @author ALBER
 */
public class Explosion extends Item {
    private int tipo;
    private int radio;
    private Game game;
    private Animation Assetexplosion;
    private boolean finish = false;
    private int piso;
    private int destruir = 1500;

    public Explosion(int tipo, int x, int y, int width, int height, int radio,int piso,Game game ) {
        super(x, y, width, height);
        this.tipo = tipo;
        this.radio = radio;
        this.game = game;
        this.piso = piso;
        if(tipo == 0){
            this.Assetexplosion = new Animation(Assets.explosion, 100);
        }
        else if(tipo == 1){
            this.Assetexplosion = new Animation(Assets.bomba_hielo, 100); 
        }        
        else{
            this.Assetexplosion = new Animation(Assets.golpe, 100);
        }
        Assetexplosion.setIndex(1);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }
    
    public double getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public Animation getAssetexplosion() {
        return Assetexplosion;
    }

    public void setAssetexplosion(Animation Assetexplosion) {
        this.Assetexplosion = Assetexplosion;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getDestruir() {
        return destruir;
    }

    public void setDestruir(int destruir) {
        this.destruir = destruir;
    }
     

    
    @Override
    public void tick() {
        this.Assetexplosion.tick();
        if( Assetexplosion.getIndex() == 0  ){
            finish = true;
        }
        destruir--;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assetexplosion.getCurrentFrame(), getX() - radio, getY() - radio, getWidth()+ 2 * radio, getHeight() + 2* radio, null);
    }
}
