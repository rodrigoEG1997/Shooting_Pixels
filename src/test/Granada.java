/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Granada extends Item {
    private Game game;
    private int tipo;
    private int xDestino;
    private int yDestino;
    private int contTicks=0;
    private int cuadrante;
    private int pixelsx;
    private int pixelsy;
    private int velocity = 7;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param vida
     * @param game
     */
    public Granada(int x, int y, int width, int height,int tipo,int xDestino, int yDestino, Game game){
        super(x,y,width,height);
        this.game = game;
        this.tipo = tipo;
        this.xDestino=xDestino;
        this.yDestino=yDestino;
        cuadrante = cuadrante();
        //System.out.println(x + "    " + y + "   " + width + "    " + height );
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

    public int getxDestino() {
        return xDestino;
    }

    public void setxDestino(int xDestino) {
        this.xDestino = xDestino;
    }

    public int getyDestino() {
        return yDestino;
    }

    public void setyDestino(int yDestino) {
        this.yDestino = yDestino;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
        ComponentesMovimiento();
        //System.out.println(pixelsx + "  " + pixelsy);
        switch (cuadrante) {
            case 1:
                setX(getX() + pixelsx); 
                setY(getY() - pixelsy);
                break;
            case 2:
                setX(getX() - pixelsx);
                setY(getY()- pixelsy);
                break;
            case 3:
                setX(getX() - pixelsx);
                setY(getY() + pixelsy);
                break;
            default:
                setX(getX() + pixelsx);
                setY(getY() + pixelsy);
                break;
        }
        //System.out.println( (getX() + (getWidth() / 2) - 5)  + "   " + (getX() + (getWidth() / 2) + 5) + "  " + xDestino );
        if( (getX() + (getWidth() / 2) - 5) < xDestino && (getX() + (getWidth() / 2) + 5) > xDestino && (getY() + (getHeight() / 2) - 5) < yDestino && (getY() + (getHeight() / 2) + 5) > yDestino  ){
            //System.out.println("simon");            
        }
        /*
        if(x == xDestino && y == yDestino ){
            System.out.println("lolllllllllllll");
        }*/
    }
    
    @Override
    public void render(Graphics g){
         g.drawImage(Assets.granadaFuego, getX(), getY(), getWidth(), getHeight(), null); 
    }
    
    public int cuadrante(){
        if(xDestino > x){
            //lado derecho del personaje
            if(yDestino < y){
                return 1;
            }
            else{
                return 4;
            }
        }
        else{
            if(yDestino < y){
                return 2;
            }
            else{
                return 3;
            }           
        }       
    }
    
    public void ComponentesMovimiento(){
        int dx = Math.abs( (getX() + (getWidth() / 2)) - xDestino);
        int dy = Math.abs( (getY() + (getHeight() / 2)) - yDestino);
        double distance = Math.sqrt(Math.pow((dx), 2) + Math.pow((dy), 2));
        double grados = Math.toDegrees( Math.acos( dx / distance ) ) ;
        //System.out.println(grados);
        double pix = velocity * Math.cos(Math.toRadians(grados));
        pixelsx = (int) pix;
        if(pix - pixelsx >= .5 ){
            pixelsx++;
        }
        pix = velocity * Math.sin(Math.toRadians(grados));
        pixelsy = (int) pix;
        if(pix - pixelsy >= .5){
            pixelsy++;
        }
    }
}