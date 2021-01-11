/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Iterator;

/**
 *
 * @author ALBER
 */
public class Misil extends Item {
    private Game game;
    private int direccion;                  // 0 = va para arriba apenas , 1 = va para abajo
    private int xGoal;                      // random x en el mapa para el misil
    private int yGoal;                      // random y en el mapa para el misil
    private int dano;                       // daño del misil  
    private int contArriba;                 // contador que espera entre entrar al mapa
    
    private Animation misilUp;
    private Animation misilDown;

    private boolean done;

    public Misil(int x, int y, int width, int height,int dano, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.dano = dano;
        direccion = 0;
        misilUp = new Animation(Assets.misil_UP, 100);
        misilDown = new Animation(Assets.misil_DOWN, 100);
        done = false;
        xGoal = (int) (Math.random() * game.getWidth());
        yGoal = (int) (Math.random() * game.getHeight());
        //randomEnMapa();
    }
    
    public void randomEnMapa(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        
        int xx = (int) tk.getScreenSize().getWidth();
        int yy = (int) tk.getScreenSize().getHeight();

        xx =(int) ((int) xx * .75);
        yy = (int) ((int) yy * .9);
        
        xGoal = (int) (Math.random() * xx);
        yGoal = (int) (Math.random() * yy);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getxGoal() {
        return xGoal;
    }

    public void setxGoal(int xGoal) {
        this.xGoal = xGoal;
    }

    public int getyGoal() {
        return yGoal;
    }

    public void setyGoal(int yGoal) {
        this.yGoal = yGoal;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getContArriba() {
        return contArriba;
    }

    public void setContArriba(int contArriba) {
        this.contArriba = contArriba;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public void tick() {
        //Direccion  == > 0, misil arriba ||  1, misil abajo
        if( direccion == 0){

            if( y + this.getHeight() <= 0 ){
                contArriba++;
                misilUp.tick();
            }else{
               y = y - 2; 
               misilDown.tick();
            }
            if( contArriba == 200 ){
                x = xGoal;
                direccion = 1;
            }
        }
        else if( direccion == 1){
            
            if( y >= yGoal){
                //System.out.println("simon carnal");
                Explosion exp = new Explosion(0,this.getX() + (this.getWidth() / 2),this.getY() + (this.getHeight() / 2 ),1,1,100,dano,game);
                game.getExplosiones().add(exp);
                //sound = new SoundClipTest("boom"); // SONIDO DE BOOOM!!!
                int xExp = exp.getX();
                int yExp = exp.getY();
                double radioExp = exp.getRadio();

                //colision de explosion con otro zombie
                Iterator itr3 = game.getZombies().iterator();
                while(itr3.hasNext()){
                    Zombie zomb = (Zombie) itr3.next();
                    if(DistanciaDosPuntos( zomb.getX() + (zomb.getWidth() / 2), xExp, zomb.getY() + (zomb.getHeight() / 2) , yExp ) <= radioExp ){
                        zomb.setVidaZ(zomb.getVidaZ()- dano);
                    }                                  
                }
                done = true;
            }
            else{
                y = y + 2;  
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(direccion == 0){
         g.drawImage(misilUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }else{
         g.drawImage(misilDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
    
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        
        return Math.sqrt(deltax2 + deltay2);
    }
}
