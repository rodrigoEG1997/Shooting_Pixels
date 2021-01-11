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
public class Sobreviviente_Healer extends Item {
    private Game game;
    private boolean move;
    private int xgoal;
    private int ygoal;
    private int cuadrante;
    private int areaRambo = 1;
    private int cont = 0;
    private int objetivo = 0;
    private Animation assetHada;
    private int tiempoRecuperacion;
    
    public Sobreviviente_Healer(int objetivo, int x, int y, int width, int height,int tiempoRecuperacion,Game game) {
        super(x, y, width, height);
        this.objetivo = objetivo;
        this.game = game;
        this.tiempoRecuperacion = tiempoRecuperacion;
        assetHada =  new Animation( Assets.survivor_hada , 75 );

    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }
    
    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getXgoal() {
        return xgoal;
    }

    public void setXgoal(int xgoal) {
        this.xgoal = xgoal;
    }

    public int getYgoal() {
        return ygoal;
    }

    public void setYgoal(int ygoal) {
        this.ygoal = ygoal;
    }

    public int getCuadrante() {
        return cuadrante;
    }

    public void setCuadrante(int cuadrante) {
        this.cuadrante = cuadrante;
    }

    public int getAreaRambo() {
        return areaRambo;
    }

    public void setAreaRambo(int areaRambo) {
        this.areaRambo = areaRambo;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public Animation getAssetHada() {
        return assetHada;
    }

    public void setAssetHada(Animation assetHada) {
        this.assetHada = assetHada;
    }

    public int getTiempoRecuperacion() {
        return tiempoRecuperacion;
    }

    public void setTiempoRecuperacion(int tiempoRecuperacion) {
        this.tiempoRecuperacion = tiempoRecuperacion;
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

    
    @Override
    public void tick() {
        this.assetHada.tick();
        if(cont > 30){
          cont = 0;  
            if( move){
                move = false;

                //System.out.println("no entro carnal");
            }
            else{
                for( int i = 0 ; i < 8 ; i++ ){
                    if(objetivo == 0 && DistanciaDosPuntos(x,game.getRambo().getAreax().get(areaRambo),y, game.getRambo().getAreay().get(areaRambo)) > DistanciaDosPuntos(x,game.getRambo().getAreax().get(i),y, game.getRambo().getAreay().get(i))){
                        areaRambo = i;
                    }
                    if(objetivo == 1 && DistanciaDosPuntos(x,game.getRocky().getAreax().get(areaRambo),y, game.getRocky().getAreay().get(areaRambo)) > DistanciaDosPuntos(x,game.getRocky().getAreax().get(i),y, game.getRocky().getAreay().get(i))){
                        areaRambo = i;
                    }
                }
                if(objetivo == 0){
                    xgoal = game.getRambo().getAreax().get(areaRambo);
                    ygoal = game.getRambo().getAreay().get(areaRambo);  
                }
                if( objetivo == 1){
                    xgoal = game.getRocky().getAreax().get(areaRambo);
                    ygoal = game.getRocky().getAreay().get(areaRambo);  
                }
            }
        }
        
        //System.out.println(xgoal + "    " + ygoal);
        if( x <= xgoal ){
            x = x + 1;
        }
        else{
            x = x - 1;
        }
        if( y <= ygoal ){
            y = y + 1;
        }
        else{
            y = y - 1;
        }
        cont++;
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(assetHada.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public void changeGoal(int sx,int sy, int zx, int zy){  
        //System.out.println("awebooooooooooo");
        if(zx > sx){
            //lado derecho del personaje
            if(zy < sy){
                xgoal = sx - 50;  
                ygoal = sy + 50;
            }
            else{
                xgoal = sx - 50;  
                ygoal = sy - 50;
            }
        }
        else{
            if(zy < sy){
                xgoal = sx + 50;  
                ygoal = sy + 50;
            }
            else{
                xgoal = sx + 50;  
                ygoal = sy - 50;
            }           
        }        
        //System.out.println( xgoal+ "  " + ygoal+ "  " + x+ "  " + y  );
    }
    
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        
        return Math.sqrt(deltax2 + deltay2);
    }
    
}
