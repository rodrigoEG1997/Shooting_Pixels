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
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *
 * @author 
 */
public class Game implements Runnable {
    private BufferStrategy bs;//to have several buffers when displaying
    private Graphics g;//to paint objects
    private Display display;//to display in the game
    String title;// title of the window
    private int width;// width of the window
    private int height;	//height of the	window
    private Thread thread; //thread to create the game
    private boolean running;//to set the game
    private int x;
    private Player player;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private boolean finish=false;
    private int vidas=0;
    private boolean colision;
    private ArrayList<Double> porcientos;    
    private ArrayList<Controller> foundControllers;
    private int destinox;
    private int destinoy;
    private ArrayList<Bullet> bullets;
    private ArrayList<Escalera> escaleras;
    private ArrayList<Zombie> zombies;
    private ArrayList<Line2D.Double> paredes;
    private ArrayList<Integer> F1P1X;
    private ArrayList<Integer> F1P1Y;
    private ArrayList<Integer> F1P2X;
    private ArrayList<Integer> F1P2Y;
    private ArrayList<Integer> F1P3X;
    private ArrayList<Integer> F1P3Y;
    private ArrayList<Integer> F1P4X;
    private ArrayList<Integer> F1P4Y;
    private ArrayList<Integer> F1P5X;
    private ArrayList<Integer> F1P5Y;
    private ArrayList<Integer> F2P62X;
    private ArrayList<Integer> F2P62Y;
    private ArrayList<Integer> F2P63X;
    private ArrayList<Integer> F2P63Y;
    private ArrayList<Integer> F2P75X;
    private ArrayList<Integer> F2P75Y;
    private ArrayList<Integer> F2P73X;
    private ArrayList<Integer> F2P73Y;
    private Tower tower;
    private Tienda_vidas tienda;
    private Tienda_weapon tienda2;
    private Tienda_armas tienda3;
    private Tienda_survivor tienda4;
    private int vidaZombie = 200;
    private boolean pausaTienda = false;
    private Floyd floyd;
    private Menu menu;
    private ArrayList<Dinero> dinero;
    
    private ArrayList<Explosion> explosiones_zombies;
    private ArrayList<Explosion> explosiones;
    
    private SobrevivienteRambo rambo;
    private SobrevivienteRocky rocky;
   /*  private SobrevivienteEscudo sEscudo;*/
    private ArmaExtra armaExtra;

    private Torreta torreta;
    private ArrayList<Line2D.Double> paredesTorreta;


    private Torreta_Granadas torretagra;
    private Sobreviviente_Healer healer;
    private boolean pause; // To pause the game
    private SoundClipTest sound; //Game sound
    ////////////camviar inicializacion

    private boolean swatActivo = false; // para ver si el superviviente rambo esta activo
    private boolean ninjaActivo = false; // para ver si el superviviente rocky esta activo
    private boolean hadaActivo = false;
    private boolean droneActivo = false;
    private boolean misilActivado = false;

    private int contTicksBala=0;
    private int cantBalasSeguidas=1;
    private int contBalas=0;
    private double vidaPlayer=1.0;
    private double vidaTorre=1.0;
    private Sobreviviente_Healer hada;
    
    private boolean contTicksNoDisparar=true;
    private int contTicks= 0;
    private Mina mina;
    private Drone drone;
    
    private Misil_Launcher launcher;
    
    private boolean pauseControl=false;
    private boolean plix =false;
    private ArrayList<Zombie> zombAux;
    private int ronda = 0; 
    private int numZomTotal; 
    private int numZomNorm; 
    private int numZomFlas; 
    private int numZomMama; 
    private int numZomKami; 
    private int nextZombi; 
    private int contNextZomb = 0; 
    private boolean yanisequenombre = false;
    private int contRenderNuevaRonda = 0;
    private boolean nuevaRonda = false;

    /**
     *
     */
    public enum STATE{

        /**
         *
         */
        Menu,

        /**
         *
         */
        Game
    }
    
    /**
     *
     */
    public static STATE gameState = STATE.Menu; //creacion del Menu HOMEPAGE del juego
    
    /**
    * constructor
    * @param title
    * @param width
    * @param height 
    */
    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        running	= false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
       // pause= false;//Game Pause boolean variable initialized on false
        menu= new Menu(this);
    }
    
    /**
    * 
    */

    private void init(){
        display = new Display(title, getWidth(), getHeight());		
        Assets.init();
        
        tower = new Tower(0,(int)(getHeight()*0.34),(int)(getWidth()*0.025),(int)(getHeight()*0.14),100,this);
        tienda = new Tienda_vidas(0,(int)(getHeight()*0.77),   (int)(getWidth()*0.10),(int)(getHeight()*0.12),1,this);
        tienda2 = new Tienda_weapon((int)(getWidth()*0.785),0,(int)(getWidth()*0.10),(int)(getHeight()*0.12),1,this);
        tienda3 = new Tienda_armas((int)(getWidth()*0.785),(int)(getHeight()*0.790),(int)(getWidth()*0.10),(int)(getHeight()*0.12),1,this);
        tienda4 = new Tienda_survivor(0,0,(int)(getWidth()*0.10),(int)(getHeight()*0.12),1,this);
        armaExtra = new ArmaExtra(0,0,0,0,this);
        player = new Player((int) (getWidth()*.06),(int)(getHeight()*.38),(int) (getWidth()*.025),(int)(getHeight()*.06),3, 100, 10,10,1000, this);
        tower = new Tower(-20,(int)(getHeight()*0.16),   (int)(getWidth()*0.08),(int)(getHeight()*0.3),100,this);
        
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager); 
        
        bullets = new ArrayList<Bullet>();
        
        foundControllers = new ArrayList<>();
        searchForControllers();       
        
        explosiones_zombies = new ArrayList<>();
        explosiones = new ArrayList<>();
        zombAux = new ArrayList<>();
        paredes = new ArrayList<>();
        inicializarParedes();
        
        paredesTorreta = new ArrayList<>();
        inicializarParedesTorreta();
        
        escaleras = new ArrayList<>();
        inicializarEscaleras();
        floyd = new Floyd(this);
        dinero = new ArrayList<>();
        sound = new SoundClipTest("game_sound");
        sound = new SoundClipTest("zombie_sound");
        zombies = new ArrayList<>();
        zombAux = new ArrayList<>();
        
        rambo = null;
        rocky = null;
        hada = null;
        drone = null;
        torreta = new Torreta(300,(int)(getHeight()*.48),(int) (getWidth()*.025),(int)(getHeight()*.06),3,200,500,this);
        torretagra = new Torreta_Granadas(300,(int)(getHeight()*.48)    ,(int) (getWidth()*.06),(int)(getHeight()*.12),3,1,1,this);
        mina = new Mina(0,200,300, (int)(this.getWidth()*.02), (int)(this.getHeight()*.03),50,2, this);
        launcher =null;
       
    }

    /**
     * 
     */
    public void run(){
        init();

        int fps = 50;
        double timeTick=1000000000/fps;
        double delta=0;
        long now;
        long lastTime = System.nanoTime();
        while (running){
            now = System.nanoTime();
            delta += (now - lastTime)/timeTick;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                delta --;
            }
            if(finish){
                stop();
            }
        }
    }
    
    /**
     * 
     */
    private void tick(){
       // System.out.println( explosiones.size() );

        
        keyManager.tick();
        if (gameState == STATE.Game  ){
            //pause = this.getKeyManager().p;
            // PAUSA CON EL CONTROL 
            int contC=0;
            for(int i = 0; i < this.getFoundControllers().size(); i++){
            Controller controller = this.getFoundControllers().get(0);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentP = components[12];
            Component componentA = components [5];
                    if (componentP.getPollData()== 0 && !pause && !pauseControl ){
                        pause= false;
                        pauseControl= false;
                        plix=false;
                    }
                    if(componentP.getPollData() == 1 && !pauseControl && !pause && !plix){
                        pauseControl= true; 
                    }
                    if(componentP.getPollData() == 0 && pauseControl && !pause && !plix){
                        pause=true; 
                        plix= true;
                    }
                    if(componentP.getPollData() == 1 && pause && pauseControl && plix){
                        pause= false;
                        pauseControl= false;
                      // plix= false;
                    }   
            }
            
             
                
             //pause=false;
            if(!(pause) && player.getVida() > 0 && tower.getVida() > 0){//=================== PAUSE ============================
                //sEscudo.tick();
                tower.tick();
                tienda.tick();
                tienda2.tick();
                tienda3.tick();
                tienda4.tick();
                if(pausaTienda){

                }else{
                    if(zombies.isEmpty() && !yanisequenombre ){ 
                        vidaZombie = vidaZombie + 20;
                        ronda++;                      
                        nuevaRonda = true;
                         //sacar el numero total de zombies de la ronda 
                        numZomTotal =(int)(24 * (.15 * ronda)); 
                        int temp = numZomTotal; 
                         //sumar la mitad de los zombies de tipo normal, mas un random de la otra mitad 
                        numZomNorm = numZomTotal / 2 + (int) (Math.random() * (numZomTotal / 2)); 
                        //restar los zombies que ya llevamos 
                        numZomTotal = numZomTotal - numZomNorm; 
                        //para zombie flash sacar un random de 0 a los que queden de zombies totales restantes 
                        numZomFlas = (int) (Math.random() * numZomTotal); 
                        //restar los zombies para ver cuantos quedan 
                        numZomTotal = numZomTotal - numZomFlas; 
                        //para zombies mas fuerte sacar random de 0 a los totales restantes 
                        numZomMama = (int) (Math.random() * numZomTotal); 
                        //reronda++;         star los zombies para ver cuantos quedan 
                        numZomTotal = numZomTotal - numZomMama; 
                        //los restantes ponerlos directo en numero de zombies kamikaze 
                        numZomKami = numZomTotal; 
                        numZomTotal = temp; 
                        initZombiesRonda(); 
                         nextZombi = (int) (Math.random() * 80); 
                        //System.out.println("eeeeeeeeeeee" +numZomTotal); 
                    } 
                    if(!zombAux.isEmpty() ){ 
                        //System.out.println("wwwwwwwwwwwww"+zombAux.size()); 
                        contNextZomb++; 
                        if(contNextZomb >= nextZombi && zombies.size() < 20){ 
                           // System.out.println("weeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeet");
                            yanisequenombre =false;
                            contNextZomb = 0; 
                            zombies.add( zombAux.get(0) ); 
                            zombAux.remove(0); 
                            nextZombi = (int) (Math.random() * 200); 
                        } 
                    } 
                    
                    tickearGranadasPlayer();

                    if(swatActivo){ 
                        rambo.tick();
                        
                        //colision rambo con los zombies
                        Iterator itr = zombies.iterator();
                        while(itr.hasNext()){
                            Zombie zomb = (Zombie) itr.next();
                            if(zomb.intersects(rambo) && zomb.isbAtaque()){
                                rambo.setVida(rambo.getVida() - zomb.getDano());
                                zomb.setbAtaque(false);
                            }
                        }
                        
                        //eliminar rambo si ya no tiene vida
                        if(rambo.getVida() <= 0 ){
                            rambo = null;
                            swatActivo = false;
                        }
                        
                        //cambio de piso rambo
                        itr = escaleras.iterator();
                        while(itr.hasNext()){
                            Escalera esca = (Escalera) itr.next();
                            esca.tick();
                            if(esca.intersects(rambo)){
                                rambo.setPiso(esca.getPiso());
                            }
                        }
                    }
                    
                    if(hadaActivo){ 
                        hada.tick(); 
                    }
                    
                    if(ninjaActivo){ 
                        rocky.tick();
                        
                        //colision rocky con zombies
                        Iterator itr = zombies.iterator();
                        while(itr.hasNext()){
                            Zombie zomb = (Zombie) itr.next();
                            if(zomb.intersects(rambo) && zomb.isbAtaque()){
                                rocky.setVida(rocky.getVida() - zomb.getDano());
                                zomb.setbAtaque(false);
                            }
                        }
                        
                        //eliminar rocky si ya no tiene vida
                        if(rocky.getVida() <= 0 ){
                            rocky = null;
                            ninjaActivo = false;
                        }
                        
                        //cambio de piso rocky
                        itr = escaleras.iterator();
                        while(itr.hasNext()){
                            Escalera esca = (Escalera) itr.next();
                            esca.tick();
                            if(esca.intersects(rocky)){
                                rocky.setPiso(esca.getPiso());
                            }
                        }
                    }
                    
                    if(droneActivo){ 
                        drone.tick(); 
                        if(drone.getContBalas() >= drone.getCantBalas() ){ 
                                drone = null; 
                                droneActivo = false; 
                        } 
                    }
                    
                    if(misilActivado){
                        launcher.tick();
                    }
                    
                    Iterator itr = tienda3.getTorretas().iterator();
                    while(itr.hasNext()){
                        Torreta torr=(Torreta) itr.next();
                        torr.tick();
                        if(torr.getCantBalas()<=0){
                            tienda3.getTorretas().remove(torr);
                            itr = tienda3.getTorretas().iterator();
                        }
                    }

                    itr = tienda3.getTorretasGranadas().iterator();
                    while(itr.hasNext()){
                        Torreta_Granadas torr =(Torreta_Granadas) itr.next();
                        torr.tick();
                        if(torr.getCantBalas()<=0 && torr.getGranadas_torreta().size() > 0){
                            tienda3.getTorretasGranadas().remove(torr);
                            itr = tienda3.getTorretasGranadas().iterator();
                        }
                        
                        Iterator itr2 = torr.getGranadas_torreta().iterator();
                        while(itr2.hasNext()){
                            Granada gran = (Granada) itr2.next();
                            gran.tick(); 
                        }
                        
                        //ver si las granadas de la torreta de granadas ya llegaron a su destino
                        itr2 = torr.getGranadas_torreta().iterator();
                        while(itr2.hasNext()){
                            Granada gran=(Granada) itr2.next();
                            if( (gran.getX() + (gran.getWidth() / 2) - 5) < gran.getxDestino() && (gran.getX() + (gran.getWidth() / 2) + 5) > gran.getxDestino() && (gran.getY() + (gran.getHeight() / 2) - 5) < gran.getyDestino() && (gran.getY() + (gran.getHeight() / 2) + 5) > gran.getyDestino()  ){
                                //System.out.println("si entre men");
                                Explosion exp = new Explosion(0,gran.getX() + (gran.getWidth() / 2),gran.getY() + (gran.getHeight() / 2 ),1,1,30,4,this);
                                explosiones.add(exp);
                                sound = new SoundClipTest("sonido_boom_bomba"); // SONIDO DE BOOOM!!!
                                int xExp = exp.getX();
                                int yExp = exp.getY();
                                double radioExp = exp.getRadio();

                                //colision de explosion con otro zombie
                                Iterator itr3 = zombies.iterator();
                                while(itr3.hasNext()){
                                    Zombie zomb = (Zombie) itr3.next();
                                    if(DistanciaDosPuntos( zomb.getX() + (zomb.getWidth() / 2), xExp, zomb.getY() + (zomb.getHeight() / 2) , yExp ) <= radioExp ){
                                        zomb.setVidaZ(zomb.getVidaZ() - torr.getDano());
                                    }                                  
                                }
                                torr.getGranadas_torreta().remove(gran);
                                itr2 = torr.getGranadas_torreta().iterator();
                            }               
                        }
                    }
                    
                    itr = tienda3.getMinas().iterator();
                    while(itr.hasNext()){
                        Mina min=(Mina) itr.next();
                        min.tick();
                        
                        if( min.getTipo() == 0 ){
                            Iterator itr3 = zombies.iterator();
                            while(itr3.hasNext()){
                                Zombie zomb = (Zombie) itr3.next();
                                if( zomb.intersects(min) ){
                                    Explosion exp = new Explosion(0,min.getX() + (min.getWidth() / 2),min.getY() + (min.getHeight() / 2 ),1,1,100,4,this);
                                    explosiones.add(exp);
                                    sound = new SoundClipTest("sonido_boom_bomba"); // SONIDO DE BOOOM!!!
                                    int xExp = exp.getX();
                                    int yExp = exp.getY();
                                    double radioExp = exp.getRadio();

                                    //colision de explosion con otro zombie
                                    Iterator itr4 = zombies.iterator();
                                    while(itr4.hasNext()){
                                        Zombie zombi = (Zombie) itr4.next();
                                        if(DistanciaDosPuntos( zombi.getX() + (zombi.getWidth() / 2), xExp, zombi.getY() + (zombi.getHeight() / 2) , yExp ) <= radioExp ){
                                            zombi.setVidaZ(zombi.getVidaZ() - min.getDano());
                                        }                                  
                                    }
                                    tienda3.getMinas().remove(min);
                                    tienda3.setCantMinasFuego(tienda3.getCantMinasFuego() - 1);
                                    itr = tienda3.getMinas().iterator();
                                }                  
                            }
                        }
                        else{
                            Iterator itr3 = zombies.iterator();
                            while(itr3.hasNext()){
                                Zombie zomb = (Zombie) itr3.next();
                                if( zomb.intersects(min) ){
                                    Explosion exp = new Explosion(1,min.getX() + (min.getWidth() / 2),min.getY() + (min.getHeight() / 2 ),1,1,min.getRadio() ,4 ,this);
                                    explosiones.add(exp);
                                    sound = new SoundClipTest("hielo_sound"); // SONIDO DE BOOOM!!!
                                    int xExp = exp.getX();
                                    int yExp = exp.getY();
                                    double radioExp = exp.getRadio();

                                    //colision de explosion con otro zombie
                                    Iterator itr4 = zombies.iterator();
                                    while(itr4.hasNext()){
                                        Zombie zombi = (Zombie) itr4.next();
                                        if(DistanciaDosPuntos( zombi.getX() + (zombi.getWidth() / 2), xExp, zombi.getY() + (zombi.getHeight() / 2) , yExp ) <= radioExp ){
                                            zombi.setCongelado(true);
                                            zombi.setTicksCongelado(min.getDano());
                                        }                                  
                                    }

                                    tienda3.getMinas().remove(min);
                                    tienda3.setCantMinasHielo(tienda3.getCantMinasHielo() - 1);
                                    itr = tienda3.getMinas().iterator();
                                }                  
                            }
                        }
                    }

                    for(int i = 0;  i < dinero.size(); i++){
                        dinero.get(i).tick();
                    }
                    
                    player.tick();
                    armaExtra.tick();
                    cambiarPisoPlayer();
                    //PLAYER DISPARANDO
                    if(player.isValBalas() && this.getKeyManager().space){
                        bullets.add(new Bullet(player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2) ,5,5,5,this.getMouseManager().getX(),this.getMouseManager().getY(),getPlayer().getPiso(),1,player.getDano(),this));
                        sound = new SoundClipTest("sonido_disparo_player");
                    }

                    //iterar sobre el arreglo de las balas
                    itr = bullets.iterator();
                    while(itr.hasNext()){
                        Bullet bull=(Bullet) itr.next();
                        bull.tick();

                        // iterar sobre el arreglo de las balas dentro del iterador de balas para checar colisiones 
                        Iterator itr2 = zombies.iterator();
                        int cont = 0;
                        while(itr2.hasNext()){
                            Zombie zom=(Zombie) itr2.next();

                            //colision entre zombie y bala
                            if((bull.getPiso() == zom.getPiso() && bull.intersects(zom)) || bull.getPiso() == 4 && bull.intersects(zom)){
                                zom.setVidaZ(zom.getVidaZ()- bull.getDano());
                                bullets.remove(bull);
                                itr = bullets.iterator();
                            }
                            cont++;
                        }

                        //cuando la bala esta en al piso uno, colisiona con todas las paredes
                        if(bull.getPiso() == 1){
                            Iterator itr3 = paredes.iterator();
                            while(itr3.hasNext()){
                                Line2D.Double line = (Line2D.Double) itr3.next();
                                //eliminar bala
                                if(line.intersects(bull.getX(),bull.getY(),bull.getWidth(),bull.getHeight())){
                                    bullets.remove(bull);
                                    itr = bullets.iterator();
                                }
                            } 
                        }

                        //cuando la bala este en el piso dos, colisiona con las paredes del piso 2 y 3
                        if(bull.getPiso() == 2){
                            Iterator itr3 = paredes.iterator();
                            for(int x = 0;x < 16;x++){
                                Line2D.Double line = (Line2D.Double) itr3.next();
                                //eliminar bala
                                if( line.intersects( bull.getX(),bull.getY(),bull.getWidth(),bull.getHeight() ) ){
                                    bullets.remove(bull);
                                    itr = bullets.iterator();
                                }
                            }
                        }
                    }

                    //iterar sobre el arreglo de zombie
                    itr = zombies.iterator();
                    while(itr.hasNext()){
                        Zombie zom = (Zombie) itr.next();
                        zom.tick();
                        if(zom.getVidaZ()<=0){                           
                            dinero.add(new Dinero(zom.getX(),zom.getY(),zom.getWidth(),zom.getHeight(),10,this));
                            //System.out.println((zom.getX() + (zom.getWidth() / 2)) + "  " + (zom.getY() + (zom.getHeight() / 2 )) );
                            if(zom.getTipoZombie() == 4 ){
                                Explosion exp = new Explosion(0,zom.getX() + (zom.getWidth() / 2),zom.getY() + (zom.getHeight() / 2 ),1,1,40,zom.getPiso(),this);
                                explosiones_zombies.add(exp);
                                sound = new SoundClipTest("sonido_boom_bomba"); // SONIDO DE BOOOM!!!
                                int xExp = exp.getX();
                                int yExp = exp.getY();
                                double radioExp = exp.getRadio();
                                int pisoExp = exp.getPiso();

                                //colision de explosion con otro zombie
                                Iterator itr3 = zombies.iterator();
                                while(itr3.hasNext()){
                                    Zombie zomb = (Zombie) itr3.next();
                                    if( zomb.getPiso() == pisoExp && DistanciaDosPuntos( zomb.getX() + (zomb.getWidth() / 2), xExp, zomb.getY() + (zomb.getHeight() / 2) , yExp ) <= radioExp ){
                                        zomb.setVidaZ(zomb.getVidaZ() - (zomb.getVidaZ() / 4) );
                                    }                                  
                                }

                                //colision de explosion con player
                                if( player.getPiso() == pisoExp && DistanciaDosPuntos( player.getX() + (player.getWidth() / 2), xExp, player.getY() + (player.getHeight() / 2) , yExp ) <= radioExp  ){
                                    player.setVida( player.getVida() - 50 );
                                }

                                //colision de explosion con torre
                                if( DistanciaDosPuntos( tower.getX() + (tower.getWidth() / 2), xExp, tower.getY() + (tower.getHeight() / 2) , yExp ) <= radioExp ){
                                    tower.setVida( tower.getVida() - 50);
                                }
                                //falta colision con los sobrevivientes
                                
                            }

                            zombies.remove(zom);
                            itr = zombies.iterator();
                        }
                        // control de cambio de piso de los zombies
                        cambiarPisoZombie(zom);
                    }
                    
                    itr = zombies.iterator();
                    while(itr.hasNext()){
                        Zombie zomb = (Zombie) itr.next();
                        //colision torre con zombies
                        if(zomb.intersects(tower) && zomb.isbAtaque() ){
                            tower.setVida(tower.getVida() - zomb.getDano());
                            zomb.setbAtaque(false);
                        }
                        //colision player con zombies
                        if(zomb.intersects(player) && zomb.isbAtaque()){
                            player.setVida(player.getVida() - zomb.getDano());
                            zomb.setbAtaque(false);
                        }
                    }

                    //iterar sobre la clase explosiones
                    itr = explosiones_zombies.iterator();
                    while(itr.hasNext()){
                        Explosion exp = (Explosion) itr.next();
                        exp.tick();
                        //System.out.println(exp.getX() + "   " + exp.getY());
                        if(exp.isFinish()){
                            explosiones_zombies.remove(exp);
                            itr = explosiones_zombies.iterator();
                        }
                    }
                    //
                    itr = explosiones.iterator();
                    while(itr.hasNext()){
                        Explosion expl = (Explosion) itr.next();
                        expl.tick();
                        if(expl.isFinish() ){
                            explosiones.remove(expl);
                            itr = explosiones.iterator();
                        }
                    }
                    
                    //accesar a los componentes del control 
                    for(int i = 0; i < foundControllers.size(); i++){
                        Controller controller = foundControllers.get(i);
                        controller.poll();
                        Component[] components = controller.getComponents();
                        
                        if(components[4].getPollData() < -.7){
                            if(player.isValBalas()){
                                if((contTicksBala%10==0 || contTicksNoDisparar)&& contBalas<cantBalasSeguidas && !player.isTiempoCargaBalas()){
                                    bullets.add(new Bullet(player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2) ,5,5,5,1,1,getPlayer().getPiso(),1,player.getDano(), this));

                                    contTicksNoDisparar=false;
                                    player.setCantBalas(player.getCantBalas()-1);
                                    contBalas++;
                                    sound = new SoundClipTest("sonido_disparo_player");
                                }
                            }
                            
                        }
                        
                        if(components[4].getPollData()>-.3){
                            contBalas=0;
                            contTicksNoDisparar=true;
                        }
                    }
                  contTicksBala++; 
                }
            }//=================== END PAUSE ==================================
        }else if(gameState == STATE.Menu){
            
            menu.tick();
        }
    }
    
    private void render(){
        bs = display.getCanvas().getBufferStrategy();	// get the buffer strategy from the display

        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
        }else{
            if (gameState == STATE.Game){
                g = bs.getDrawGraphics();
                g.drawImage(Assets.background, 0, 0, width,	height,	null);

                Iterator itr = bullets.iterator();
                while(itr.hasNext()){
                    Bullet bull=(Bullet) itr.next();
                    bull.render(g);
                }                
                
                if(droneActivo){ 
                        drone.render(g); 
                } 
                
                if(ninjaActivo){
                        rocky.render(g);
                }
                
                if(swatActivo){
                        rambo.render(g);
                }
                
                
                if(hadaActivo){ 
                    hada.render(g); 
                }
                
                /*
                itr = escaleras.iterator();
                while(itr.hasNext()){
                    Escalera esc=(Escalera) itr.next();
                    esc.render(g);
                }*/
                
                
                itr = zombies.iterator();
                while(itr.hasNext()){
                    Zombie zom = (Zombie) itr.next();
                    zom.render(g);
                }
                
                Graphics2D g2d = (Graphics2D) g;

                /*
                itr = paredes.iterator();
                while(itr.hasNext()){
                    g.setColor(Color.RED);
                    Line2D.Double line = (Line2D.Double) itr.next();
                    g2d.draw(line);
                }*/
                 
                /*
                itr = paredesTorreta.iterator();
                while(itr.hasNext()){
                    g.setColor(Color.BLUE);
                    Line2D.Double lines = (Line2D.Double) itr.next();
                    g2d.draw(lines);
                }
                
                */

                itr = dinero.iterator();
                while(itr.hasNext()){
                    contTicks++;
                  // System.out.println("CONT TICKS DINERO" + contTicks);
                      Dinero money = (Dinero) itr.next();
                     
                   
                      
                    //  for(int i=contTicks; i<=50; i++){
                          money.render(g);
                    //      System.out.println("CONT TICKS DINERO loop " +i+" "+ contTicks);
                   //   }
                      
                    //  for(int i=contTicks; i>55 && i<100; i++){
                       //   money.render(g);
                         // System.out.println("CONT TICKS DINERO3  " + contTicks);
                    //  }
                   
                }
                

                if(player.isDesplegarGranada()){
                    Iterator itrG = player.getGranadas().iterator();
                    while(itrG.hasNext()){
                        Granada gran=(Granada) itrG.next();
                        gran.render(g);
                    }
                }
                
                itr = tienda3.getTorretas().iterator();
                while(itr.hasNext()){
                    Torreta torr=(Torreta) itr.next();
                    torr.render(g);
                }

                itr = tienda3.getTorretasGranadas().iterator();
                while(itr.hasNext()){
                    Torreta_Granadas torr=(Torreta_Granadas) itr.next();
                    torr.render(g);
                    
                    Iterator itr2 = torr.getGranadas_torreta().iterator();
                    while(itr2.hasNext()){
                        Granada gra = (Granada) itr2.next();
                        gra.render(g);
                     }
                }

                itr = tienda3.getMinas().iterator();
                while(itr.hasNext()){
                    Mina torr=(Mina) itr.next();
                    torr.render(g);
                }

                formatoAbajo(g);

                tienda.render(g);
                tienda2.render(g);
                tienda3.render(g);
                tienda4.render(g);
                armaExtra.render(g);

                
                /*itr = floyd.getNodos().iterator();

                while(itr.hasNext()){
                    Node nod = (Node) itr.next();
                    nod.render(g);
                }*/

                tower.render(g);
                player.render(g);

                itr = explosiones_zombies.iterator();
                while(itr.hasNext()){
                    Explosion exp = (Explosion) itr.next();
                    exp.render(g);
                }
                
                itr = explosiones.iterator();
                while(itr.hasNext()){
                    Explosion exp = (Explosion) itr.next();
                    exp.render(g);
                }
                
                Font leter = new Font ("Arial", 1, 50);
                g.setFont (leter);
                g.setColor(Color.RED);
                g.drawString ("" + ronda+"", (int)(this.getWidth() * .959722) ,(int)(this.getHeight() * .885667) );
                
                if(nuevaRonda && ronda != 1){
                    contRenderNuevaRonda ++;
                    g.drawImage(Assets.ronda,(int)(0.3409722)  , (int)(0.31481),getWidth(), getHeight(), null);
                    if(contRenderNuevaRonda >= 50){
                        nuevaRonda = false;
                        contRenderNuevaRonda = 0;
                    }
                }
                
                if(misilActivado){
                  launcher.render(g);  
                }                
                
                if (pause){
                    
                    drawPause(g);
                    
                }
                if(player.getVida() <= 0 || tower.getVida() <= 0 ){
                    g.drawImage(Assets.game_over,(int)(0.386800)  , (int)(0.32661),getWidth(), getHeight(), null);

                }
                
            } else { 
                g = bs.getDrawGraphics();
               
                menu.render(g);
            }
            bs.show();
            g.dispose();
        }
    }

    /**
     *
     */
    public synchronized void start(){
        if(!running){
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     *
     * @return
     */
    public int getContTicks() {
        return contTicks;
    }

    /**
     *
     * @param contTicks
     */
    public void setContTicks(int contTicks) {
        this.contTicks = contTicks;
    }

    /**
     *
     * @return
     */
    public boolean isPauseControl() {
        return pauseControl;
    }

    /**
     *
     * @param pauseControl
     */
    public void setPauseControl(boolean pauseControl) {
        this.pauseControl = pauseControl;
    }

    /**
     *
     * @return
     */
    public boolean isPlix() {
        return plix;
    }

    /**
     *
     * @param plix
     */
    public void setPlix(boolean plix) {
        this.plix = plix;
    }

    
    
    
    /**
     *
     */
    public synchronized void stop(){
        if(running){
            running = false;
            try{
                thread.join();
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
        
    /**
    * 
    * @return 
    */
    public int getWidth(){
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight(){
        return height;
    }

    /**
     *
     * @return
     */
    public Player getPlayer(){
        return player;
    }
    
    /**
     *
     * @return
     */
    public int getVidaZombie(){
        return vidaZombie;
    }
    
    /**
     *
     * @param vida
     */
    public void setVidaZombie(int vida){
        this.vidaZombie=vida;
    }

    /**
     *
     * @return
     */
    public boolean isPausaTienda() {
        return pausaTienda;
    }

    /**
     *
     * @param pausaTienda
     */
    public void setPausaTienda(boolean pausaTienda) {
        this.pausaTienda = pausaTienda;
    }
   
    /**
     * get de keyManager
     * @return 
     */    
    public MouseManager getMouseManager(){
        return mouseManager;
    }

    /**
     *
     * @return
     */
    public MouseManager getMouseInput(){
        return mouseManager;
    }

    /**
     *
     * @return
     */
    public Sobreviviente_Healer getHealer() {
        return healer;
    }

    /**
     *
     * @param healer
     */
    public void setHealer(Sobreviviente_Healer healer) {
        this.healer = healer;
    }

    /**
     *
     * @return
     */
    public SobrevivienteRambo getRambo() {
        return rambo;
    }

    /**
     *
     * @param rambo
     */
    public void setRambo(SobrevivienteRambo rambo) {
        this.rambo = rambo;
    }

    /**
     *
     * @return
     */
    public KeyManager getKeyManager(){
        return keyManager;
    }

    /**
     *
     * @param t
     */
    public void setColision(boolean t){
        this.colision=t;
    }

    /**
     *
     * @return
     */
    public boolean getColision(){
        return colision;
    }

    /**
     *
     * @return
     */
    public ArrayList<Line2D.Double> getParedes() {
        return paredes;
    }

    /**
     *
     * @param paredes
     */
    public void setParedes(ArrayList<Line2D.Double> paredes) {
        this.paredes = paredes;
    }

    /**
     *
     * @return
     */
    public ArrayList<Double> getPorcientos() {
        return porcientos;
    }

    /**
     *
     * @param porcientos
     */
    public void setPorcientos(ArrayList<Double> porcientos) {
        this.porcientos = porcientos;
    }

    /**
     *
     * @return
     */
    public ArrayList<Escalera> getEscaleras() {
        return escaleras;
    }

    /**
     *
     * @param escaleras
     */
    public void setEscaleras(ArrayList<Escalera> escaleras) {
        this.escaleras = escaleras;
    }

    /**
     *
     * @return
     */
    public ArrayList<Controller> getFoundControllers() {
        return foundControllers;
    }

    /**
     *
     * @param foundControllers
     */
    public void setFoundControllers(ArrayList<Controller> foundControllers) {
        this.foundControllers = foundControllers;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getVidas() {
        return vidas;
    }

    /**
     *
     * @param vidas
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     *
     * @return
     */
    public int getDestinox() {
        return destinox;
    }

    /**
     *
     * @param destinox
     */
    public void setDestinox(int destinox) {
        this.destinox = destinox;
    }

    /**
     *
     * @return
     */
    public int getDestinoy() {
        return destinoy;
    }

    /**
     *
     * @param destinoy
     */
    public void setDestinoy(int destinoy) {
        this.destinoy = destinoy;
    }

    /**
     *
     * @return
     */
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     *
     * @param bullets
     */
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    /**
     *
     * @return
     */
    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    /**
     *
     * @param zombies
     */
    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P1X() {
        return F1P1X;
    }

    /**
     *
     * @param F1P1X
     */
    public void setF1P1X(ArrayList<Integer> F1P1X) {
        this.F1P1X = F1P1X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P1Y() {
        return F1P1Y;
    }

    /**
     *
     * @param F1P1Y
     */
    public void setF1P1Y(ArrayList<Integer> F1P1Y) {
        this.F1P1Y = F1P1Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P2X() {
        return F1P2X;
    }

    /**
     *
     * @param F1P2X
     */
    public void setF1P2X(ArrayList<Integer> F1P2X) {
        this.F1P2X = F1P2X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P2Y() {
        return F1P2Y;
    }

    /**
     *
     * @param F1P2Y
     */
    public void setF1P2Y(ArrayList<Integer> F1P2Y) {
        this.F1P2Y = F1P2Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P3X() {
        return F1P3X;
    }

    /**
     *
     * @param F1P3X
     */
    public void setF1P3X(ArrayList<Integer> F1P3X) {
        this.F1P3X = F1P3X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P3Y() {
        return F1P3Y;
    }

    /**
     *
     * @param F1P3Y
     */
    public void setF1P3Y(ArrayList<Integer> F1P3Y) {
        this.F1P3Y = F1P3Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P4X() {
        return F1P4X;
    }

    /**
     *
     * @param F1P4X
     */
    public void setF1P4X(ArrayList<Integer> F1P4X) {
        this.F1P4X = F1P4X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P4Y() {
        return F1P4Y;
    }

    /**
     *
     * @param F1P4Y
     */
    public void setF1P4Y(ArrayList<Integer> F1P4Y) {
        this.F1P4Y = F1P4Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P5X() {
        return F1P5X;
    }

    /**
     *
     * @param F1P5X
     */
    public void setF1P5X(ArrayList<Integer> F1P5X) {
        this.F1P5X = F1P5X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF1P5Y() {
        return F1P5Y;
    }

    /**
     *
     * @param F1P5Y
     */
    public void setF1P5Y(ArrayList<Integer> F1P5Y) {
        this.F1P5Y = F1P5Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P62X() {
        return F2P62X;
    }

    /**
     *
     * @param F2P62X
     */
    public void setF2P62X(ArrayList<Integer> F2P62X) {
        this.F2P62X = F2P62X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P62Y() {
        return F2P62Y;
    }

    /**
     *
     * @param F2P62Y
     */
    public void setF2P62Y(ArrayList<Integer> F2P62Y) {
        this.F2P62Y = F2P62Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P63X() {
        return F2P63X;
    }

    /**
     *
     * @param F2P63X
     */
    public void setF2P63X(ArrayList<Integer> F2P63X) {
        this.F2P63X = F2P63X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P63Y() {
        return F2P63Y;
    }

    /**
     *
     * @param F2P63Y
     */
    public void setF2P63Y(ArrayList<Integer> F2P63Y) {
        this.F2P63Y = F2P63Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P75X() {
        return F2P75X;
    }

    /**
     *
     * @param F2P75X
     */
    public void setF2P75X(ArrayList<Integer> F2P75X) {
        this.F2P75X = F2P75X;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P75Y() {
        return F2P75Y;
    }

    /**
     *
     * @param F2P75Y
     */
    public void setF2P75Y(ArrayList<Integer> F2P75Y) {
        this.F2P75Y = F2P75Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P73Y() {
        return F2P73Y;
    }

    /**
     *
     * @param F2P73Y
     */
    public void setF2P73Y(ArrayList<Integer> F2P73Y) {
        this.F2P73Y = F2P73Y;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getF2P73X() {
        return F2P73X;
    }

    /**
     *
     * @param F2P73X
     */
    public void setF2P73X(ArrayList<Integer> F2P73X) {
        this.F2P73X = F2P73X;
    }

    /**
     *
     * @return
     */
    public Tower getTower() {
        return tower;
    }

    /**
     *
     * @param tower
     */
    public void setTower(Tower tower) {
        this.tower = tower;
    }

    /**
     *
     * @return
     */
    public Sobreviviente_Healer getHada() {
        return hada;
    }

    /**
     *
     * @param hada
     */
    public void setHada(Sobreviviente_Healer hada) {
        this.hada = hada;
    }

    
    
    /**
     *
     * @return
     */
    public Floyd getFloyd() {
        return floyd;
    }

    /**
     *
     * @param floyd
     */
    public void setFloyd(Floyd floyd) {
        this.floyd = floyd;
    }

    /**
     *
     * @return
     */
    public ArrayList<Dinero> getDinero() {
        return dinero;
    }

    /**
     *
     * @param dinero
     */
    public void setDinero(ArrayList<Dinero> dinero) {
        this.dinero = dinero;
    }

    /**
     *
     * @return
     */
    public ArmaExtra getArmaExtra() {
        return armaExtra;
    }

    /**
     *
     * @param armaExtra
     */
    public void setArmaExtra(ArmaExtra armaExtra) {
        this.armaExtra = armaExtra;
    }

    /**
     *
     * @return
     */
    public ArrayList<Line2D.Double> getParedesTorreta() {
        return paredesTorreta;
    }

    /**
     *
     * @param paredesTorreta
     */
    public void setParedesTorreta(ArrayList<Line2D.Double> paredesTorreta) {
        this.paredesTorreta = paredesTorreta;
    }    

    /**
     *
     * @return
     */
    public SobrevivienteRocky getRocky() {
        return rocky;
    }

    /**
     *
     * @param rocky
     */
    public void setRocky(SobrevivienteRocky rocky) {
        this.rocky = rocky;
    }

    /**
     *
     * @return
     */
    public BufferStrategy getBs() {
        return bs;
    }

    /**
     *
     * @param bs
     */
    public void setBs(BufferStrategy bs) {
        this.bs = bs;
    }

    /**
     *
     * @return
     */
    public Graphics getG() {
        return g;
    }

    /**
     *
     * @param g
     */
    public void setG(Graphics g) {
        this.g = g;
    }

    /**
     *
     * @return
     */
    public Display getDisplay() {
        return display;
    }

    /**
     *
     * @param display
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Thread getThread() {
        return thread;
    }

    /**
     *
     * @param thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     *
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    /**
     *
     * @param running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     *
     * @return
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     *
     * @param finish
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     *
     * @return
     */
    public Tienda_vidas getTienda() {
        return tienda;
    }

    /**
     *
     * @param tienda
     */
    public void setTienda(Tienda_vidas tienda) {
        this.tienda = tienda;
    }

    /**
     *
     * @return
     */
    public Tienda_weapon getTienda2() {
        return tienda2;
    }

    /**
     *
     * @param tienda2
     */
    public void setTienda2(Tienda_weapon tienda2) {
        this.tienda2 = tienda2;
    }

    /**
     *
     * @return
     */
    public Tienda_armas getTienda3() {
        return tienda3;
    }

    /**
     *
     * @param tienda3
     */
    public void setTienda3(Tienda_armas tienda3) {
        this.tienda3 = tienda3;
    }

    /**
     *
     * @return
     */
    public Tienda_survivor getTienda4() {
        return tienda4;
    }

    /**
     *
     * @param tienda4
     */
    public void setTienda4(Tienda_survivor tienda4) {
        this.tienda4 = tienda4;
    }

    /**
     *
     * @return
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     *
     * @param menu
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     *
     * @return
     */
    public ArrayList<Explosion> getExplosiones_zombies() {
        return explosiones_zombies;
    }

    /**
     *
     * @param explosiones_zombies
     */
    public void setExplosiones_zombies(ArrayList<Explosion> explosiones_zombies) {
        this.explosiones_zombies = explosiones_zombies;
    }

    /**
     *
     * @return
     */
    public Torreta_Granadas getTorretagra() {
        return torretagra;
    }

    /**
     *
     * @param torretagra
     */
    public void setTorretagra(Torreta_Granadas torretagra) {
        this.torretagra = torretagra;
    }

    /**
     *
     * @return
     */
    public Torreta getTorreta() {
        return torreta;
    }

    /**
     *
     * @param torreta
     */
    public void setTorreta(Torreta torreta) {
        this.torreta = torreta;
    }

    /**
     *
     * @return
     */
    public boolean isPause() {
        return pause;
    }

    /**
     *
     * @param pause
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     *
     * @return
     */
    public SoundClipTest getSound() {
        return sound;
    }

    /**
     *
     * @param sound
     */
    public void setSound(SoundClipTest sound) {
        this.sound = sound;
    }

    /**
     *
     * @return
     */
    public boolean isSwatActivo() {
        return swatActivo;
    }

    /**
     *
     * @param swatActivo
     */
    public void setSwatActivo(boolean swatActivo) {
        this.swatActivo = swatActivo;
    }

    /**
     *
     * @return
     */
    public boolean isNinjaActivo() {
        return ninjaActivo;
    }

    /**
     *
     * @param ninjaActivo
     */
    public void setNinjaActivo(boolean ninjaActivo) {
        this.ninjaActivo = ninjaActivo;
    }

    /**
     *
     * @return
     */
    public boolean isHadaActivo() {
        return hadaActivo;
    }

    /**
     *
     * @param hadaActivo
     */
    public void setHadaActivo(boolean hadaActivo) {
        this.hadaActivo = hadaActivo;
    }

    /**
     *
     * @return
     */
    public boolean isDroneActivo() {
        return droneActivo;
    }

    /**
     *
     * @param droneActivo
     */
    public void setDroneActivo(boolean droneActivo) {
        this.droneActivo = droneActivo;
    }

    /**
     *
     * @return
     */
    public boolean isMisilActivado() {
        return misilActivado;
    }

    /**
     *
     * @param misilActivado
     */
    public void setMisilActivado(boolean misilActivado) {
        this.misilActivado = misilActivado;
    }

    /**
     *
     * @return
     */
    public static STATE getGameState() {
        return gameState;
    }

    /**
     *
     * @param gameState
     */
    public static void setGameState(STATE gameState) {
        Game.gameState = gameState;

    }

    /**
     *
     * @return
     */
    public int getCantBalasSeguidas() {
        return cantBalasSeguidas;
    }

    /**
     *
     * @param cantBalasSeguidas
     */
    public void setCantBalasSeguidas(int cantBalasSeguidas) {
        this.cantBalasSeguidas = cantBalasSeguidas;
    }

    /**
     *
     * @return
     */
    public ArrayList<Explosion> getExplosiones() {
        return explosiones;
    }

    /**
     *
     * @param explosiones
     */
    public void setExplosiones(ArrayList<Explosion> explosiones) {
        this.explosiones = explosiones;
    }

    /**
     *
     * @return
     */
    public int getContTicksBala() {
        return contTicksBala;
    }

    /**
     *
     * @param contTicksBala
     */
    public void setContTicksBala(int contTicksBala) {
        this.contTicksBala = contTicksBala;
    }

    /**
     *
     * @return
     */
    public int getContBalas() {
        return contBalas;
    }

    /**
     *
     * @param contBalas
     */
    public void setContBalas(int contBalas) {
        this.contBalas = contBalas;
    }

    /**
     *
     * @return
     */
    public double getVidaPlayer() {
        return vidaPlayer;
    }

    /**
     *
     * @param vidaPlayer
     */
    public void setVidaPlayer(double vidaPlayer) {
        this.vidaPlayer = vidaPlayer;
    }

    /**
     *
     * @return
     */
    public double getVidaTorre() {
        return vidaTorre;
    }

    /**
     *
     * @param vidaTorre
     */
    public void setVidaTorre(double vidaTorre) {
        this.vidaTorre = vidaTorre;
    }

    /**
     *
     * @return
     */
    public Mina getMina() {
        return mina;
    }

    /**
     *
     * @param mina
     */
    public void setMina(Mina mina) {
        this.mina = mina;
    }

    /**
     *
     * @return
     */
    public Drone getDrone() {
        return drone;
    }

    /**
     *
     * @param drone
     */
    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    /**
     *
     * @return
     */
    public Misil_Launcher getLauncher() {
        return launcher;
    }

    /**
     *
     * @param launcher
     */
    public void setLauncher(Misil_Launcher launcher) {
        this.launcher = launcher;
    }
    
    
    
    
    private void drawPause(Graphics g){
       // Show LOST!!
        g.drawImage(Assets.pause, (this.width / 2) - 200, (this.height / 2) - 200, 400 , 400, null);
    }
    
    private void searchForControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for(int i = 0; i < controllers.length; i++){
            Controller controller = controllers[i];
            if (controller.getType() == Controller.Type.STICK || controller.getType() == Controller.Type.GAMEPAD || controller.getType() == Controller.Type.WHEEL ||controller.getType() == Controller.Type.FINGERSTICK){
                // Add new controller to the list of all controllers.
                foundControllers.add(controller);
                //System.out.println(controller.getName());
            }
        }
    }
    
    private void cambiarPisoPlayer(){
        Iterator itr = escaleras.iterator();
        while(itr.hasNext()){
            Escalera esca = (Escalera) itr.next();
            esca.tick();
            if(esca.intersects(player)){
                player.setPiso(esca.getPiso());
            }
        }
    }
    
    private void cambiarPisoZombie(Zombie zom){
        Iterator itr = escaleras.iterator();
        while(itr.hasNext()){
            Escalera esca = (Escalera) itr.next();
            esca.tick();
            if(esca.intersects(zom)){
                zom.setPiso(esca.getPiso());
            }
        }
    }

    /**
     *
     */
    public void inicializarParedes(){
        
        //piso3
        paredes.add(new Line2D.Double(getWidth()*.245,getHeight()*.168,getWidth()*.245,getHeight()*.232));
        paredes.add(new Line2D.Double(getWidth()*.245,getHeight()*.232,getWidth()*.325,getHeight()*.327));
        paredes.add(new Line2D.Double(getWidth()*.325,getHeight()*.327,getWidth()*.454,getHeight()*.327));
        //cambie este
        paredes.add(new Line2D.Double(getWidth()*.454,getHeight()*.327,getWidth()*.454,getHeight()*.350));
        paredes.add(new Line2D.Double(getWidth()*.525,getHeight()*.37,getWidth()*.525,getHeight()*.325));
        paredes.add(new Line2D.Double(getWidth()*.525,getHeight()*.325,getWidth()*.579,getHeight()*.325));
        paredes.add(new Line2D.Double(getWidth()*.579,getHeight()*.325,getWidth()*.579,getHeight()*.49));
        paredes.add(new Line2D.Double(getWidth()*.579,getHeight()*.49,getWidth()*.526,getHeight()*.49));
        paredes.add(new Line2D.Double(getWidth()*.526,getHeight()*.49,getWidth()*.526,getHeight()*.45));
        //cambie este 
        paredes.add(new Line2D.Double(getWidth()*.454,getHeight()*.493,getWidth()*.454,getHeight()*.470));
        paredes.add(new Line2D.Double(getWidth()*.454,getHeight()*.493,getWidth()*.324,getHeight()*.493));
        paredes.add(new Line2D.Double(getWidth()*.324,getHeight()*.493,getWidth()*.246,getHeight()*.586));
        paredes.add(new Line2D.Double(getWidth()*.246,getHeight()*.586,getWidth()*.246,getHeight()*.653));
        paredes.add(new Line2D.Double(getWidth()*.246,getHeight()*.653,getWidth()*.198,getHeight()*.653));
        
        paredes.add(new Line2D.Double(getWidth()*.0775,getHeight()*.194,getWidth()*.0775,getHeight()*.168));
        paredes.add(new Line2D.Double(getWidth()*.0775,getHeight()*.168,getWidth()*.1275,getHeight()*.168));
        paredes.add(new Line2D.Double(getWidth()*.0775,getHeight()*.168,getWidth()*.0775,getHeight()*.233));
        paredes.add(new Line2D.Double(getWidth()*.0775,getHeight()*.233,getWidth()*0,getHeight()*.327));
        //cambie estas
        paredes.add(new Line2D.Double(getWidth()*.1275,getHeight()*.168,getWidth()*.1275,getHeight()*.220));
        paredes.add(new Line2D.Double(getWidth()*.195,getHeight()*.168,getWidth()*.195,getHeight()*.220));
        paredes.add(new Line2D.Double(getWidth()*.195,getHeight()*.168,getWidth()*.245,getHeight()*.168));
        paredes.add(new Line2D.Double(getWidth()*.125,getHeight()*.653,getWidth()*.078,getHeight()*.653));
        paredes.add(new Line2D.Double(getWidth()*.078,getHeight()*.653,getWidth()*.078,getHeight()*.587));
        paredes.add(new Line2D.Double(getWidth()*.078,getHeight()*.587,getWidth()*0,getHeight()*.493));
        
        
        //extremos
        paredes.add(new Line2D.Double(0,0,0,getHeight()*.905));
        paredes.add(new Line2D.Double(0,getHeight()*.905,getWidth(),getHeight()*.905));
        paredes.add(new Line2D.Double(getWidth()*.9999,getHeight()*.905,getWidth()*.9999,0));
       paredes.add(new Line2D.Double(getWidth()*.9999,0,0,0));
        
        
        
        //piso1
       paredes.add(new Line2D.Double(0,getHeight()*.137,getWidth()*.03,getHeight()*.133));
       paredes.add(new Line2D.Double(getWidth()*.03,getHeight()*.133,getWidth()*.0775,getHeight()*.194));
       paredes.add(new Line2D.Double(getWidth()*.087,getHeight()*.653,getWidth()*.087,getHeight()*.684));
       paredes.add(new Line2D.Double(getWidth()*.087,getHeight()*.684,getWidth()*.03,getHeight()*.753));
       paredes.add(new Line2D.Double(getWidth()*.03,getHeight()*.753,getWidth()*0,getHeight()*.753)); 
       paredes.add(new Line2D.Double(getWidth()*.238,getHeight()*.653,getWidth()*.238,getHeight()*.68));
       paredes.add(new Line2D.Double(getWidth()*.238,getHeight()*.68,getWidth()*.293,getHeight()*.75));
       paredes.add(new Line2D.Double(getWidth()*.293,getHeight()*.75,getWidth()*.34,getHeight()*.75));
       paredes.add(new Line2D.Double(getWidth()*.336,getHeight()*.775,getWidth()*.336,getHeight()*.75));
       paredes.add(new Line2D.Double(getWidth()*.418,getHeight()*.75,getWidth()*.656,getHeight()*.75));
       paredes.add(new Line2D.Double(getWidth()*.656,getHeight()*.75,getWidth()*.735,getHeight()*.657));
       paredes.add(new Line2D.Double(getWidth()*.735,getHeight()*.657,getWidth()*.733,getHeight()*.5));
       paredes.add(new Line2D.Double(getWidth()*.737,getHeight()*.367,getWidth()*.737,getHeight()*.232));
       paredes.add(new Line2D.Double(getWidth()*.737,getHeight()*.232,getWidth()*.65,getHeight()*.136));
       paredes.add(new Line2D.Double(getWidth()*.65,getHeight()*.136,getWidth()*.415,getHeight()*.136));
       paredes.add(new Line2D.Double(getWidth()*.341,getHeight()*.136,getWidth()*.296,getHeight()*.136));
       paredes.add(new Line2D.Double(getWidth()*.296,getHeight()*.136,getWidth()*.245,getHeight()*.199));
        
        
        
        
        //escaleras esquineadas
      // paredes.add(new Line2D.Double(getWidth()*.532,getHeight()*.49,getWidth()*.532,getHeight()*.52));
       //paredes.add(new Line2D.Double(getWidth()*.454,getHeight()*.493,getWidth()*.44,getHeight()*.521));

       paredes.add(new Line2D.Double(getWidth()*.198,getHeight()*.680,getWidth()*.195,getHeight()*.60));
       paredes.add(new Line2D.Double(getWidth()*.125,getHeight()*.680,getWidth()*.125,getHeight()*.60));
 
       //caambie estas, escaleras segundo pispo parte abajo (lo de arriba)
       paredes.add(new Line2D.Double(getWidth()*.34,getHeight()*.75,getWidth()*.34,getHeight()*.730)); 
       paredes.add(new Line2D.Double(getWidth()*.418,getHeight()*.775,getWidth()*.418,getHeight()*.730));
        
        //estas son 
        paredes.add(new Line2D.Double(getWidth()*.733,getHeight()*.5,getWidth()*.705,getHeight()*.5));
        paredes.add(new Line2D.Double(getWidth()*.737,getHeight()*.367,getWidth()*.705,getHeight()*.367));
        
        
        //cambie estas(Escaleras arriba)
        paredes.add(new Line2D.Double(getWidth()*.415,getHeight()*.136,getWidth()*.415,getHeight()*.160));

        paredes.add(new Line2D.Double(getWidth()*.341,getHeight()*.136,getWidth()*.341,getHeight()*.160));
      
    }
     
    /**
     *
     */
    public void inicializarEscaleras(){
        //escalera 1
        escaleras.add( new Escalera((int)(getWidth() *.123),(int)((int)getHeight()*.26),(int)(getWidth() *.081),(int)((int)getHeight()*.005),3,1,this));
        escaleras.add( new Escalera((int)(getWidth() *.123),(int)((int)getHeight()*.195),(int)(getWidth() *.075),(int)((int)getHeight()*.005),1,1,this));
        //escalera 2
        escaleras.add( new Escalera((int)(getWidth() *.341),(int)((int)getHeight()*.711),(int)(getWidth() *.076),(int)((int)getHeight()*.005),2,2,this));
        escaleras.add( new Escalera((int)(getWidth() *.123),(int)((int)getHeight()*.713),(int)(getWidth() *.076),(int)((int)getHeight()*.005),1,2,this));
        //escalera 3
        escaleras.add( new Escalera((int)(getWidth() *.341),(int)((int)getHeight()*.179),(int)(getWidth() *.076),(int)((int)getHeight()*.005),2,3,this));
        escaleras.add( new Escalera((int)(getWidth() *.341),(int)((int)getHeight()*.149),(int)(getWidth() *.076),(int)((int)getHeight()*.005),1,3,this));
        //escalera 4
        escaleras.add( new Escalera((int)(getWidth() *.123),(int)((int)getHeight()*.56),(int)(getWidth() *.076),(int)((int)getHeight()*.005),3,4,this));
        escaleras.add( new Escalera((int)(getWidth() *.336),(int)((int)getHeight()*.774),(int)(getWidth() *.082),(int)((int)getHeight()*.005),1,4,this));
        //escalera 5
        escaleras.add( new Escalera((int)(getWidth() *.676),(int)((int)getHeight()*.368),(int)(getWidth() *.003),(int)((int)getHeight()*.13),2,5,this));
        escaleras.add( new Escalera((int)(getWidth() *.736),(int)((int)getHeight()*.368),(int)(getWidth() *.003),(int)((int)getHeight()*.13),1,5,this));
        //escalera 6
        escaleras.add( new Escalera((int)(getWidth() *.455),(int)((int)getHeight()*.338),(int)(getWidth() *.07),(int)((int)getHeight()*.005),2,6,this));
        escaleras.add( new Escalera((int)(getWidth() *.451),(int)((int)getHeight()*.373),(int)(getWidth() *.078),(int)((int)getHeight()*.005),3,6,this));
        //escalera 7
        escaleras.add( new Escalera((int)(getWidth() *.455),(int)((int)getHeight()*.444),(int)(getWidth() *.07),(int)((int)getHeight()*.005),3,7,this));
        escaleras.add( new Escalera((int)(getWidth() *.443),(int)((int)getHeight()*.517),(int)(getWidth() *.09),(int)((int)getHeight()*.005),2,7,this)); 

    }
    
    /**
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return
     */
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        
        return Math.sqrt(deltax2 + deltay2);
    }
    
    /**
     *
     * @param g
     */
    public void formatoAbajo(Graphics g){
        
        Font leter = new Font ("Arial", 1, 30);
                g.setFont (leter);
                g.setColor(Color.BLACK);
                //dinero player
                g.drawString ("" + player.getDinero() + "$" , (int) (getWidth()*.01) , (int) (getHeight()*.97));
        
                //numero de balas totales
                leter = new Font ("Arial", 1, 12);
                g.setFont (leter);
                g.setColor(Color.BLACK);
                
                //balas totales player
                g.drawString ("" + player.getBalasTotal() , (int) (getWidth()*.16) , (int) (getHeight()*.935));
                
                //cargamiento balas
                if(player.isTiempoCargaBalas()){
                g.drawString ("" + player.getSegundosCarga() + "s" , (int) (getWidth()*.165) , (int) (getHeight()*.96));
                }
                
                //contar balas en pantalla
                //falta hcer modificaciones, esto es solo para la entrega
                
                double xbull = (int) (getWidth() * .196);
                double ybull = (int) (getHeight() * .91);
                for(int x = 0;x < player.getCantBalas() ; x++){
                    xbull = xbull + (int) (getWidth() * .02);
                    if( x % 10 == 0){
                        xbull = (int) (getWidth() * .196);
                        ybull = ybull + (int) (getHeight()*.018);
                    }

                    g.setColor(Color.BLACK);
                    g.fillRect((int) (xbull),(int) (ybull),(int) (getWidth()*.01),(int) (getHeight()*.005));
                }

                //vida tower
                g.setColor(Color.green);
                vidaTorre=(double)(tower.getVida()/tower.getVidaEntera());
                g.fillRect((int) (getWidth()*.83),(int) (getHeight()*.93),(int) ((getWidth()*.15)*(vidaTorre)),(int) (getHeight()*.02));
                g.drawImage(Assets.marcoNegro, (int) (getWidth()*.83),(int) (getHeight()*.927),(int) ((getWidth()*.15)),(int) (getHeight()*.023),null);
                g.drawString ("" + tower.getVida(), (int) (getWidth()*.78) , (int) (getHeight()*.944));

                //vida del player
                g.setColor(Color.red);
                vidaPlayer=(double)(player.getVida()/player.getVidaEntera());
                g.fillRect((int) (getWidth()*.83),(int) (getHeight()*.96),(int) ((getWidth()*.15)*(vidaPlayer)),(int) (getHeight()*.02));
                g.drawImage(Assets.marcoNegro, (int) (getWidth()*.83),(int) (getHeight()*.957),(int) ((getWidth()*.15)),(int) (getHeight()*.023),null);
                g.drawString ("" + player.getVida(), (int) (getWidth()*.78) , (int) (getHeight()*.974));
                
                
                leter = new Font ("Arial", 1, 27);
                g.setFont (leter);
                g.setColor(Color.BLACK);
                
                //dibujar granada hielo
                g.drawImage(Assets.granadaHielo, (int) (getWidth()*.67), (int) (getHeight()*.92), (int) (getWidth()*.03),(int) (getHeight()*.05),null);
                g.drawString ("" + player.getCantGranadasHielo(), (int) (getWidth()*.705) , (int) (getHeight()*.965));
                
                //dibujar granada fuego
                g.drawImage(Assets.granadaFuego, (int) (getWidth()*.59), (int) (getHeight()*.92), (int) (getWidth()*.03),(int) (getHeight()*.05),null);
                g.drawString ("" + player.getCantGranadasFuego(), (int) (getWidth()*.625) , (int) (getHeight()*.965));
                
                //dibujar paquetes vida chida
                g.drawImage(Assets.vidaChida, (int) (getWidth()*.51), (int) (getHeight()*.92), (int) (getWidth()*.03),(int) (getHeight()*.05),null);
                g.drawString ("" + player.getCantVidaChida(), (int) (getWidth()*.545) , (int) (getHeight()*.965));
                
                //dibujar paquetes vida chafa
                g.drawImage(Assets.vidaChafa, (int) (getWidth()*.43), (int) (getHeight()*.92), (int) (getWidth()*.03),(int) (getHeight()*.05),null);
                g.drawString ("" + player.getCantVidaChafa(), (int) (getWidth()*.465) , (int) (getHeight()*.965));
    }
    
    /**
     *
     */
    public void inicializarParedesTorreta(){
        //piso2
        paredesTorreta.add(new Line2D.Double(getWidth()*.268,getHeight()*.215,getWidth()*.332,getHeight()*.295));
        paredesTorreta.add(new Line2D.Double(getWidth()*.332,getHeight()*.295,getWidth()*.6,getHeight()*.295));
        paredesTorreta.add(new Line2D.Double(getWidth()*.6,getHeight()*.295,getWidth()*.6,getHeight()*.521));
        paredesTorreta.add(new Line2D.Double(getWidth()*.6,getHeight()*.521,getWidth()*.337,getHeight()*.521));
        paredesTorreta.add(new Line2D.Double(getWidth()*.337,getHeight()*.521,getWidth()*.269,getHeight()*.6));
        paredesTorreta.add(new Line2D.Double(getWidth()*.269,getHeight()*.6,getWidth()*.269,getHeight()*.67));
        paredesTorreta.add(new Line2D.Double(getWidth()*.269,getHeight()*.67,getWidth()*.3,getHeight()*.72));
        paredesTorreta.add(new Line2D.Double(getWidth()*.3,getHeight()*.72,getWidth()*.64,getHeight()*.72));
        paredesTorreta.add(new Line2D.Double(getWidth()*.64,getHeight()*.72,getWidth()*.71,getHeight()*.65));
        paredesTorreta.add(new Line2D.Double(getWidth()*.71,getHeight()*.25,getWidth()*.64,getHeight()*.17));
        paredesTorreta.add(new Line2D.Double(getWidth()*.64,getHeight()*.17,getWidth()*.30,getHeight()*.17));
        paredesTorreta.add(new Line2D.Double(getWidth()*.30,getHeight()*.17,getWidth()*.268,getHeight()*.215));
        paredesTorreta.add(new Line2D.Double(getWidth()*.71,getHeight()*.65,getWidth()*.71,getHeight()*.25));
        
        //piso1
        
        paredesTorreta.add(new Line2D.Double(getWidth()*.095,getHeight()*.68,getWidth()*.23,getHeight()*.68));
        paredesTorreta.add(new Line2D.Double(getWidth()*.065,getHeight()*.155,getWidth()*.26,getHeight()*.155));
        paredesTorreta.add(new Line2D.Double(getWidth()*.325,getHeight()*.13,getWidth()*.42,getHeight()*.13));
        paredesTorreta.add(new Line2D.Double(getWidth()*.325,getHeight()*.79,getWidth()*.426,getHeight()*.79));
        paredesTorreta.add(new Line2D.Double(getWidth()*.74,getHeight()*.35,getWidth()*.74,getHeight()*.5));
        
        //piso3
        paredesTorreta.add(new Line2D.Double(getWidth()*.095,getHeight()*.62,getWidth()*.23,getHeight()*.62));
        paredesTorreta.add(new Line2D.Double(getWidth()*.09,getHeight()*.21,getWidth()*.21,getHeight()*.21));
        paredesTorreta.add(new Line2D.Double(getWidth()*.42,getHeight()*.35,getWidth()*.55,getHeight()*.35));
        paredesTorreta.add(new Line2D.Double(getWidth()*.42,getHeight()*.45,getWidth()*.55,getHeight()*.45));
    }
    
    /**
     *
     */
    public void tickearGranadasPlayer(){
        // tickear arreglo de granadas del player
        Iterator itrG = player.getGranadas().iterator();
        while(itrG.hasNext()){
            Granada gran=(Granada) itrG.next();
            gran.tick();              
        }
        //ver si las granadas del player ya llegaron a su destino
        itrG = player.getGranadas().iterator();
        while(itrG.hasNext()){
            Granada gran=(Granada) itrG.next();
            //System.out.println(gran.getTipo() );
            if( gran.getTipo() == 0){
                if( (gran.getX() + (gran.getWidth() / 2) - 5) < gran.getxDestino() && (gran.getX() + (gran.getWidth() / 2) + 5) > gran.getxDestino() && (gran.getY() + (gran.getHeight() / 2) - 5) < gran.getyDestino() && (gran.getY() + (gran.getHeight() / 2) + 5) > gran.getyDestino()  ){
                    Explosion exp = new Explosion(0,gran.getX() + (gran.getWidth() / 2),gran.getY() + (gran.getHeight() / 2 ),1,1,50,4,this);
                    explosiones.add(exp);
                    sound = new SoundClipTest("sonido_boom_bomba"); // SONIDO DE BOOOM!!!
                    int xExp = exp.getX();
                    int yExp = exp.getY();
                    double radioExp = exp.getRadio();
                    int pisoExp = exp.getPiso();

                    //colision de explosion con otro zombie
                    Iterator itr3 = zombies.iterator();
                    while(itr3.hasNext()){
                        Zombie zomb = (Zombie) itr3.next();
                        if(DistanciaDosPuntos( zomb.getX() + (zomb.getWidth() / 2), xExp, zomb.getY() + (zomb.getHeight() / 2) , yExp ) <= radioExp ){
                            zomb.setVidaZ(zomb.getVidaZ()-100);
                        }                                  
                    }
                    player.getGranadas().remove(gran);
                    itrG = player.getGranadas().iterator();
                }      
            }
            else{
                if( (gran.getX() + (gran.getWidth() / 2) - 5) < gran.getxDestino() && (gran.getX() + (gran.getWidth() / 2) + 5) > gran.getxDestino() && (gran.getY() + (gran.getHeight() / 2) - 5) < gran.getyDestino() && (gran.getY() + (gran.getHeight() / 2) + 5) > gran.getyDestino()  ){
                    Explosion exp = new Explosion(1,gran.getX() + (gran.getWidth() / 2),gran.getY() + (gran.getHeight() / 2 ),1,1,50,4,this);
                    explosiones.add(exp);
                    sound = new SoundClipTest("sonido_boom_bomba"); // SONIDO DE BOOOM!!!
                    int xExp = exp.getX();
                    int yExp = exp.getY();
                    double radioExp = exp.getRadio();

                    //colision de explosion con otro zombie
                    Iterator itr3 = zombies.iterator();
                    while(itr3.hasNext()){
                        Zombie zomb = (Zombie) itr3.next();
                        if(DistanciaDosPuntos( zomb.getX() + (zomb.getWidth() / 2), xExp, zomb.getY() + (zomb.getHeight() / 2) , yExp ) <= radioExp ){
                            zomb.setCongelado(true);
                            zomb.setTicksCongelado(mina.getDano());
                        }                                  
                    }
                    player.getGranadas().remove(gran);
                    itrG = player.getGranadas().iterator();
                }
            }
                     
        }
    }
    
    /**
     *
     */
    public void initZombiesRonda(){ 
        zombAux = new ArrayList<>(); 
        yanisequenombre = true;
        int temp = 0; 
        int temp2 = 0; 
        int tempnormal = 0; 
        int tempflash = 0; 
        int tempmama = 0; 
        int tempkami = 0; 
        while(temp < numZomTotal){
            
            temp2 = (int) (Math.random() * 4); 
            //System.out.println(temp  + "    " + temp2 + "     " + numZomTotal+ "  simonnnnnnnnnnnnnnnnnnnn");
            if(temp2 == 0 && tempnormal < numZomNorm){ 
            zombAux.add(new Zombie(1,(int) (Math.random() * 100)+getWidth(),(int) (Math.random() * (getHeight() - 150) )  ,(int) (getWidth()*.025),(int)(getHeight()*.06),1,vidaZombie,vidaZombie,false,this ) ); 
            tempnormal++; 
            temp++; 
            } 
            else if(temp2 == 1 && tempflash < numZomFlas){ 
                zombAux.add(new Zombie(2,(int) (Math.random() * 100)+getWidth(),(int) (Math.random() * (getHeight() - 150) ) ,(int) (getWidth()*.025),(int)(getHeight()*.06),1,vidaZombie,vidaZombie,false,this ) ); 
                tempflash++; 
                temp++; 
            } 
            else if(temp2 == 2 && tempmama < numZomMama){ 
                zombAux.add(new Zombie(3,(int) (Math.random() * 100)+getWidth(),(int) (Math.random() * (getHeight() - 150) ) ,(int) (getWidth()*.025),(int)(getHeight()*.06),1,vidaZombie,vidaZombie,false,this ) ); 
               tempmama++; 
               temp++; 
            } 
            else if( temp2 == 3 && tempkami < numZomKami){ 
                zombAux.add(new Zombie(4,(int) (Math.random() * 100)+getWidth(),(int) (Math.random() * (getHeight() - 150) ) ,(int) (getWidth()*.025),(int)(getHeight()*.06),1,vidaZombie,vidaZombie,false,this ) ); 
                tempkami++; 
                temp++; 
            } 
        }         
    } 
}
