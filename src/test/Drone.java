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
public class Drone extends Item {
    private Game game;
    private int objZom;
    private int objX;
    private int objY;
    private boolean objetivoZombie;
    private int contDisparo;
    private int dano;
    private int piso;
    private int rangoDisparo;
    private int cantBalas;
    private int velocidadDisparo;
    private int contBalas = 0;

    
    private Animation droneRight;
    private Animation droneLeft;
    private Animation droneUpDown;
    
    int cuadrante;
    double grados;

    public Drone(int x, int y, int width, int height,int dano,int rangoDisparo,int cantBalas,int velocidadDisparo,Game game) {
        super(x, y, width, height);
        this.game = game;
        this.dano = dano;
        this.rangoDisparo = (int)((rangoDisparo ) * ( game.getWidth() * 0.06944)) ;
        this.cantBalas = 30;
        this.velocidadDisparo = velocidadDisparo;
        droneRight  = new Animation(Assets.droneRight, 100);
        droneLeft   = new Animation(Assets.droneLeft, 100);
        droneUpDown = new Animation(Assets.droneUpDown, 100);
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getRangoDisparo() {
        return rangoDisparo;
    }

    public void setRangoDisparo(int rangoDisparo) {
        this.rangoDisparo = (int)((rangoDisparo ) * ( game.getWidth() * 0.06944));
    }

    public int getCantBalas() {
        return cantBalas;
    }

    public void setCantBalas(int cantBalas) {
        this.cantBalas = cantBalas;
    }

    public int getVelocidadDisparo() {
        return velocidadDisparo;
    }

    public void setVelocidadDisparo(int velocidadDisparo) {
        this.velocidadDisparo = velocidadDisparo;
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

    public int getObjZom() {
        return objZom;
    }

    public void setObjZom(int objZom) {
        this.objZom = objZom;
    }

    public int getObjX() {
        return objX;
    }

    public void setObjX(int objX) {
        this.objX = objX;
    }

    public int getObjY() {
        return objY;
    }

    public void setObjY(int objY) {
        this.objY = objY;
    }

    public boolean isObjetivoZombie() {
        return objetivoZombie;
    }

    public void setObjetivoZombie(boolean objetivoZombie) {
        this.objetivoZombie = objetivoZombie;
    }

    public int getContDisparo() {
        return contDisparo;
    }

    public void setContDisparo(int contDisparo) {
        this.contDisparo = contDisparo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }


    public Animation getDroneRight() {
        return droneRight;
    }

    public void setDroneRight(Animation droneRight) {
        this.droneRight = droneRight;
    }

    public Animation getDroneLeft() {
        return droneLeft;
    }

    public void setDroneLeft(Animation droneLeft) {
        this.droneLeft = droneLeft;
    }

    public Animation getDroneUpDown() {
        return droneUpDown;
    }

    public void setDroneUpDown(Animation droneUpDown) {
        this.droneUpDown = droneUpDown;
    }

    public int getCuadrante() {
        return cuadrante;
    }

    public void setCuadrante(int cuadrante) {
        this.cuadrante = cuadrante;
    }

    public double getGrados() {
        return grados;
    }

    public void setGrados(double grados) {
        this.grados = grados;
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

    public int getContBalas() {
        return contBalas;
    }

    public void setContBalas(int contBalas) {
        this.contBalas = contBalas;
    }

    @Override
    public void tick() {
        System.out.println(rangoDisparo);
        x = game.getPlayer().getX() - 30;
        y = game.getPlayer().getY();
        
        objZom = -1;
        for(int i = 0;  i < game.getZombies().size(); i++){
            if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) < rangoDisparo ){
                if(objZom == -1){
                    objZom = i;
                    objetivoZombie = true;
                    objX = game.getZombies().get(i).getX() + (game.getZombies().get(i).getWidth() / 2) ;
                    objY = game.getZombies().get(i).getY() + (game.getZombies().get(i).getHeight() / 2);
                }
                else{
                    if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) <  DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() )){
                        objZom = i;
                        objetivoZombie = true;
                        objX = game.getZombies().get(i).getX() + (game.getZombies().get(i).getWidth() / 2) ;
                        objY = game.getZombies().get(i).getY() + (game.getZombies().get(i).getHeight() / 2);
                    }
                }
            }
        }

        if( objZom != -1 ){
            if( DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() ) > rangoDisparo ){
                objetivoZombie = false;
                objZom = -1;
            }
        }
        
        //la torreta va a disparar le va a disparar a un zombie
        if(objetivoZombie && contDisparo >= 10 ){
            game.getBullets().add( new Bullet(x + (this.getWidth() / 2), y + (this.getHeight() / 2), 5 , 5 , 5 ,objX,objY,4,  2,dano,game ) );
            objetivoZombie = false;
            contDisparo = 0;
            contBalas++;            
        }
        contDisparo++;
    }

    @Override
    public void render(Graphics g) {
        switch (cuadrante) {
            case 1:
                if(grados >0 && grados <= 45){
                     g.drawImage(droneLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);           
                }else{
                    g.drawImage(droneUpDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                }
                break;
            case 2:
                if(grados >0 && grados <= 45){
                    g.drawImage(droneUpDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);             
                }else{
                    g.drawImage(droneRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                }
                break;
            case 3:
               if(grados >0 && grados <= 45){
                   g.drawImage(droneRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);          
                }else{
                   g.drawImage(droneUpDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                }           
                break;
            case 4:
               if(grados >0 && grados <= 45){
                   g.drawImage(droneUpDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                }else{
                   g.drawImage(droneLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                }
                break;
            default:
                g.drawImage(droneUpDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
        }
    }
    
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        double distancia = Math.sqrt(deltax2 + deltay2);
        updateAndTick(deltax ,distancia);
        return distancia;
    }
    
    public void updateAndTick(double deltax, double distancia){
        int px = getX()+ (getWidth()/2) ;
        int py = getY()+ (getHeight()/2);
        if(objX > x){
            //lado derecho del personaje
            if(objY < y){
                this.cuadrante = 1;
            }
            else{
                this.cuadrante = 4;
            }
        }
        else{
            if(objY < y){
                this.cuadrante = 2;
            }
            else{
                this.cuadrante = 3;
            }           
        }
        grados = Math.toDegrees( Math.acos( deltax / distancia ) ) ;
        // 1 == velocity (checar clase zombies)
        double pix = 1 * Math.cos(Math.toRadians(grados));
        int pixelsx = (int) pix;
        if(pix - pixelsx >= .5 ){
            pixelsx++;
        }
        pix = 1 * Math.sin(Math.toRadians(grados));
        int pixelsy = (int) pix;
        if(pix - pixelsy >= .5){
            pixelsy++;
        }
        
         switch (cuadrante) {
            case 1:
                if(grados >0 && grados <= 45){
                     this.droneLeft.tick();              
                }else{
                    this.droneUpDown.tick();
                }
                break;
            case 2:
                if(grados >0 && grados <= 45){
                     this.droneUpDown.tick();              
                }else{
                    this.droneRight.tick();
                }
                
                break;
            case 3:
               if(grados >0 && grados <= 45){
                     this.droneRight.tick();              
                }else{
                    this.droneUpDown.tick();
                }           
                break;
            case 4:
               if(grados >0 && grados <= 45){
                     this.droneUpDown.tick();              
                }else{
                    this.droneLeft.tick();
                }
                break;
            default:
                this.droneUpDown.tick();
                break;
        }
        //System.out.println("Grados:" + grados + " Cuadrante:" + cuadrante);
    }
    
    
}
