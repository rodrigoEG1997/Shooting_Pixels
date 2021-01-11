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
import net.java.games.input.Component;
import net.java.games.input.Controller;
import static test.Assets.balas20;
import static test.Assets.balas_damage;
import static test.Assets.cantidadBalas;
import static test.Assets.cartucho;

/**
 *
 * @author Rodrigo
 */
public class Tienda_weapon extends Item {
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
    private int xSelectMensaje;
    private int contTicks=0;
    private boolean validarCompra=true;
    private boolean estaTienda=false;
    private boolean menuTienda=true;
    private boolean validarB=false;
    private boolean validarA=false;
    private boolean fueraTienda=true;
    private boolean mensajeNoCompra=false;
     SoundClipTest sound;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param piso
     * @param game
     */
    public Tienda_weapon(int x, int y, int width, int height,int piso, Game game){
        super(x,y,width,height);
        this.game = game;
        this.piso = piso;
        init();
    }
    
    public void init(){
        productos = new ArrayList<>();
        
        productos.add(new ObjetoTienda((int) (game.getWidth()*.371),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),0,true, Assets.balas_damage,120,"Poder de Balas",1,20, 0,12,15, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.491),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),1,true, Assets.balas20,25,"20 balas",0,20,0,0,0, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.606),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),2,true, Assets.balas40,55,"40 balas",0,40,0,0,0, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.725),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),3,true, Assets.balas60,85,"60 balas",0,60,0,0,0, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.371),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),4,true, Assets.cartucho,150,"Cartucho",1,10,0,4,10, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.491),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),5,true, Assets.cantidadBalas,130,"Cantidad Balas",1,50,0,8,30, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.606),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),6,true, Assets.vel_disparo,140,"Vel Disparo",1,1,0,10,1, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.725),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),7,true, Assets.vel_recarga,120,"Vel Recarga",1,5,0,5,1, game));
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
        
        contTicks++;
        entrarTienda();
        salirTienda();
        
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(Assets.store_weapon, getX(), getY(), getWidth(), getHeight(), null); // shop animation
        
        
        
        if(game.isPausaTienda() && estaTienda){
            g.drawImage(Assets.tienda8,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
            
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
                        g.drawString ("Mejora el daño probocado", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("por la bala ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("Daño de bala: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.305));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.325));
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Daño de bala: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado>0 && objetoSeleccionado<4){
                        g.drawString ("Se realiza la compra de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString (""+obj.getCantidadReal()+" bala ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                        g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        
                        
                    }
                    
                    if(objetoSeleccionado==4){
                        g.drawString ("Aumenta la cantidad de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("balas disponibles en el", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("cartucho", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("cantidad de balas: "+obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.317));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("balas en cartucho: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==5){
                        g.drawString ("Aumenta la cantidad de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("balas que puedas tener", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("en reserva", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        
                        g.drawString ("cantidad de balas ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.317));
                        g.drawString ("en Reservas: "+obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.333));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.355));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                           
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("balas en reserva: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==6){
                        g.drawString ("Aumenta la cantidad de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("balas que se disparen", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("al tirar el gatillo", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        
                        g.drawString ("cantidad de balas ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.317));
                        g.drawString ("continuas: "+obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.333));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.355));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("balas por disparo: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==7){
                        g.drawString ("Disminuye la cantidad", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("de segundos que se ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("tarda cargando", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        
                        g.drawString ("cantidad de segundos ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.317));
                        g.drawString ("para recarga: "+obj.getCantidadReal()+"s", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.333));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.355));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Tiempo de recarga: -" + obj.getCantidadSubir() + "s", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
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
                        }
                    
                    
                    if(comprarObjeto){
                        menuTienda=false;
                        
                        Iterator itr = productos.iterator();
                            while(itr.hasNext()){
                                ObjetoTienda obj=(ObjetoTienda) itr.next();

                                if(obj.getNumObjeto()==objetoSeleccionado){
                                    if(obj.getCosto()<=game.getPlayer().getDinero()){
                                        if(objetoSeleccionado>0&&objetoSeleccionado<4){
                                            g.drawImage(Assets.mensajeCompra,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                            salirMensajeCompra(g);
                                        }else{if(productos.get(objetoSeleccionado).getLevel()>=productos.get(objetoSeleccionado).getLevelMax()){
                                            g.drawImage(Assets.mensajeNivel,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                            salirLetreto();
                                        }else{
                                            g.drawImage(Assets.mensajeMejora,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                                            salirMensajeCompra(g);
                                        }
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
    
    public void salirMensajeCompra(Graphics g){
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componentA = components[5];
                    Component componentB = components[6];

                    if(mensajeNoCompra){
                        g.drawImage(Assets.mensajeNoCompra,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                        salirLetretoNoCompra();
                    }else{
                        if(componentA.getPollData()==1 && !validarA && !mensajeNoCompra){
                            validarA=true;
                        }

                        if(componentA.getPollData()==0 && validarA){
                            if(!mensajeNoCompra && objetoSeleccionado>0 && objetoSeleccionado<4 && game.getPlayer().getBalasTotal()>=game.getPlayer().getMaxBalas()){
                            mensajeNoCompra=true;
                            validarA=false;
                        }else{
                            validarCompra=true;
                            comprarObjeto=false;
                            validarA=false;
                            menuTienda=true;
                            realizarAccionObjetos(); 
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
                            game.getPlayer().setDano(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado>0 && objetoSeleccionado<4){
                        if(game.getPlayer().getBalasTotal()+obj.getCantidadReal()<=game.getPlayer().getMaxBalas()){
                           game.getPlayer().setBalasTotal(game.getPlayer().getBalasTotal()+obj.getCantidadReal()); 
                        }else{
                           game.getPlayer().setBalasTotal(productos.get(5).getCantidadReal());
                        }
                        game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                    }
                    
                    if(objetoSeleccionado==4){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setCartucho(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==5){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setMaxBalas(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==6){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            if(obj.getLevel()>=obj.getLevelMax()){
                                game.setCantBalasSeguidas(40);
                            }else{
                                game.setCantBalasSeguidas(obj.getCantidadReal());
                            }
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==7){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()-obj.getCantidadSubir());
                            game.getPlayer().setSegundosCarga(obj.getCantidadReal());
                            game.getPlayer().setSegundosEstablesCarga(obj.getCantidadReal());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
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
   
    public void salirLetretoNoCompra(){
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
                        mensajeNoCompra=false;
                    }
        }
    }
}
