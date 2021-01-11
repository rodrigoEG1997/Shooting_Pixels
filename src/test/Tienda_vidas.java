/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Rodrigo
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import test.Assets;
import static test.Assets.aumentovidaChafa;
import static test.Assets.aumentovidaChida;
import static test.Assets.vidaChafa;
import static test.Assets.vidaChida;
import static test.Assets.vida_jugador;
import test.Game;
import test.Item;
import test.ObjetoTienda;
import test.Player;

/**
 *
 * @author ALBER
 */
public class Tienda_vidas extends Item {
    private Game game;
    private Player player;
    private Assets assets;
    private int piso;
    private boolean moverTiendaDerecha=false;
    private boolean moverTiendaIzquierda=false;
    private boolean moverTiendaArriba=false;
    private boolean moverTiendaAbajo=false;
    private int objetoSeleccionado=0;
    private double xSelect;
    private double ySelect;
    private ArrayList<ObjetoTienda> productos;
    private boolean comprarObjeto=false;
    boolean empezarMensaje=false;
    private boolean confirmarCompra=true;
    private int contTicks=0;
    private boolean validarCompra=true;
    private boolean estaTienda = false;
    private boolean menuTienda=true;
    private boolean validarB=false;
    private boolean validarA=false;
    private boolean fueraTienda=true;
    private int xSelectMensaje;
    private boolean comprarOmejorar=true;
    private boolean mensajeNivel=false;
    private boolean mensajeErrorComprar=false;
     SoundClipTest sound;
    
    
    private int cantvidaChida= 0;
    private boolean validarEspacio= true;

    private Animation shop_animation;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param piso
     * @param game
     */
    public Tienda_vidas(int x, int y, int width, int height,int piso, Game game){
        super(x,y,width,height);
        this.game = game;
        this.piso = piso;
        init();
    }
    
    public void init(){
        productos = new ArrayList<>();
        
        productos.add(new ObjetoTienda((int) (game.getWidth()*.371),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),0,true, Assets.vida_jugador,200,"+Vida Jugador",1,100, 0,10,30, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.491),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),1,true, Assets.vida_torre,200,"+Vida Torre",1,100,0,10,30, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.606),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),2,true, Assets.aumentovidaChafa,120,"Tamaño pildoras",1,1,0,7,1, game));//bolsa vida chafa
        productos.add(new ObjetoTienda((int) (game.getWidth()*.725),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),3,true, Assets.aumentovidaChida,120,"Tamaño botequin",1,1,0,7,1, game));//bolsa vida chida
        productos.add(new ObjetoTienda((int) (game.getWidth()*.371),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),4,true, Assets.vidaChafa,70,"Comprar pildora",1,200,100,10,15, game));//vida chafa
        productos.add(new ObjetoTienda((int) (game.getWidth()*.491),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),5,true, Assets.vidaChida,140,"Comprar botequin",1,100,120,10,30, game));//vida chida
    
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

    public ArrayList<ObjetoTienda> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ObjetoTienda> productos) {
        this.productos = productos;
    }
    
    
    
    @Override
    public void tick() {
        contTicks++;
        entrarTienda();
        salirTienda();
        
        
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(Assets.store_vidas, getX(), getY(), getWidth(), getHeight(), null); // shop animation
        
        
        
        if(game.isPausaTienda() && estaTienda){
            g.drawImage(Assets.tienda6,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
            
           mostrarProductosDisponibles(g);
           desplegarInfoObjetos(g);
            
            g.drawImage(Assets.select,(int)(xSelect),(int) (ySelect), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075), null);
            
            if(!comprarObjeto){
               moverSelect(); 
            }
            
            desplegarMensajeCompra(g);
        }
        
       
        
        
        
    }
    
    public void entrarTienda(){
        if(game.getPlayer().intersects(this) && game.getKeyManager().entrar){
            game.setPausaTienda(true);
            estaTienda=true;
            xSelect = (double) (game.getWidth()*.29);
            ySelect = (double) (game.getHeight()*.52);
        }
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            
            if(game.getPlayer().intersects(this) && components[5].getPollData()==1 && fueraTienda){
                game.setPausaTienda(true);
                estaTienda=true;
                xSelect = (int) (game.getWidth()*.371);
                ySelect = (int) (game.getHeight()*.228);
                fueraTienda=false;
            }
        }
    }
    
    public void salirTienda(){
        if(game.isPausaTienda() && game.getKeyManager().salir && estaTienda){
            game.setPausaTienda(false);
            estaTienda=false;
        }
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            
            if(game.isPausaTienda() && components[6].getPollData()==1 && estaTienda && menuTienda){
                game.setPausaTienda(false);
                estaTienda=false;
                comprarObjeto=false;
                empezarMensaje=false;
                fueraTienda=true;
            }
        }
    }
    
    public void moverSelect(){
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componentx = components[1];
                    Component componenty = components[0];
                      
                    //mover a la derecha
                    if(componentx.getPollData() >= .9){
                        moverTiendaAbajo=false;
                        moverTiendaArriba=false;
                        moverTiendaIzquierda=false;
                        moverTiendaDerecha=true;
                    }
                    if(moverTiendaDerecha && componentx.getPollData()<.9){
                        xSelect = encontrarDireccionSelect(xSelect);
                        moverTiendaDerecha=false;
                    }
                    
                    //mover a la izquierda
                    if(componentx.getPollData() <= -.9){
                        moverTiendaAbajo=false;
                        moverTiendaArriba=false;
                        moverTiendaIzquierda=true;
                        moverTiendaDerecha=false;
                    }
                    if(moverTiendaIzquierda && componentx.getPollData()>-.9){
                        xSelect = encontrarDireccionSelect(xSelect);
                        moverTiendaIzquierda=false;
                    }
                    
                    //mover a arriba
                    if (componenty.getPollData() <= -.9){
                        moverTiendaAbajo=false;
                        moverTiendaArriba=true;
                        moverTiendaIzquierda=false;
                        moverTiendaDerecha=false;
                    }
                    if(moverTiendaArriba && componenty.getPollData()>-.9){
                        
                        ySelect = encontrarDireccionSelect(ySelect);
                        moverTiendaArriba=false;
                    }
                    
                    
                    //mover a abajo
                    if (componenty.getPollData() >= .9){
                        moverTiendaAbajo=true;
                        moverTiendaArriba=false;
                        moverTiendaIzquierda=false;
                        moverTiendaDerecha=false;
                    }
                    if(moverTiendaAbajo && componenty.getPollData()<.9){
                        ySelect = encontrarDireccionSelect(ySelect);
                        moverTiendaAbajo=false;
                    }
                    
                }
        
    }
    
    public void mostrarProductosDisponibles(Graphics g){
        
        Iterator itr = productos.iterator();
            while(itr.hasNext()){
                ObjetoTienda obj=(ObjetoTienda) itr.next();
                Font leter = new Font ("Arial", 1, 10);
                g.setFont (leter);
                g.setColor(Color.BLACK);
                g.drawString (obj.getNombre(),obj.getX()-((int)(game.getWidth()*.005)),obj.getY()-((int)(game.getHeight()*.015)));
                g.drawImage(obj.getObjeto(),obj.getX(),obj.getY() , obj.getWidth(), obj.getHeight(), null);
           
                g.drawString (""+obj.getCosto()+"$",obj.getX()+((int)(game.getWidth()*.018)),obj.getY()+((int)(game.getHeight()*.095)));
            }
            
    }
    
    public double encontrarDireccionSelect(double select){
        double x=-1;
        
        if(moverTiendaDerecha){x=game.getWidth();};
        if(moverTiendaIzquierda){x=0;};
        if(moverTiendaArriba){x=0;};
        if(moverTiendaAbajo){x=game.getHeight();};
        
        
        Iterator itr = productos.iterator();
            while(itr.hasNext()){
                ObjetoTienda obj=(ObjetoTienda) itr.next();
                
                if(moverTiendaDerecha){  
                   if(obj.getX()> select && obj.getX()<x && obj.getY()==ySelect){
                        x=obj.getX();
                        objetoSeleccionado=obj.getNumObjeto();
                    } 
                }
                
                if(moverTiendaIzquierda){
                   if(obj.getX()< select && obj.getX()>x && obj.getY()==ySelect){
                        x=obj.getX();
                        objetoSeleccionado=obj.getNumObjeto();
                    } 
                }
                
                if(moverTiendaArriba){
                   if(obj.getY()< select && obj.getY()>x && obj.getX()==xSelect){
                        x=obj.getY();
                        objetoSeleccionado=obj.getNumObjeto();
                    } 
                }
                
                if(moverTiendaAbajo){
                   if(obj.getY()> select && obj.getY()<x  && obj.getX()==xSelect){
                        x=obj.getY();
                        objetoSeleccionado=obj.getNumObjeto();
                    } 
                }
                
            }
            if(x==0 || x==game.getWidth() || x==game.getHeight()){
                return select; 
            }
        return x;
    }
    
    public void desplegarInfoObjetos(Graphics g){
        
        Font leter = new Font ("Arial", 1, 12);
            g.setFont (leter);
            g.setColor(Color.black);
            
            Iterator itr = productos.iterator();
            while(itr.hasNext()){
                ObjetoTienda obj=(ObjetoTienda) itr.next();
                
                if(obj.getNumObjeto()==objetoSeleccionado){
                    g.drawString ("Nivel " + obj.getLevel(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.24));
                    
                    if(objetoSeleccionado==0){
                        g.drawString ("Aumenta la vida del", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("jugador ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("Vida jugador: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.305));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.325));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de vida: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==1){
                        g.drawString ("Aumenta la vida de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("la torre ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("Vida torre: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.305));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.325));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de vida: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==2){
                        g.drawString ("Aumenta la cantidad", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("de paquetes de poca vida", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("vida que puedas", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("almacenar", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Cantidad disponible: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.36));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de cantidad: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==3){
                        g.drawString ("Aumenta la cantidad", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("de paquetes de mucha", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("vida que puedas", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("almacenar", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Cantidad disponible: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.36));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de cantidad: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==4){
                        g.drawString ("Le recupera al jugador", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("cierta cantidad de vida", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("Cantidad que", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.305));
                        g.drawString ("recupera: "+obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.321));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.341));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                            g.drawString ("recuperación: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.67));
                        }
                    }
                    
                    if(objetoSeleccionado==5){
                        g.drawString ("Le recupera al jugador", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("cierta cantidad de vida", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("Cantidad que", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.305));
                        g.drawString ("recupera: "+obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.321));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.341));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                            g.drawString ("recuperación: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.67));
                        }
                    }
                    
                }
               
            }
            
            
    }
    
    public void desplegarMensajeCompra(Graphics g){
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componenta = components[5];
                    Component componentm = components[1];
                    
                    if(componenta.getPollData()==0 && !comprarObjeto){
                        empezarMensaje=true;
                    }
                    
                        if(componenta.getPollData()==1 && empezarMensaje && !comprarObjeto){
                            validarA=true;
                            empezarMensaje=false;
                        }
                        
                        
                        if(componenta.getPollData()==0 && validarA && !comprarObjeto){
                            comprarObjeto=true;
                            validarA=false;
                            xSelectMensaje=(int)(game.getWidth()*.35);
                        }
                    
                    
                    if(comprarObjeto){
                        menuTienda=false;
                        
                        Iterator itr = productos.iterator();
                            while(itr.hasNext()){
                                ObjetoTienda obj=(ObjetoTienda) itr.next();

                                if(obj.getNumObjeto()==objetoSeleccionado){
                                    if(obj.getCosto()<=game.getPlayer().getDinero()){
                                        if(objetoSeleccionado>=0&&objetoSeleccionado<4){
                                            if(productos.get(objetoSeleccionado).getLevel()>=productos.get(objetoSeleccionado).getLevelMax()){
                                            g.drawImage(Assets.mensajeNivel,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                            salirLetreto();
                                            }else{
                                                 g.drawImage(Assets.mensajeMejora,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                                 salirMensajeCompra();
                                                 }
                                            
                                        }else{
                                            g.drawImage(Assets.mensajeComprarMejorar,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                            salirMensajeCompraMejora(g);
                                        } 
                                    }else{
                                        g.drawImage(Assets.dineroInsuficiente,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                        salirLetreto();
                                    }
                                }

                            }
                    }
        }
    }
    
    public void salirMensajeCompra(){
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componentA = components[5];
                    Component componentB = components[6];
                    
                    if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        validarCompra=true;
                        comprarObjeto=false;
                        validarA=false;
                        menuTienda=true;
                        realizarAccionObjetos();
                    }
                    
                    if(componentB.getPollData()==1 && empezarMensaje){
                        validarB=true;
                    }
                    
                    if(componentB.getPollData()==0 && validarB){
                        validarCompra=false;
                        comprarObjeto=false;
                        empezarMensaje=false;
                        validarB=false;
                        menuTienda=true;
                    }
                    
                    
                    
        }
    }
    
    public void salirMensajeCompraMejora(Graphics g){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component component = components[1];
                    Component componentA = components[5];
                    Component componentB = components[6];
                    
                    //mover a la derecha
                    if(component.getPollData() >= .9){
                        moverTiendaIzquierda=false;
                        moverTiendaDerecha=true;
                        xSelectMensaje=(int)(game.getWidth()*.51);
                        comprarOmejorar=false;
                    }
                    if(moverTiendaDerecha && component.getPollData()<.9){
                        moverTiendaDerecha=false;
                    }
                    
                    //mover a la izquierda
                    if(component.getPollData() <= -.9){
                        moverTiendaIzquierda=true;
                        moverTiendaDerecha=false;
                        xSelectMensaje=(int)(game.getWidth()*.35);
                        comprarOmejorar=true;
                    }
                    if(moverTiendaIzquierda && component.getPollData()>-.9){
                        moverTiendaIzquierda=false;
                    }
                    g.drawImage(Assets.select,xSelectMensaje,(int)(game.getHeight()*.375), (int)(game.getWidth()*.09), (int)(game.getHeight()*.16), null);
                    
                    if(mensajeNivel || mensajeErrorComprar){
                        if(mensajeNivel){
                            g.drawImage(Assets.mensajeNivel,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                            salirLetreroNivel();
                        }else{
                            if(mensajeErrorComprar){
                            g.drawImage(Assets.mensajeNoCompra,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                            salirLetreroNivel();
                            }
                        }
                        if(mensajeErrorComprar){
                            g.drawImage(Assets.mensajeNoCompra,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                            salirLetreroNivel();
                        } 
                        
                    }else{
                        //para el boton comprar
                    if(componentA.getPollData()==1 && !validarA && (!mensajeNivel || !mensajeErrorComprar)){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        if(validarObjeto(objetoSeleccionado)){
                            mensajeErrorComprar=true;
                        }else{
                            if(comprarOmejorar && productos.get(objetoSeleccionado).getLevel()>=productos.get(objetoSeleccionado).getLevelMax()){
                            mensajeNivel=true;
                            }else{
                                validarA=false;
                                menuTienda=true;
                                comprarObjeto=false;
                                realizarAccionObjetos();
                            }
                        }
                        
                    }
                    
                    
                    
                    if(componentB.getPollData()==1 && empezarMensaje){
                        validarB=true;
                    }
                    
                    if(componentB.getPollData()==0 && validarB){
                        validarCompra=false;
                        comprarObjeto=false;
                        empezarMensaje=false;
                        validarB=false;
                        menuTienda=true;
                    }
                        
                    }
        }
        
    }
    
  public void realizarAccionObjetos(){
        int cont=0;
        Iterator itr = productos.iterator();
        while(itr.hasNext()){
            ObjetoTienda obj=(ObjetoTienda) itr.next();
                if(obj.getNumObjeto()==objetoSeleccionado){
                    
                    if(objetoSeleccionado==0){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setVidaEntera(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==1){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getTower().setVidaEntera(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==2){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setCantMaximasVidasChafas(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==3){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setCantMaximasVidasChidas(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    
                    if(objetoSeleccionado==4 && comprarOmejorar){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==4 && !comprarOmejorar){
                        if(game.getPlayer().getCantVidaChafa()<productos.get(2).getCantidadReal()){
                            game.getPlayer().setCantVidaChafa(game.getPlayer().getCantVidaChafa()+1);
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==5 && comprarOmejorar){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setRecuperaMuchaVida(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==5 && !comprarOmejorar){
                        if(game.getPlayer().getCantVidaChida()<productos.get(3).getCantidadReal()){
                            game.getPlayer().setCantVidaChida(game.getPlayer().getCantVidaChida()+1);
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                       comprarOmejorar=true;
                    }
                    
                    
                }
        }       
    
    
    }
  
  public void salirLetreto(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componentA = components[5];
                    
                    if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        comprarObjeto=false;
                        validarA=false;
                        menuTienda=true;
                    }
        }
    }
  
  public void salirLetreroNivel(){
      menuTienda=false;
      for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componentA = components[5];
                    
                    if(componentA.getPollData()==1 && validarA){
                        validarA=false;
                    }
                    
                    
                    if(componentA.getPollData()==0 && !validarA){
                        validarCompra=false;
                        comprarObjeto=false;
                        menuTienda=true;
                        validarA=false;
                        mensajeNivel=false;
                        mensajeErrorComprar=false;
                        comprarOmejorar=true;
                    }
      }
      
  }
  
  public boolean validarObjeto(int obj){
      if(objetoSeleccionado==4 && !comprarOmejorar && game.getPlayer().getCantVidaChafa()>=productos.get(2).getCantidadReal()){
          return true;
      }
      
      if(objetoSeleccionado==5 && !comprarOmejorar && game.getPlayer().getCantVidaChida()>=productos.get(3).getCantidadReal()){
          return true;
      }
      
      
       return false;
   } 
  
}
   

