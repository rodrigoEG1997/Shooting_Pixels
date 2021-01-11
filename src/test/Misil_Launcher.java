/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author ALBER
 */
public class Misil_Launcher extends Item {
    private Game game;
    private int dano;                       // daño que tiene asignado
    private int tiempo_entre_disparos;      // cuantos ticks entre disparos
    private int cantBalasTotales;           // cantidad de misiles que tiene antes de acabar su funcionamiento
    private int contBalas;                  // contador para control entre disparo y disparo 
    private ArrayList<Misil> misiles;       // arreglo de misiles 
    private Animation mortero;
    private boolean otro = true;
    private int cantBalas;
    
    public Misil_Launcher(int x, int y, int width, int height,int dano,int tiempo_entre_disparos,int cantBalasTotales,int cantBalas ,Game game) {
        super(x, y, width, height);
        this.game = game;
        this.cantBalasTotales = cantBalasTotales;
        this.dano = dano;
        this.tiempo_entre_disparos = tiempo_entre_disparos;
        this.cantBalas=cantBalas;
        misiles = new ArrayList<>();
        mortero =  new Animation(Assets.mortero, (int) (tiempo_entre_disparos * 2.5 ) );
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getTiempo_entre_disparos() {
        return tiempo_entre_disparos;
    }

    public void setTiempo_entre_disparos(int tiempo_entre_disparos) {
        this.tiempo_entre_disparos = tiempo_entre_disparos;
    }

    public int getCantBalasTotales() {
        return cantBalasTotales;
    }

    public void setCantBalasTotales(int cantBalasTotales) {
        this.cantBalasTotales = cantBalasTotales;
    }

    public int getContBalas() {
        return contBalas;
    }

    public void setContBalas(int contBalas) {
        this.contBalas = contBalas;
    }

    public ArrayList<Misil> getMisiles() {
        return misiles;
    }

    public void setMisiles(ArrayList<Misil> misiles) {
        this.misiles = misiles;
    }

    public Animation getMortero() {
        return mortero;
    }

    public void setMortero(Animation mortero) {
        this.mortero = mortero;
    }

    public boolean isOtro() {
        return otro;
    }

    public void setOtro(boolean otro) {
        this.otro = otro;
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

    public int getCantBalas() {
        return cantBalas;
    }

    public void setCantBalas(int cantBalas) {
        this.cantBalas = cantBalas;
    }
    
    
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }

      @Override
    public void tick() {
        this.mortero.tick();

        Iterator itr = misiles.iterator();
        while(itr.hasNext()){
            Misil mis=(Misil) itr.next();
            mis.tick();   
            if( mis.isDone() ){
                misiles.remove(mis);
                itr = misiles.iterator();
            }
        }
        
        if( mortero.getIndex() == 0){
            otro = true;
        }
        
        if(mortero.getIndex() == 7 && otro && cantBalas>0){
            misiles.add ( new Misil(x + this.getWidth() - 50 ,y ,50,50,200,game));
            cantBalas--;
            otro = false;
        }   // falta hacer control de que ya no pueda mandar misiles si no tiene ya disponibles
        
        if(misiles.size()<=0 && cantBalas<=0){
            game.setMisilActivado(false);
            game.setLauncher(null);
        }
        
        
        
    }
    

    @Override
    public void render(Graphics g) {
        if(cantBalas>0){
           
            Font leter = new Font ("Arial", 1, 12);
                g.setFont (leter);
                g.setColor(Color.BLACK);
                //dinero player
                g.drawString ("" + cantBalas , (int) (getX()) , (int) (getY()-(getHeight()*.2)));

                g.drawImage(mortero.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);   
        }
                Iterator itr = misiles.iterator();
                while(itr.hasNext()){
                    Misil mis=(Misil) itr.next();
                    mis.render(g);   
                } 
        
        
    }    
}
