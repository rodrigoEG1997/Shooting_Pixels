/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Rectangle;
import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 *
 * @author Rodrigo
 */
public class DestinoGranada extends Item {
    private Game game;
    private int tipo;

    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public DestinoGranada(int x, int y, int width, int height, Game game){
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

    
    
    @Override
    public void tick() {
        movimientoDestinoGranada();
    }
    
    @Override
    public void render(Graphics g){
       // g.setColor(Color.green);
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
         g.drawImage(Assets.destinoGranada, getX(), getY(), getWidth(), getHeight(), null); 
    }
    
    /**
     *
     */
    public void movimientoDestinoGranada(){
        
        //mover personaje con control
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentX = components[3];
            Component componentY = components[2];        

            
                
                if (componentX.getPollData() <= -0.5){
                setX(getX() - 4);
                }
                if (componentX.getPollData() >= 0.5){
                   setX(getX() + 4);
                }

                if (componentY.getPollData() <= 0.5){
                   setY(getY() - 4);
                }
                if (componentY.getPollData() >= -0.5){
                   setY(getY() + 4);
                }
            }
        }
}