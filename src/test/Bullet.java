/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 *
 * @author Rodrigo
 */
public class Bullet extends Item{

    private Game game;
    private Player player;
    private int xgoal;
    private int ygoal;
    private int velocity;
    private int cont = 5;
    private int pixelsx = 0;
    private int pixelsy = 0;
    private int cuadrante;
    private int piso = 0;
    private int deQuien;
    private int mx;
    private int my;
    private int dano;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param velocity
     * @param mx
     * @param my
     * @param piso
     * @param game
     * @param deQuien
     * @param dano
     */
    public Bullet(int x, int y, int width, int height,int velocity,int mx,int my,int piso,int deQuien,int dano, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.velocity = velocity;
        this.piso = piso;
        this.mx = mx;
        this.my = my;
        this.deQuien = deQuien;
        this.dano = dano;
        //PointGoal();
        ComponentesDisparo();
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
    public int getPiso() {
        return piso;
    }

    /**
     *
     * @param piso
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     *
     * @return
     */
    public int getDano() {
        return dano;
    }

    /**
     *
     * @param dano
     */
    public void setDano(int dano) {
        this.dano = dano;
    }
    
    @Override
    public void tick() {
       
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
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
    
    /**
     *
     */
    public void ComponentesDisparo(){
        if(game.getFoundControllers().size() > 0 && deQuien == 1){
            ComponentesDisparoControl();
        }
        else{
            ComponentesDisparoMouse();
        }
        
    }
    
    /**
     *
     */
    public void ComponentesDisparoMouse(){
        System.out.println(mx + "       " + my);
        
        int dx = Math.abs(x - mx);
        int dy = Math.abs(y - my);
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
        
        if(mx > x){
            //lado derecho del personaje
            if(my < y){
                this.cuadrante = 1;
            }
            else{
                this.cuadrante = 4;
            }
        }
        else{
            if(my < y){
                this.cuadrante = 2;
            }
            else{
                this.cuadrante = 3;
            }           
        }
    }
    
    /**
     *
     */
    public void ComponentesDisparoControl(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();

            double mxx = components[3].getPollData();
            double myy = components[2].getPollData();
            
            double dx = Math.abs(mxx);
            double dy = Math.abs(myy);
            double grados;
        
            if (dx == 0){
                grados = 90;
            }    
            else if ( dy == 0 ){
                grados = 0;
            }
            else{
                grados = Math.toDegrees( Math.atan( (dy/dx) ) ) ;
            }
            
            //System.out.println(mx + "       " + my  );
            
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

            if(mxx >= 0){
                //lado derecho del personaje
                if(myy < 0){
                    this.cuadrante = 1;
                }
                else{
                    this.cuadrante = 4;
                }
            }
            else{
                if(myy < 0){
                    this.cuadrante = 2;
                }
                else{
                    this.cuadrante = 3;
                }           
            }
        }
        
    }   
}
