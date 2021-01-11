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
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import static test.Assets.combo_torretas;
import static test.Assets.mina_de_fuego;
import static test.Assets.misil_buy;
import static test.Assets.packeteGranadas;

/**
 *
 * @author Rodrigo
 */
public class Tienda_armas extends Item {
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
    private boolean estaTienda=false;
    private boolean menuTienda=true;
    private boolean validarB=false;
    private boolean validarA=false;
    private boolean fueraTienda=true;
    private int xSelectMensaje;
    private boolean comprarOmejorar=true;
    private boolean posicionarObjeto=false;
    private boolean posicionTorretaBalas=false;
    private boolean posicionTorretaGranadas=false;
    private boolean posicionMina=false;
    private boolean posicionarTorreta=false;
    private boolean posicionarTorretaGranada=false;
    private boolean posicionarMina=false;
    private boolean mensajeNivel=false;
    private boolean mensajeErrorComprar=false;
    private int cantMinasFuego=0;
    private int cantMinasHielo=0;
    SoundClipTest sound;
    
    
    private boolean validarEspacio=true;
    private boolean acomodarObjeto=false;
    private ArrayList<Torreta> torretas;
    private ArrayList<Torreta_Granadas> torretasGranadas;
    private ArrayList<Mina> minas;
    private boolean acomodarTorreta=false;
    private ArrayList<CuadrosDeControl> cuadros;
    private boolean desplegarTacha=false;
    private boolean desplegarMensaje=false;
    private boolean empezarMensajeAcomodo=false;
    private boolean confirmarAcomodo=true;
    private boolean noMejorarMisil=false;
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param piso
     * @param game
     */
    public Tienda_armas(int x, int y, int width, int height,int piso, Game game){
        super(x,y,width,height);
        this.game = game;
        this.piso = piso;
        init();
    }
    
    public void init(){
        productos = new ArrayList<>();
        

        productos.add(new ObjetoTienda((int)(game.getWidth()*.371),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),0,true, Assets.granadaFuego,100,"Granada Fuego",1,80,120,6,30, game));
        productos.add(new ObjetoTienda((int)(game.getWidth()*.491),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),1,true, Assets.granadaHielo,100,"Granada Hielo",1,2,120,6,1, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.606),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),2,true, Assets.packeteGranadas,100,"Bolsa Granadas",1,1,0,6,1, game));

        
        productos.add(new ObjetoTienda((int) (game.getWidth()*.725),(int) (game.getHeight()*.228), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),3,true, Assets.torreta_de_balas,150,"Torreta Balas",1,5,140,6,10, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.371),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),4,true, Assets.torreta_de_granadas,150,"Torreta Granada",1,50,140,6,30, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.491),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),5,true, Assets.combo_torretas,120,"Bolsa Torreta",1,1,0,4,1, game));


        productos.add(new ObjetoTienda((int) (game.getWidth()*.606),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),6,true, Assets.mina_de_fuego,90,"Mina Fuego",1,600,110,5,15, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.725),(int) (game.getHeight()*.441), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),7,true, Assets.mina_de_hielo,90,"Mina Hielo",1,2,110,5,1, game));
        productos.add(new ObjetoTienda((int) (game.getWidth()*.371),(int) (game.getHeight()*.655), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),8,true, Assets.combo_minas,0,"Bolsa Minas",1,1,0,5,1, game));
        
        productos.add(new ObjetoTienda((int) (game.getWidth()*.491),(int) (game.getHeight()*.655), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075),9,true, Assets.misil_buy,200,"Misiles",1,100,200,5,30, game));

       productos.get(3).setCantBalas(200);
       productos.get(3).setRango(300);
       productos.get(3).setSubirCantBalas(100);
       productos.get(3).setSubirRango(50);
        
       productos.get(4).setCantBalas(100);
       productos.get(4).setRango(300);
       productos.get(4).setSubirCantBalas(60);
       productos.get(4).setSubirRango(50);
       
       productos.get(9).setCantBalas(4);
       productos.get(9).setSubirCantBalas(4);
        
        torretas = new ArrayList<>();
        torretasGranadas = new ArrayList<>();
        minas = new ArrayList<>();
        cuadros = new ArrayList<>();
        
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.128),(int)(game.getHeight()*.18),(int)(game.getWidth()*.07),(int)(game.getHeight()*.09),game));
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.128),(int)(game.getHeight()*.55),(int)(game.getWidth()*.07),(int)(game.getHeight()*.16),game));
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.455),(int)(game.getHeight()*.32),(int)(game.getWidth()*.07),(int)(game.getHeight()*.06),game));
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.45),(int)(game.getHeight()*.45),(int)(game.getWidth()*.08),(int)(game.getHeight()*.07),game));
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.338),(int)(game.getHeight()*.705),(int)(game.getWidth()*.08),(int)(game.getHeight()*.08),game));
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.345),(int)(game.getHeight()*.11),(int)(game.getWidth()*.07),(int)(game.getHeight()*.07),game));
        cuadros.add(new CuadrosDeControl((int)(game.getWidth()*.675),(int)(game.getHeight()*.37),(int)(game.getWidth()*.07),(int)(game.getHeight()*.132),game));
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

    public ArrayList<Torreta> getTorretas() {
        return torretas;
    }

    public void setTorretas(ArrayList<Torreta> torretas) {
        this.torretas = torretas;
    }

    public boolean isDesplegarTacha() {
        return desplegarTacha;
    }

    public void setDesplegarTacha(boolean desplegarTacha) {
        this.desplegarTacha = desplegarTacha;
    }

    public ArrayList<Torreta_Granadas> getTorretasGranadas() {
        return torretasGranadas;
    }

    public void setTorretasGranadas(ArrayList<Torreta_Granadas> torretasGranadas) {
        this.torretasGranadas = torretasGranadas;
    }

    public ArrayList<Mina> getMinas() {
        return minas;
    }

    public void setMinas(ArrayList<Mina> minas) {
        this.minas = minas;
    }

    public int getCantMinasFuego() {
        return cantMinasFuego;
    }

    public void setCantMinasFuego(int cantMinasFuego) {
        this.cantMinasFuego = cantMinasFuego;
    }

    public int getCantMinasHielo() {
        return cantMinasHielo;
    }

    public void setCantMinasHielo(int cantMinasHielo) {
        this.cantMinasHielo = cantMinasHielo;
    }
    
    
    
    
    @Override
    public void tick() {
        
        contTicks++;
        entrarTienda();
        salirTienda();
    }
   
    @Override
    public void render(Graphics g){
        
        g.drawImage(Assets.store_armas, getX(), getY(), getWidth(), getHeight(), null); // shop animation
        
        
        
        if(game.isPausaTienda() && estaTienda){
            if(!posicionarObjeto){
                g.drawImage(Assets.tienda10,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
            
                mostrarProductosDisponibles(g);
                desplegarInfoObjetos(g);

                g.drawImage(Assets.select,(int)(xSelect),(int) (ySelect), (int)(game.getWidth()*.06), (int)(game.getHeight()*.075), null);

                if(!comprarObjeto){
                    moverSelect(); 
                }

                desplegarMensajeCompra(g);
            }else{
                if(posicionarTorreta){
                    desplegarLimites(g);
                    posicionarTorretaBalas(g);
                }
                
                if(posicionarTorretaGranada){
                    desplegarLimites(g);
                    posicionarTorretaGranadas(g);
                }
                
                if(posicionarMina){
                    desplegarLimites(g);
                    posicionarMinas(g);
                }
            }
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
                        g.drawString ("Al activar, tiene una", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("duracion de 3s antes de que  ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("explote, proboca daño a los", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("zombies de alrededor", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Daño Granada: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.332));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.355));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Daño Granada: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==1){
                        g.drawString ("Al activar, tiene una", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("duracion de 3s antes de que  ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("explote, congela a los", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("zombies de alrededor", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Tiempo Congelamiento: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.332));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.355));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Tiempo Congelamiento: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==2){
                        g.drawString ("Aumenta la cantidad", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("de granadas que puedas", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("cargar al mismo tiempo", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("Cantidad disponible: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.321));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.355));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de cantidad: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==3){
                        g.drawString ("Se coloca en una posicion", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("seleccionada y se activa", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.279));
                        g.drawString ("cuando haya Zombies en el", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("mismo piso", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Daño por Bala: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Rango: " + obj.getRango(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        g.drawString ("Cantidad Balas: " + obj.getCantBalas(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.377));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.397));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de daño: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                            g.drawString ("Aumento rango: " + obj.getSubirRango(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.67));
                            g.drawString ("Aumento balas: " + obj.getSubirCantBalas(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.69));
                        }
                    }
                    
                    if(objetoSeleccionado==4){
                        g.drawString ("Se coloca en una posicion", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("seleccionada y dispara", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("granadas a cualquier piso", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("con un rango limitado", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Daño por Granada: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Rango: " + obj.getRango(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        g.drawString ("Cantidad Balas: " + obj.getCantBalas(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.377));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.397));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Daño por Granada: ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                            g.drawString ("Aumento rango: " + obj.getSubirRango(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.67));
                            g.drawString ("Aumento balas: " + obj.getSubirCantBalas(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.69));
                        }
                    }
                    
                    if(objetoSeleccionado==5){
                        g.drawString ("Aumenta la cantidad de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("torretas que puedas ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("tener en el campo", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("Cantidad Torretas: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento Cantidad: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==6){
                        g.drawString ("Se posiciona en algun ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("lugar seleccionado y ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("explota cuanto un zombie", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("la toca, probocando daño", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Daño Mina Fuego: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de Daño: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==7){
                        g.drawString ("Se posiciona en algun ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("lugar seleccionado y ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("explota cuanto un zombie", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("la toca, congelandolos", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.312));
                        g.drawString ("Tiempo Congelamiento: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de Tiempo: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==8){
                        g.drawString ("Aumenta la cantidad de", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("minas que puedas ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("tener en el campo", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("Cantidad Minas: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento Cantidad: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                        }
                    }
                    
                    if(objetoSeleccionado==9){
                        g.drawString ("Lanza misil al espacio", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.265));
                        g.drawString ("y cae en una cordenada ", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.281));
                        g.drawString ("aleatoria", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.295));
                        g.drawString ("Daño Misil: " + obj.getCantidadReal(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.337));
                        g.drawString ("Cantidad balas: " + obj.getCantBalas(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.357));
                        g.drawString ("Nivel maximo: " + obj.getLevelMax(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.377));
                        
                        if(obj.getLevel()>=obj.getLevelMax()){
                            g.drawString ("Este artefacto no cuenta", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("con mejoras disponibles", (int) (game.getWidth()*.142) , (int) (game.getHeight()*.626));
                        }else{
                            g.drawString ("Costo: "+obj.getPrecioMejora(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.61));
                            g.drawString ("nivel "+(obj.getLevel()+1), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.63));
                            g.drawString ("Aumento de Daño: +" + obj.getCantidadSubir(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.65));
                            g.drawString ("Aumento balas:+ " + obj.getSubirCantBalas(), (int) (game.getWidth()*.142) , (int) (game.getHeight()*.67));
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
                                        if(objetoSeleccionado==2 || objetoSeleccionado==5||objetoSeleccionado==8){
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
                    
                    if(mensajeNivel || mensajeErrorComprar || noMejorarMisil){
                        if(noMejorarMisil){
             //remplacé este asset por mensaje para depurar cantidadBalas que no ocupabamos
                           g.drawImage(Assets.mensaje_2,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                            salirLetreroNivel();  
                        }
                        if(mensajeNivel){
                            g.drawImage(Assets.mensajeNivel,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                            salirLetreroNivel();  
                        }else{
                            if(mensajeErrorComprar){
                            g.drawImage(Assets.mensajeNoCompra,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                            salirLetreroNivel();
                            }
                        }
                    }else{
                    //para el boton comprar
                    if(componentA.getPollData()==1 && !validarA && (!mensajeNivel || !mensajeErrorComprar || !noMejorarMisil)){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        if(comprarOmejorar && objetoSeleccionado==9 && game.isMisilActivado()){
                            noMejorarMisil=true;
                        }else{
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
    
    public void realizarAccionObjetos(){
        int cont=0;
        Iterator itr = productos.iterator();
        while(itr.hasNext()){
            ObjetoTienda obj=(ObjetoTienda) itr.next();
                if(obj.getNumObjeto()==objetoSeleccionado){
                    
                    
                    if(objetoSeleccionado==0 && comprarOmejorar){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==0 && !comprarOmejorar){
                            if(game.getPlayer().getCantGranadasFuego() <productos.get(2).getCantidadReal()){
                            game.getPlayer().setCantGranadasFuego(game.getPlayer().getCantGranadasFuego()+1);
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                            comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==1 && comprarOmejorar){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==1 && !comprarOmejorar){
                        if(game.getPlayer().getCantGranadasHielo() <productos.get(2).getCantidadReal()){
                            game.getPlayer().setCantGranadasHielo(game.getPlayer().getCantGranadasHielo()+1);
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==2){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==3 && comprarOmejorar){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            obj.setCantBalas(obj.getCantBalas()+obj.getSubirCantBalas());
                            obj.setRango(obj.getRango()+obj.getSubirRango());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                        
                    }
                    
                    if(objetoSeleccionado==3 && !comprarOmejorar){
                        if(torretas.size()<productos.get(5).getCantidadReal()){
                            posicionarObjeto=true;
                            posicionarTorreta=true;
                            torretas.add(new Torreta((int) (game.getWidth()*.34),(int) (game.getHeight()*.6), (int)(game.getWidth()*.03), (int)(game.getHeight()*.05),2,obj.getCantBalas(),obj.getRango(),game));
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==4 && comprarOmejorar){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            obj.setCantBalas(obj.getCantBalas()+obj.getSubirCantBalas());
                            obj.setRango(obj.getRango()+obj.getSubirRango());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==4 && !comprarOmejorar){
                        if(torretasGranadas.size()<productos.get(5).getCantidadReal()){
                            posicionarObjeto=true;
                            posicionarTorretaGranada=true;
                            torretasGranadas.add(new Torreta_Granadas((int) (game.getWidth()*.34),(int) (game.getHeight()*.6), (int)(game.getWidth()*.03), (int)(game.getHeight()*.05),2,obj.getCantBalas(),obj.getCantidadReal(), game));
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }    
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==5){
                        if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                    }
                    
                    if(objetoSeleccionado==6 && comprarOmejorar){
                            if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==6 && !comprarOmejorar){
                        if(cantMinasFuego<productos.get(8).getCantidadReal()){
                            posicionarObjeto=true;
                            posicionarMina=true;
                            minas.add(new Mina(0,(int) (game.getWidth()*.34),(int) (game.getHeight()*.6), (int)(game.getWidth()*.03), (int)(game.getHeight()*.05),50,(obj.getCantidadReal()*50), game));
                            cantMinasFuego++;
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==7 && comprarOmejorar){
                            if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora());
                        }
                    }
                    
                    if(objetoSeleccionado==7 && !comprarOmejorar){
                        if(cantMinasHielo<productos.get(8).getCantidadReal()){
                            posicionarObjeto=true;
                            posicionarMina=true;
                            minas.add(new Mina(1,(int) (game.getWidth()*.34),(int) (game.getHeight()*.6), (int)(game.getWidth()*.03), (int)(game.getHeight()*.05),100,(obj.getCantidadReal()*50), game));
                            cantMinasHielo++;
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==8){
                            if(obj.getLevel()<obj.getLevelMax()){
                            obj.setLevel(obj.getLevel()+1);
                            obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                            comprarOmejorar=true;
                    }
                    
                    if(objetoSeleccionado==9 && comprarOmejorar){
                            if(obj.getLevel()<obj.getLevelMax()){
                                if(!game.isMisilActivado()){
                                    obj.setLevel(obj.getLevel()+1);
                                    obj.setCantidadReal(obj.getCantidadReal()+obj.getCantidadSubir());
                                    obj.setCantBalas(obj.getCantBalas()+obj.getSubirCantBalas());
                                    game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getPrecioMejora()); 
                                }
                        }
                    }
                    
                    if(objetoSeleccionado==9 && !comprarOmejorar){
                        if(!game.isMisilActivado()){
                            game.setMisilActivado(true);

                            game.setLauncher(new Misil_Launcher((int)(game.getWidth()*0.00951683748169839),(int)(game.getHeight()*0.5807291666666666),(int) (game.getWidth()*.06),(int)(game.getHeight()*.08),obj.getCantidadReal(),200,obj.getCantBalas(),obj.getCantBalas(),game));
                            game.getPlayer().setDinero(game.getPlayer().getDinero()-obj.getCosto());
                        }
                        comprarOmejorar=true;
                    }
                }
        }       
    
    
    }
    
    public void posicionarTorretaBalas(Graphics g){
        menuTienda=false;
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentX = components[1];
            Component componentY = components[0];
            Component componentA = components[5];
            
            if(!posicionTorretaBalas){
                    if (componentX.getPollData() <= -0.5){
                        torretas.get(torretas.size()-1).setX(torretas.get(torretas.size()-1).getX()-2);
                    }
                    if (componentX.getPollData() >= 0.5){
                        torretas.get(torretas.size()-1).setX(torretas.get(torretas.size()-1).getX()+2);
                    }
                    if (componentY.getPollData() <= 0.5){
                        torretas.get(torretas.size()-1).setY(torretas.get(torretas.size()-1).getY()-2);
                    }
                    if (componentY.getPollData() >= -0.5){
                        torretas.get(torretas.size()-1).setY(torretas.get(torretas.size()-1).getY()+2);
                    }
                    
                    for(int x = 0; x <24 ; x++){
                             Iterator itr = torretas.iterator();
                             while(itr.hasNext()){
                                 Torreta torr=(Torreta) itr.next();

                                 if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                     torr.setPiso(3);
                                     System.out.println(torr.getPiso());
                                 }


                             }
                         }
                
                    /*for(int x = 24; x <28 ; x++){
                        Iterator itr = torretas.iterator();
                        while(itr.hasNext()){
                            Torreta torr=(Torreta) itr.next();
                            if(torr.intersects(game.getParedes().get(x))){
                                torr.setPiso(3);
                            }
                        }
                    }*/

                    for(int x = 28; x <45 ; x++){
                        Iterator itr = torretas.iterator();
                        while(itr.hasNext()){
                            Torreta torr=(Torreta) itr.next();
                            if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(1);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }

                    for(int x = 0; x <13 ; x++){
                        Iterator itr = torretas.iterator();
                        while(itr.hasNext()){
                            Torreta torr=(Torreta) itr.next();
                            if(game.getParedesTorreta().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(2);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }

                    for(int x = 13; x <18 ; x++){
                        Iterator itr = torretas.iterator();
                        while(itr.hasNext()){
                            Torreta torr=(Torreta) itr.next();
                            if(game.getParedesTorreta().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(1);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }

                    for(int x = 18; x <22 ; x++){
                        Iterator itr = torretas.iterator();
                        while(itr.hasNext()){
                        Torreta torr=(Torreta) itr.next();
                            if(game.getParedesTorreta().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(3);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }
                
                    if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        validarA=false;
                        posicionTorretaBalas=true;
                    }
            }else{
                
                if(intersectObjeto(objetoSeleccionado)){
                    g.drawImage(Assets.mensajeNoPosicion,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                    salirMensajeNoPosicion();
                }else{
                    g.drawImage(Assets.mensajePosicion,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                    salirMensajePosicion();
                }
            }
        }
                    
            
            
    }
    
    public void salirMensajeNoPosicion(){
        menuTienda=false;
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentA = components[5];
            Component componentB = components[6];
            
            if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        validarA=false;
                        posicionTorretaBalas=false;
                        posicionTorretaGranadas=false;
                        posicionMina=false;
                    }
                    
                    
        }
    }
    
   public boolean intersectObjeto(int obj){
       if(obj==3){
           Iterator itr = cuadros.iterator();
                while(itr.hasNext()){
                    CuadrosDeControl cuad=(CuadrosDeControl) itr.next();
                    if(torretas.get(torretas.size()-1).intersects(cuad)){
                        return true;
                    }
                }
                
                itr = game.getParedes().iterator();
                while(itr.hasNext()){
                    Line2D.Double line = (Line2D.Double) itr.next();
                    if(line.intersects(torretas.get(torretas.size()-1).getX(),torretas.get(torretas.size()-1).getY(),torretas.get(torretas.size()-1).getWidth(),torretas.get(torretas.size()-1).getHeight())){
                        return true;
                    }
                }
       }
       
       if(obj==4){
           Iterator itr = cuadros.iterator();
                while(itr.hasNext()){
                    CuadrosDeControl cuad=(CuadrosDeControl) itr.next();
                    if(torretasGranadas.get(torretasGranadas.size()-1).intersects(cuad)){
                        return true;
                    }
                }
                
                itr = game.getParedes().iterator();
                while(itr.hasNext()){
                    Line2D.Double line = (Line2D.Double) itr.next();
                    if(line.intersects(torretasGranadas.get(torretasGranadas.size()-1).getX(),torretasGranadas.get(torretasGranadas.size()-1).getY(),torretasGranadas.get(torretasGranadas.size()-1).getWidth(),torretasGranadas.get(torretasGranadas.size()-1).getHeight())){
                        return true;
                    }
                }
       }
       
       if(obj==6 || obj==7){
           Iterator itr = cuadros.iterator();
                while(itr.hasNext()){
                    CuadrosDeControl cuad=(CuadrosDeControl) itr.next();
                    if(minas.get(minas.size()-1).intersects(cuad)){
                        return true;
                    }
                }
                
                itr = game.getParedes().iterator();
                while(itr.hasNext()){
                    Line2D.Double line = (Line2D.Double) itr.next();
                    if(line.intersects(minas.get(minas.size()-1).getX(),minas.get(minas.size()-1).getY(),minas.get(minas.size()-1).getWidth(),minas.get(minas.size()-1).getHeight())){
                        return true;
                    }
                }
       }
       
      return false; 
   }
        
    
    
    public void salirMensajePosicion(){
        menuTienda=false;
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentA = components[5];
            Component componentB = components[6];
                    
                    if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        validarA=false;
                        posicionTorretaBalas=false;
                        posicionTorretaGranadas=false;
                        posicionMina=false;
                        posicionarObjeto=false;
                        posicionarTorreta=false;
                        posicionarTorretaGranada=false;
                        posicionarMina=false;
                        menuTienda=true;
                    }
                    
                    if(componentB.getPollData()==1 && empezarMensaje){
                        validarB=true;
                    }
                    
                    if(componentB.getPollData()==0 && validarB){
                        validarB=false;
                        posicionTorretaBalas=false;
                        posicionTorretaGranadas=false;
                        posicionMina=false;
                    }
        }
    }
    
    
    public void desplegarLimites(Graphics g){
                Iterator itr = cuadros.iterator();
                while(itr.hasNext()){
                    CuadrosDeControl cuad=(CuadrosDeControl) itr.next();
                    cuad.render(g);
                }
                
                Graphics2D g2d = (Graphics2D) g;
                itr = game.getParedes().iterator();
                while(itr.hasNext()){
                    g.setColor(Color.RED);
                    Line2D.Double line = (Line2D.Double) itr.next();
                    g2d.draw(line);
                }
                
                if(objetoSeleccionado==3){
                   for(int x = 0; x <game.getParedes().size() ; x++){
                    itr = torretas.iterator();
                    while(itr.hasNext()){
                        Torreta torr=(Torreta) itr.next();
                        if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                            desplegarTacha=true;   
                        }
                    }
                }
                
                for(int x = 0; x <cuadros.size() ; x++){
                    itr = torretas.iterator();
                    while(itr.hasNext()){
                        Torreta torr=(Torreta) itr.next();
                        if(torr.intersects(cuadros.get(x))){
                            desplegarTacha=true;    
                        }
                    }
                }
                
                itr=torretas.iterator();
                        while(itr.hasNext()){
                            Torreta torr=(Torreta) itr.next();
                            torr.render(g);    
                        }
                }
                
                
                
                
                if(objetoSeleccionado==4){
                    itr=torretasGranadas.iterator();
                        while(itr.hasNext()){
                            Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                            torr.render(g);    
                        }
                        
                        for(int x = 0; x <game.getParedes().size() ; x++){
                    itr = torretasGranadas.iterator();
                    while(itr.hasNext()){
                        Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                        if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                            desplegarTacha=true;   
                        }
                    }
                }
                
                for(int x = 0; x <cuadros.size() ; x++){
                    itr = torretasGranadas.iterator();
                    while(itr.hasNext()){
                        Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                        if(torr.intersects(cuadros.get(x))){
                            desplegarTacha=true;    
                        }
                    }
                }
                }
                
                if(objetoSeleccionado==6 || objetoSeleccionado==7){
                    itr=minas.iterator();
                        while(itr.hasNext()){
                            Mina torr=(Mina) itr.next();
                            torr.render(g);    
                        }
                        
                        for(int x = 0; x <game.getParedes().size() ; x++){
                    itr = minas.iterator();
                    while(itr.hasNext()){
                        Mina torr=(Mina) itr.next();
                        if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                            desplegarTacha=true;   
                        }
                    }
                }
                
                for(int x = 0; x <cuadros.size() ; x++){
                    itr = minas.iterator();
                    while(itr.hasNext()){
                        Mina torr=(Mina) itr.next();
                        if(torr.intersects(cuadros.get(x))){
                            desplegarTacha=true;    
                        }
                    }
                }
                }
                
                
                        if(desplegarTacha){
                            if(objetoSeleccionado==3){
                                g.drawImage(Assets.tacha,torretas.get(torretas.size()-1).getX(),torretas.get(torretas.size()-1).getY(), torretas.get(torretas.size()-1).getWidth(), torretas.get(torretas.size()-1).getHeight(), null);
                                desplegarTacha=false;
                            }
                            if(objetoSeleccionado==4){
                                g.drawImage(Assets.tacha,torretasGranadas.get(torretasGranadas.size()-1).getX(),torretasGranadas.get(torretasGranadas.size()-1).getY(), torretasGranadas.get(torretasGranadas.size()-1).getWidth(), torretasGranadas.get(torretasGranadas.size()-1).getHeight(), null);
                                desplegarTacha=false;
                            }
                            if(objetoSeleccionado==6 || objetoSeleccionado==7){
                                g.drawImage(Assets.tacha,minas.get(minas.size()-1).getX(),minas.get(minas.size()-1).getY(),minas.get(minas.size()-1).getWidth(),minas.get(minas.size()-1).getHeight(), null);
                                desplegarTacha=false;
                            }
                        }  
                
                
                        
    }
    
    public void desplegarMensajePonerTorreta(Graphics g){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component componenta = components[5];
                    Component componentm = components[1];
                        
                    
                    
                    if(componenta.getPollData()==0){
                        empezarMensajeAcomodo=true;
                    }
                    
                        if(componenta.getPollData()==1 && empezarMensajeAcomodo && !comprarObjeto){
                            empezarMensajeAcomodo=false;
                            desplegarMensaje=true;
                            xSelectMensaje=(int)(game.getWidth()*.332);
                            
                        }
                    
                    
                    if(desplegarMensaje){
                        
                       g.drawImage(Assets.mensaje,(int)(game.getWidth()*.3),(int) (game.getHeight()*.3), (int)(game.getWidth()*.35), (int)(game.getHeight()*.3), null);
                       
                    
                        if(componentm.getPollData() >= .9){
                            moverTiendaDerecha=true;
                        }
                        if(moverTiendaDerecha && componentm.getPollData()<.9){
                            xSelectMensaje=(int) (game.getWidth()*.482);
                            confirmarAcomodo=true;
                            moverTiendaDerecha=false;
                        }

                        //mover a la izquierda
                        if(componentm.getPollData() <= -.9){
                            moverTiendaIzquierda=true;
                        }
                        if(moverTiendaIzquierda && componentm.getPollData()<.9){
                            xSelectMensaje=(int) (game.getWidth()*.332);
                            confirmarAcomodo=false;
                            moverTiendaIzquierda=false;
                        }
                           
                           g.drawImage(Assets.select,xSelectMensaje,(int) (game.getHeight()*.495), (int)(game.getWidth()*.134), (int)(game.getHeight()*.06), null);
                           salirMensajeAcomodoCompra();
                    }
                    
            
        }
      
    }
    
    public void salirMensajeAcomodoCompra(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                    Controller controller = game.getFoundControllers().get(0);
                    controller.poll();
                    Component[] components = controller.getComponents();
                    Component component = components[5];
                    
                    if(component.getPollData()==0){
                        empezarMensaje=true;
                    }
                    
                    
                    
                    if(component.getPollData()==1 && confirmarAcomodo && empezarMensaje){
                            desplegarMensaje=false;
                            acomodarObjeto=true;
                            acomodarTorreta=true;
                            empezarMensaje=false;
                    }
                    
                    if(component.getPollData()==1 && !confirmarAcomodo && empezarMensaje){
                            desplegarMensaje=false;
                            acomodarObjeto=false;
                            acomodarTorreta=false;
                            empezarMensaje=false;
                    }
        }
    }
    
    public void posicionarTorretaGranadas(Graphics g){
        menuTienda=false;
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentX = components[1];
            Component componentY = components[0];
            Component componentA = components[5];
            
            if(!posicionTorretaGranadas){
                    if (componentX.getPollData() <= -0.5){
                        torretasGranadas.get(torretasGranadas.size()-1).setX(torretasGranadas.get(torretasGranadas.size()-1).getX()-2);
                    }
                    if (componentX.getPollData() >= 0.5){
                        torretasGranadas.get(torretasGranadas.size()-1).setX(torretasGranadas.get(torretasGranadas.size()-1).getX()+2);
                    }
                    if (componentY.getPollData() <= 0.5){
                        torretasGranadas.get(torretasGranadas.size()-1).setY(torretasGranadas.get(torretasGranadas.size()-1).getY()-2);
                    }
                    if (componentY.getPollData() >= -0.5){
                        torretasGranadas.get(torretasGranadas.size()-1).setY(torretasGranadas.get(torretasGranadas.size()-1).getY()+2);
                    }
                    
                    for(int x = 0; x <24 ; x++){
                             Iterator itr = torretasGranadas.iterator();
                             while(itr.hasNext()){
                                 Torreta_Granadas torr=(Torreta_Granadas) itr.next();

                                 if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                     torr.setPiso(3);
                                     System.out.println(torr.getPiso());
                                 }


                             }
                         }
                
                    /*for(int x = 24; x <28 ; x++){
                        Iterator itr = torretas.iterator();
                        while(itr.hasNext()){
                            Torreta torr=(Torreta) itr.next();
                            if(torr.intersects(game.getParedes().get(x))){
                                torr.setPiso(3);
                            }
                        }
                    }*/

                    for(int x = 28; x <45 ; x++){
                        Iterator itr = torretasGranadas.iterator();
                        while(itr.hasNext()){
                            Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                            if(game.getParedes().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(1);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }

                    for(int x = 0; x <13 ; x++){
                        Iterator itr = torretasGranadas.iterator();
                        while(itr.hasNext()){
                            Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                            if(game.getParedesTorreta().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(2);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }

                    for(int x = 13; x <18 ; x++){
                        Iterator itr = torretasGranadas.iterator();
                        while(itr.hasNext()){
                            Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                            if(game.getParedesTorreta().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(1);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }

                    for(int x = 18; x <22 ; x++){
                        Iterator itr = torretasGranadas.iterator();
                        while(itr.hasNext()){
                        Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                            if(game.getParedesTorreta().get(x).intersects(torr.getX(),torr.getY(),torr.getWidth(),torr.getHeight())){
                                torr.setPiso(3);
                                System.out.println(torr.getPiso());
                            }
                        }
                    }
                
                    if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        validarA=false;
                        posicionTorretaGranadas=true;
                    }
            }else{
                
                if(intersectObjeto(objetoSeleccionado)){
                    g.drawImage(Assets.mensajeNoPosicion,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                    salirMensajeNoPosicion();
                }else{
                    g.drawImage(Assets.mensajePosicion,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                    salirMensajePosicion();
                    
                }
            }
        }
                    
            
            
    }
    
    public void posicionarMinas(Graphics g){
        menuTienda=false;
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentX = components[1];
            Component componentY = components[0];
            Component componentA = components[5];
            
            if(!posicionMina){
                    if (componentX.getPollData() <= -0.5){
                        minas.get( minas.size()-1).setX( minas.get( minas.size()-1).getX()-2);
                    }
                    if (componentX.getPollData() >= 0.5){
                         minas.get( minas.size()-1).setX( minas.get( minas.size()-1).getX()+2);
                    }
                    if (componentY.getPollData() <= 0.5){
                         minas.get( minas.size()-1).setY( minas.get( minas.size()-1).getY()-2);
                    }
                    if (componentY.getPollData() >= -0.5){
                         minas.get( minas.size()-1).setY( minas.get( minas.size()-1).getY()+2);
                    }
                    
                    
                
                    if(componentA.getPollData()==1 && !validarA){
                        validarA=true;
                    }
                    
                    if(componentA.getPollData()==0 && validarA){
                        validarA=false;
                        posicionMina=true;
                    }
            }else{
                
                if(intersectObjeto(objetoSeleccionado)){
                    g.drawImage(Assets.mensajeNoPosicion,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                    salirMensajeNoPosicion();
                }else{
                    g.drawImage(Assets.mensajePosicion,(int)(game.getWidth()*.1),(int)(game.getHeight()*.1) , (int)(game.getWidth()*.75), (int)(game.getHeight()*.75), null);
                    salirMensajePosicion();
                }
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
                        noMejorarMisil=false;
                        comprarOmejorar=true;
                    }
      }
      
  }
    
   public boolean validarObjeto(int obj){
        if(objetoSeleccionado==0 && !comprarOmejorar && game.getPlayer().getCantGranadasFuego() >=productos.get(2).getCantidadReal()){
                return true;
        }
        if(objetoSeleccionado==1 && !comprarOmejorar && game.getPlayer().getCantGranadasFuego() >=productos.get(2).getCantidadReal()){
            return true;
        }   
        if(objetoSeleccionado==3 &&  !comprarOmejorar && torretas.size()>=productos.get(5).getCantidadReal()){
            return true;
        }        
        if(objetoSeleccionado==4 && !comprarOmejorar && torretasGranadas.size()>=productos.get(5).getCantidadReal()){
            return true;
        }
        if(objetoSeleccionado==6 && !comprarOmejorar && cantMinasFuego>=productos.get(8).getCantidadReal()){
            return true;
        }
        if(objetoSeleccionado==7 && !comprarOmejorar && cantMinasHielo>=productos.get(8).getCantidadReal()){
            return true;
        }
        if(objetoSeleccionado==9 && !comprarOmejorar && game.isMisilActivado()){
            return true;
        }
       
       
       return false;
   } 
  
}
    

