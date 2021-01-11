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
public class ArmaExtra extends Item {
    private Game game;
    private Player player;
    private int numObjeto=0;
    private double xSelect=.43;
    private boolean derecho = false;
    private boolean izquierda = false;
    private int cantMuniciones;
    
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public ArmaExtra(int x, int y, int width, int height, Game game){
        super(x,y,width,height);
        this.game = game;
        
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
    public int getNumObjeto() {
        return numObjeto;
        
    }

    /**
     *
     * @param numObjeto
     */
    public void setNumObjeto(int numObjeto) {
        this.numObjeto = numObjeto;
    }

    /**
     *
     * @return
     */
    public int getCantMuniciones() {
        return cantMuniciones;
    }

    /**
     *
     * @param cantMuniciones
     */
    public void setCantMuniciones(int cantMuniciones) {
        this.cantMuniciones = cantMuniciones;
    }

    
    
    @Override
    public void tick() {
        seleccionarObjeto();
        municionesDisponibles();
    }
    
    @Override
    public void render(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect((int) (game.getWidth()*xSelect), (int) (game.getHeight()*.982), (int)(game.getWidth()*.05), (int)(game.getHeight()*.01));
         
    }
    
    /**
     *
     */
    public void seleccionarObjeto(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[15];

            //mover derecha
            if(component.getPollData() == .5){
                derecho=true;
            }

            if(derecho && numObjeto<3 && component.getPollData() == 0){
                derecho=false;
                xSelect=xSelect+.08;
                numObjeto++;
            }

            //mover izquierda
            if(component.getPollData() == 1){
                izquierda=true;
            }

            if(izquierda && numObjeto>0 && component.getPollData() == 0){
                izquierda=false;
                xSelect=xSelect-.08;
                numObjeto--;
            }
        }
    }
    
    /**
     *
     */
    public void municionesDisponibles(){
        if(numObjeto==0){
            cantMuniciones = game.getPlayer().getCantVidaChafa();
        }
        if(numObjeto==1){
            cantMuniciones = game.getPlayer().getCantVidaChida();
        }
        if(numObjeto==2){
            cantMuniciones = game.getPlayer().getCantGranadasFuego();
        }
        if(numObjeto==3){
            cantMuniciones = game.getPlayer().getCantGranadasHielo();
        }
    }
}
