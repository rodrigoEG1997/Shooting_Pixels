/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import test.Game.STATE;

/**
 *
 * @author esthe
 */
public class Menu {
    private Game game;
    int xSelect;
    int ySelect;
    boolean moverBotonArriba = false;
    boolean moverBotonAbajo = false;
    boolean menuprincipal=true;
    boolean juegonuevo=false;
    boolean cargarjuego=false;
    int x=0;
   // private BufferStrategy bs;//to have several buffers when displaying
     // private Display display;//to display in the game
      
      
  //  public Rectangle playButton= new Rectangle (game.getWidth()/2 + 120 , 150, 100, 50);
  //  public Rectangle lastGameButton= new Rectangle (game.getWidth()/2 + 120 , 150, 100, 50);
    
    public Menu (Game game){
        this.game=game;
        xSelect=(int)(game.getWidth()*0.41015625);
        ySelect=(int)(game.getHeight()*0.289435601);
        
    }

    
    
    public void tick(){
       
    }
    
    public void render (Graphics g){
        
           g.drawImage(Assets.juegonuevo, 0, 0, game.getWidth(), game.getHeight(),null);
       
            moverSelect(g);
    }
    
     public void moverSelect(Graphics g){
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componenty = components[0];
                    Component componenta = components[5];

                    //mover a arriba
                    if (componenty.getPollData() <= -0.9){
                        moverBotonArriba=true;
                            
                    }
                    if(moverBotonArriba && componenty.getPollData()>-.9){
                        
                     
                         moverBotonArriba=false;
                         juegonuevo=true;
                         cargarjuego=false;
                        
                          //g.drawImage(Assets.juegonuevo, 0, 0, game.getWidth(), game.getHeight(),null);
                    }
                    
                    
                    //mover a abajo
                    if (componenty.getPollData() >= 0.9){
                        moverBotonAbajo=true;
                    }
                    if(moverBotonAbajo && componenty.getPollData()<.9){
                
                          
                         moverBotonAbajo=false; 
                         cargarjuego=true;
                         juegonuevo=false;
                        // g.drawImage(Assets.cargarjuego, 0, 0, game.getWidth(), game.getHeight(),null);  
                    }
                   
              if(ySelect == (int)(game.getHeight()*0.289435601) && componenta.getPollData()==1){
                                 Game.gameState = Game.gameState.Game;
                  
              }
              if(juegonuevo){
                  g.drawImage(Assets.juegonuevo, 0, 0, game.getWidth(), game.getHeight(),null);
              } else if(cargarjuego){
                  g.drawImage(Assets.cargarjuego, 0, 0, game.getWidth(), game.getHeight(),null);  
                  

              }
        
                }
                   
        
    }
    
     
    
}
