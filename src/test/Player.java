package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Player extends Item {
    private Game game;
    private int asset = 0;
    private int piso = 0;
    private int xMentis;
    private int yMentis;
    private boolean boolIntersect = false;
    private double vida;
    private double vidaEntera=100;
    private int cantBalas = 10;
    private boolean valBalas = true;
    private int closestNode = 10;
    private int balasTotal = 50;
    private int cartucho = 10;
    private boolean dispararControl = true;
    private boolean zombieIntersect = false;
    private int contTicks=0;
    private boolean tiempoCargaBalas=false;
    private int cargaTicks=0;
    private int segundosCarga=5;
    private int dinero;
    private int dano = 200;
    private int maxBalas=50;

    private int cantGranadasHielo = 0;
    private int cantGranadasFuego = 0;
    private int cantVidaChafa = 0;
    private int cantVidaChida = 0;
    private int cantMaximasGranadas=1;  
    private int cantMaximasVidasChafas=1;
    private int cantMaximasVidasChidas=1;
    private int contDrone=0;
    private int contNinja=0;
    private int contHada=0;
    private int contSwat=0;
    private int maxVidaChida = 1;
    private int maxVidaChafa = 1;
    private int segundosEstablesCarga=5;
    private boolean validarRecuperar=false;
    private int ticksRecuperarVida=0;
    private boolean accionPlayer=false;
    private int contSegundosRecuperar=4;
    
    private DestinoGranada destinoGranada;
    private ArrayList<Granada> granadas; 
    private boolean desplegarDestinoGranada=false;
    private int contDesplegarGranada=0;
    private int contDesplegarGranadaHielo=0;
    private boolean activarGranada=false;
    private boolean activarGranadaHielo=false;
    private boolean desplegarGranada=false;
    private boolean nuevaGranada=false;
    private boolean nuevaGranadaHielo=false;
    private int recuperaMuchaVida=80;
    private int contTicksRecuperarVida=0;
    

    private Animation animation45;
    private Animation animationRight;
    private Animation animation135;
    private Animation animationDown;
    private Animation animation225;
    private Animation animationLeft;
    private Animation animation315;
    private Animation animationUp;
    
    private SoundClipTest sound;
    
    private double grados;
    
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param piso
     * @param vida
     * @param cantBalas
     * @param game
     */
    public Player(int x, int y, int width, int height,int piso, double vida,int cantBalas, int cartuchos, int dinero, Game game){
        super(x,y,width,height);
        this.game=game;
        this.piso = piso;
        this.vida = vida;
        this.cantBalas = cantBalas;
        this.cartucho=cartuchos;
        init();
  
        animation45 = new Animation(Assets.player45, 100);
        animationRight = new Animation(Assets.playerRight, 100);
        animation135 = new Animation(Assets.player135, 100);
        animationDown = new Animation(Assets.playerDown, 100);
        animation225 = new Animation(Assets.player225, 100);
        animationLeft = new Animation(Assets.playerLeft, 100);
        animation315 = new Animation(Assets.player315, 100);
        animationUp = new Animation(Assets.playerUp, 100);

        this.cartucho = cartucho;
        this.dinero = dinero;
        init();
    }
    
    public void init(){
        granadas = new ArrayList<>();
    }

    
    
    @Override
    public void tick() {
        
        if(game.getKeyManager().cargar){
            cargarBalas();
            sound = new SoundClipTest("carga_sound");
        }
        if(!accionPlayer){
            validarBalas();
            cargarBalasControl();
 
            movimientoPersonaje(); 
        }        
        
        zombieIntersect();
        
        agarrarDinero();
        lanzarGranadaFuego();
        lanzarGranadaHielo();
        recuperarPocaVida();
        recuperarMuchaVida();
        mejorarVidaTorre();
    }
    
    @Override
    public void render(Graphics g){
        
        if(accionPlayer){
            Font leter = new Font ("Arial", 1, 12);
                    g.setFont (leter);
                    g.setColor(Color.BLACK);
                     g.drawString ("" + contSegundosRecuperar+"s",game.getPlayer().getX() , (int) (game.getPlayer().getY()-game.getPlayer().getHeight()*.5));
        }

        switch (asset) {
            case 1:  g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);//Right
                    break; 
            case 2:  g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 3:  g.drawImage(animation45.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); // 45
                    break;
            case 4:  g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 5:  g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);//up
                    break;
            case 6:  g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 7: g.drawImage(animation135.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); // 135
                    break;
            case 8:  g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 9: g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);//left
                    break;
            case 10: g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 11: g.drawImage(animation225.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);//225
                    break;
            case 12: g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 13: g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);//Down
                    break;
            case 14: g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            case 15: g.drawImage(animation315.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);//315
                    break;
            case 16: g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    break;
            default: g.drawImage(Assets.playerRight[0], getX(), getY(), getWidth(), getHeight(), null);
                    break;
        }
        
        NumberAssetPlayer();
        
        if(desplegarDestinoGranada){
            destinoGranada.render(g);
        }
        
        desplegarBoton(g);
       
    }
    
    /**
     *
     */
    public void NumberAssetPlayer(){
        if(game.getFoundControllers().size() > 0){
            NumberAssetPlayerControl();
        }
        else{
            NumberAssetPlayerMouse();
        }
    }
    
    /**
     *
     */
    public void NumberAssetPlayerControl(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            
            double mx = components[3].getPollData();
            double my = components[2].getPollData();

            double dx = Math.abs(mx);
            double dy = Math.abs(my);
            
            double distance = Math.sqrt(Math.pow((dx), 2) + Math.pow((dy), 2));
            grados = Math.toDegrees( Math.acos( dx / distance ) ) ;

            //System.out.println(grados);
            
            if(mx >= 0){
                //lado derecho del personaje
                if(my < 0){
                    //c1
                    if(grados >= 0 && grados <= 11.25){
                        asset = 1;
                    }
                    else if(grados > 11.25 && grados <= 33.75){
                        asset = 2;
                    }
                    else if(grados > 33.75 && grados <= 56.25){
                        asset = 3;
                    }
                    else if(grados > 56.25 && grados <= 78.75){
                        asset = 4;
                    }
                    else{
                        asset = 5;
                    }
                }
                else{
                    //c4
                    if(grados >= 0 && grados <= 11.25){
                        asset = 1;
                    }
                    else if(grados > 11.25 && grados <= 33.75){
                        asset = 16;
                    }
                    else if(grados > 33.75 && grados <= 56.25){
                        asset = 15;
                    }
                    else if(grados > 56.25 && grados <= 78.75){
                        asset = 14;
                    }
                    else{
                        asset = 13;
                    }
                }
            }
            else{
                if(my < 0){
                    //c2
                    if(grados >= 0 && grados <= 11.25){
                        asset = 9;
                    }
                    else if(grados > 11.25 && grados <= 33.75){
                        asset = 8;
                    }
                    else if(grados > 33.75 && grados <= 56.25){
                        asset = 7;
                    }
                    else if(grados > 56.25 && grados <= 78.75){
                        asset = 6;
                    }
                    else{
                        asset = 5;
                    }
                }
                else{
                    //c3
                    if(grados >= 0 && grados <= 11.25){
                        asset = 9;
                    }
                    else if(grados > 11.25 && grados <= 33.75){
                        asset = 10;
                    }
                    else if(grados > 33.75 && grados <= 56.25){
                        asset = 11;
                    }
                    else if(grados > 56.25 && grados <= 78.75){
                        asset = 12;
                    }
                    else{
                        asset = 13;
                    }
                }           
            }
        }
    }
    
    /**
     *
     */
    public void NumberAssetPlayerMouse(){
        int mx = game.getMouseManager().getX();
        int my = game.getMouseManager().getY();
        int px = game.getPlayer().getX()+ (game.getPlayer().getWidth()/2) ;
        int py = game.getPlayer().getY()+ (game.getPlayer().getHeight()/2);
        
        int dx = Math.abs(px - mx);
        int dy = Math.abs(py - my);
        
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
        
        if(mx > px){
            if(my < py){
                //c1
                if(grados >= 0 && grados <= 11.25){
                    asset = 1;
                }
                else if(grados > 11.25 && grados <= 33.75){
                    asset = 2;
                }
                else if(grados > 33.75 && grados <= 56.25){
                    asset = 3;
                }
                else if(grados > 56.25 && grados <= 78.75){
                    asset = 4;
                }
                else{
                    asset = 5;
                }
            }
            else{
                //c4
                if(grados >= 0 && grados <= 11.25){
                    asset = 1;
                }
                else if(grados > 11.25 && grados <= 33.75){
                    asset = 16;
                }
                else if(grados > 33.75 && grados <= 56.25){
                    asset = 15;
                }
                else if(grados > 56.25 && grados <= 78.75){
                    asset = 14;
                }
                else{
                    asset = 13;
                }
            }
        }
        else{
            if(my < py){
                //c2
                if(grados >= 0 && grados <= 11.25){
                    asset = 9;
                }
                else if(grados > 11.25 && grados <= 33.75){
                    asset = 8;
                }
                else if(grados > 33.75 && grados <= 56.25){
                    asset = 7;
                }
                else if(grados > 56.25 && grados <= 78.75){
                    asset = 6;
                }
                else{
                    asset = 5;
                }
            }
            else{
                //c3
                if(grados >= 0 && grados <= 11.25){
                    asset = 9;
                }
                else if(grados > 11.25 && grados <= 33.75){
                    asset = 10;
                }
                else if(grados > 33.75 && grados <= 56.25){
                    asset = 11;
                }
                else if(grados > 56.25 && grados <= 78.75){
                    asset = 12;
                }
                else{
                    asset = 13;
                }
            }           
        }
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
     * @return
     */
    public int ClosestNode(){
        int temp = closestNode;
        Iterator itr = game.getFloyd().getNodos().iterator();
        while(itr.hasNext()){
            Node nod = (Node) itr.next();
            //System.out.println(nod.getPiso());
            if(nod.getPiso() == piso /* && nod.getId() != 25*/){
                if( DistanciaDosPuntos(this.getX() + (this.getWidth() / 2) , nod.getX(), this.getY()  + (this.getHeight() / 2),nod.getY()) < DistanciaDosPuntos(this.getX() + (this.getWidth() / 2),game.getFloyd().getNodos().get(temp).getX(), this.getY()  + (this.getHeight() / 2),game.getFloyd().getNodos().get(temp).getY())){
                    temp = nod.getId();
                }
            }
        }
        return temp;
    }
    
    
    /**
     *
     * @return
     */
    public boolean noPasarParedes(){
        Iterator itr = game.getParedes().iterator();
        while(itr.hasNext()){
            Line2D.Double line = (Line2D.Double) itr.next();
            if(line.intersects(this.x,this.y,this.getWidth(),this.getHeight())){
                //System.out.println("Me esta tocamndoooo");
                return true;
            }
        }
        return false;
    }
    
    public void validarBalas(){
        if(game.getKeyManager().space){
            this.setCantBalas(this.getCantBalas()-1);
        }
        
        //System.out.println(this.getCantBalas());
        
        if (this.getCantBalas()<=0){
            valBalas=false;
        }
        
        if (this.getCantBalas()>0){
            valBalas=true;
        }
    }
    
    public void movimientoPersonaje(){
        if(game.getFoundControllers().size() > 0){
            movimientoPersonajeControl();
        }
        else{
            movimientoPersonajeTeclado();
        }
    }
    
    public void movimientoPersonajeControl(){
        boolIntersect = noPasarParedes();
        
        //mover personaje con control
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component componentX = components[1];
            Component componentY = components[0];        

            if(!boolIntersect){
                
                xMentis=this.x;
                yMentis=this.y;
                
                if (componentX.getPollData() <= -0.5){
                    asset = 8;
                 this.animationLeft.tick();
                    setX(getX() - 2);
                }
                if (componentX.getPollData() >= 0.5){
                    asset = 1;
                    this.animationRight.tick();
                    setX(getX() + 2);
                }

                if (componentY.getPollData() <= 0.5){
                    asset = 5;
                    this.animationUp.tick();
                   setY(getY() - 2);
                }
                if (componentY.getPollData() >= -0.5){
                    asset =13;
                    this.animationDown.tick();
                    setY(getY() + 2);
                }
                //DIAGONAL=========================================================================================
                if (componentX.getPollData() >= 0.5 && componentY.getPollData() <= 0.5){ // 45
                    asset = 3;
                    this.animation45.tick();
                    setY(getY() - 1);
                    setX(getX() + 1);
                }
                if (componentY.getPollData() <= 0.5 && componentX.getPollData() <= -0.5){ // 135
                    asset = 7;
                    this.animation135.tick();
                    setY(getY() - 1);
                    setX(getX() - 1);
                }

                if (componentY.getPollData() >= -0.5 && componentX.getPollData() <= -0.5){ //225
                    asset =11;
                    this.animation225.tick();
                    setY(getY() + 1);
                    setX(getX() - 1);
                }
                if (componentY.getPollData() >= -0.5 && componentX.getPollData() >= 0.5){//315
                    asset =15;
                    this.animation315.tick();
                    setY(getY() + 1);
                    setX(getX() + 1);
                }
                 //DIAGONAL=========================================================================================
            }
            
            if(boolIntersect){
                this.x=xMentis;
                this.y=yMentis;
            }
        }
    }
    
    public void movimientoPersonajeTeclado(){
        boolIntersect = noPasarParedes();
        //mover player con teclado
        if(!boolIntersect){
            xMentis=this.x;
            yMentis=this.y;
            if (game.getKeyManager().left) {
                asset = 8;
                this.animationLeft.tick();
               setX(getX() - 2);
            }
            if (game.getKeyManager().right) {
                asset = 1;
                this.animationRight.tick();
               setX(getX() + 2);
            }
            // reset x position and y position if colision

            if (game.getKeyManager().up) {
                asset = 5;
                 this.animationUp.tick();
               setY(getY() - 2);
            }
            if (game.getKeyManager().down) {
                asset = 13;
                this.animationDown.tick();
               setY(getY() + 2);
            }
            //DIAGONAL
            if (game.getKeyManager().right && game.getKeyManager().up) { //45
                asset = 3;
                this.animation45.tick();
                setY(getY() - 1);
                setX(getX() + 1);
            }
            if (game.getKeyManager().left && game.getKeyManager().up) { //135
                asset = 7;
                this.animation135.tick();
                setY(getY() - 1);
                setX(getX() - 1);
            }
            if (game.getKeyManager().left && game.getKeyManager().down) { //225
                asset = 11;
                this.animation225.tick();
                setY(getY() + 1);
                setX(getX() - 1);
            }
            if (game.getKeyManager().right && game.getKeyManager().down) { //315
                asset = 15;
                this.animation315.tick();
                setY(getY() + 1);
               setX(getX() + 1);
            }
            
            
        }
        
        if(boolIntersect){
            this.x=xMentis;
            this.y=yMentis;
        }
    }
    
    public void cargarBalas(){
        while(balasTotal > 0 && cantBalas < cartucho){
            balasTotal--;
            cantBalas++;    
        }        
    }
    
    public void cargarBalasControl(){  
        for(int i = 0; i < game.getFoundControllers().size(); i++){
                Controller controller = game.getFoundControllers().get(i);
                controller.poll();
                Component[] components = controller.getComponents();
                
                if(components[10].getPollData()==1){
                    
                    tiempoCargaBalas=true;
                }   
        }
        
        if(tiempoCargaBalas){
            cargaTicks++;
        }
        
        if(cargaTicks==50){
            segundosCarga--;
            cargaTicks=0;
        }
        
        if(segundosCarga<=0){
            sound = new SoundClipTest("carga_sound");
            cargarBalas();
            tiempoCargaBalas=false;
            segundosCarga=segundosEstablesCarga;
        }
        
    }
    
    public void zombieIntersect(){
        Iterator itr = game.getZombies().iterator();
        itr = game.getZombies().iterator();
        while(itr.hasNext()){
            Zombie zom = (Zombie) itr.next();
            if(this.intersects(zom)){
                this.x=xMentis;
                this.y=yMentis;
            }
        }        
    }
    
   public void agarrarDinero(){
        Iterator itr = game.getDinero().iterator();
        while(itr.hasNext()){
            Dinero money = (Dinero) itr.next();
            /*
           // System.out.println("TICKS: " + contTicks);
            if (game.getContTicks() % 550 == 0){
                game.getDinero().remove(money);
                itr = game.getDinero().iterator();
                game.setContTickts(0);
            }*/
            if(this.intersects(money)){
                this.dinero=this.dinero+money.getCantidad();
                game.getDinero().remove(money);
                itr = game.getDinero().iterator();
                sound = new SoundClipTest("correct");
                //game.setContTicks(0);
            }
        }
    }
    
    public void lanzarGranadaFuego(){
        
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[4];
            
            if(component.getPollData()>.8 && game.getArmaExtra().getNumObjeto()==2 && game.getArmaExtra().getCantMuniciones()>0){
                if(contDesplegarGranada == 0){
                    destinoGranada = new DestinoGranada((int)(getX()+getWidth()+getWidth()*.1),(int)(getY()),(int)(getWidth()*.5),(int)(getHeight()*.5),game);
                }
                destinoGranada.tick();
                desplegarDestinoGranada=true;
                activarGranada=true;
                contDesplegarGranada++;
                nuevaGranada=true;
            }
            
            if(component.getPollData()<.2 && activarGranada){
                desplegarDestinoGranada=false;
                desplegarGranada=true;
                contDesplegarGranada=0;
                
                if(nuevaGranada){
                    granadas.add(new Granada((int)(getX()),(int)(getY()),(int)(getWidth()*.45),(int)(getHeight()*.45),0,(destinoGranada.getX() + destinoGranada.getWidth() / 2),(destinoGranada.getY() + destinoGranada.getHeight() / 2),game));
                    cantGranadasFuego--;
                    nuevaGranada=false;
                }                
            }
        }
    }
    
    public void lanzarGranadaHielo(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[4];
            
            if(component.getPollData()>.8 && game.getArmaExtra().getNumObjeto()==3 && game.getArmaExtra().getCantMuniciones()>0){
                if(contDesplegarGranadaHielo == 0){
                    destinoGranada = new DestinoGranada((int)(getX()+getWidth()+getWidth()*.1),(int)(getY()),(int)(getWidth()*.5),(int)(getHeight()*.5),game);
                }
                destinoGranada.tick();
                desplegarDestinoGranada=true;
                activarGranadaHielo=true;
                contDesplegarGranadaHielo++;
                nuevaGranadaHielo=true;
            }
            
            if(component.getPollData()<.2 && activarGranadaHielo){
                desplegarDestinoGranada=false;
                desplegarGranada=true;
                contDesplegarGranadaHielo=0;
                if(nuevaGranadaHielo){
                    granadas.add(new Granada((int)(getX()),(int)(getY()),(int)(getWidth()*.45),(int)(getHeight()*.45),1,(destinoGranada.getX() + destinoGranada.getWidth() / 2),(destinoGranada.getY() + destinoGranada.getHeight() / 2),game));
                    cantGranadasHielo--;
                    System.out.println("entraaaaaaaaaa");
                    nuevaGranadaHielo=false;
                }                
            }
        }
    }
    
    public void recuperarPocaVida(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[4];
            
            if(game.getArmaExtra().getNumObjeto()==0 && game.getArmaExtra().getCantMuniciones()>0){
                if(component.getPollData()>.8 && !validarRecuperar){
                   validarRecuperar=true; 
                }
                
                if(component.getPollData()<.2 && validarRecuperar){
                    validarRecuperar=false;
                    cantVidaChafa--;
                    
                    if(vida+game.getTienda().getProductos().get(5).getCantidadReal() <= vidaEntera){
                        vida=vida+game.getTienda().getProductos().get(5).getCantidadReal();
                    }else{
                        vida=vidaEntera;
                    }
                }
            }
        }
    }
    
    public void recuperarMuchaVida(){
        for(int i = 0; i < game.getFoundControllers().size(); i++){
            Controller controller = game.getFoundControllers().get(i);
            controller.poll();
            Component[] components = controller.getComponents();
            Component component = components[4];
            
            if(game.getArmaExtra().getNumObjeto()==1 && game.getArmaExtra().getCantMuniciones()>0){
                if(component.getPollData()>.8){
                    accionPlayer=true;
                    ticksRecuperarVida++;
                        if(ticksRecuperarVida>=200 && !validarRecuperar){
                            if(vida+recuperaMuchaVida <= vidaEntera){
                            vida=recuperaMuchaVida;
                            validarRecuperar=true;
                            accionPlayer=false;
                        }else{
                            vida=vidaEntera;
                            validarRecuperar=true;
                            accionPlayer=false;
                        }
                            cantVidaChida--;
                    }
                        
                    if(ticksRecuperarVida%50==0){
                        contSegundosRecuperar--;
                    }
                    
                  
                }
                
                if(component.getPollData()<.2 && component.getPollData()>-.1){
                    ticksRecuperarVida=0;
                    validarRecuperar=false;
                    accionPlayer=false;
                    contSegundosRecuperar=4;
                }
            }
        }
    }
    
    public void mejorarVidaTorre(){
            for(int i = 0; i < game.getFoundControllers().size(); i++){
                Controller controller = game.getFoundControllers().get(i);
                controller.poll();
                Component[] components = controller.getComponents();
                Component component = components[7];
            
                if(this.intersects(game.getTower())){
                    if(component.getPollData()==1){
                        contTicksRecuperarVida++;
                        if(contTicksRecuperarVida>=15 && game.getTower().getVida()<game.getTower().getVidaEntera()){
                            contTicksRecuperarVida=0;
                            game.getTower().setVida(game.getTower().getVida()+1);
                        }
                    }
                }
                
                if(component.getPollData()==6){
                    contTicksRecuperarVida=0;
                }
        }
            //System.out.println(contTicksRecuperarVida);
            //System.out.println(vidaEntera);
    }
    
   public void desplegarBoton(Graphics g){
       for(int i = 0; i < game.getFoundControllers().size(); i++){
                Controller controller = game.getFoundControllers().get(i);
                controller.poll();
                Component[] components = controller.getComponents();
                Component componentA = components[5];
                Component componentX = components[7];
                
                
                if(this.intersects(game.getTienda()) || this.intersects(game.getTienda2()) || this.intersects(game.getTienda3()) || this.intersects(game.getTienda4()) && componentA.getPollData()==0){
                    g.drawImage(Assets.a, getX(), (int) (getY()-(getHeight()*.7)), (int) (getWidth()*.7), (int) (getHeight()*.7), null);
                }

                if(this.intersects(game.getTower()) && componentX.getPollData()==0){
                    g.drawImage(Assets.x, getX(), (int) (getY()-(getHeight()*.7)), (int) (getWidth()*.7), (int) (getHeight()*.7 ), null);
                }
       }
       
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

    public void setMaxVidaChida(int maxVidaChida) {
        this.maxVidaChida = maxVidaChida;
    }

    public void setMaxVidaChafa(int maxVidaChafa) {
        this.maxVidaChafa = maxVidaChafa;
    }

    public int getMaxVidaChida() {
        return maxVidaChida;
    }

    public int getMaxVidaChafa() {
        return maxVidaChafa;
    }

    /**
     *
     * @return
     */
    public int getAsset() {
        return asset;
    }

    /**
     *
     * @param asset
     */
    public void setAsset(int asset) {
        this.asset = asset;
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

    public int getMaxBalas() {
        return maxBalas;
    }

    public void setMaxBalas(int maxBalas) {
        this.maxBalas = maxBalas;
    }

    public int getSegundosEstablesCarga() {
        return segundosEstablesCarga;
    }

    public void setSegundosEstablesCarga(int segundosEstablesCarga) {
        this.segundosEstablesCarga = segundosEstablesCarga;
    }

    public double getVidaEntera() {
        return vidaEntera;
    }

    public void setVidaEntera(double vidaEntera) {
        this.vidaEntera = vidaEntera;
    }
    
    
    
    /**
     *
     * @return
     */

    @Override

    public Rectangle getBounds(){
        return new Rectangle (getX()+8 , getY(),getWidth() - 20 , getHeight());
    }
    
    
    /**
     *
     * @return
     */
    public double getVida() {
        return vida;
    }

    /**
     *
     * @param vida
     */
    public void setVida(double vida) {
        this.vida = vida;
    }

    /**
     *
     * @return
     */
    public boolean isValBalas() {
        return valBalas;
    }

    /**
     *
     * @param valBalas
     */
    public void setValBalas(boolean valBalas) {
        this.valBalas = valBalas;
    }
    

    /**
     *
     * @return
     */
    public int getClosestNode() {
        return closestNode;
    }

    /**
     *
     * @param closestNode
     */
    public void setClosestNode(int closestNode) {
        this.closestNode = closestNode;
    }

    public int getxMentis() {
        return xMentis;
    }

    public void setxMentis(int xMentis) {
        this.xMentis = xMentis;
    }

    public int getyMentis() {
        return yMentis;
    }

    public void setyMentis(int yMentis) {
        this.yMentis = yMentis;
    }

    public boolean isBoolIntersect() {
        return boolIntersect;
    }

    public void setBoolIntersect(boolean boolIntersect) {
        this.boolIntersect = boolIntersect;
    }

    public int getCantBalas() {
        return cantBalas;
    }

    public void setCantBalas(int cantBalas) {
        this.cantBalas = cantBalas;
    }

    public int getBalasTotal() {
        return balasTotal;
    }

    public void setBalasTotal(int balasTotal) {
        this.balasTotal = balasTotal;
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

    public int getCartucho() {
        return cartucho;
    }

    public void setCartucho(int cartucho) {
        this.cartucho = cartucho;
    }

    public boolean isDispararControl() {
        return dispararControl;
    }

    public void setDispararControl(boolean dispararControl) {
        this.dispararControl = dispararControl;
    }

    public boolean isTiempoCargaBalas() {
        return tiempoCargaBalas;
    }

    public void setTiempoCargaBalas(boolean tiempoCargaBalas) {
        this.tiempoCargaBalas = tiempoCargaBalas;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }
    public int getSegundosCarga() {
        return segundosCarga;
    }

    public void setSegundosCarga(int segundosCarga) {
        this.segundosCarga = segundosCarga;
    }

    public int getCantGranadasHielo() {
        return cantGranadasHielo;
    }

    public void setCantGranadasHielo(int cantGranadasHielo) {
        this.cantGranadasHielo = cantGranadasHielo;
    }

    public int getCantGranadasFuego() {
        return cantGranadasFuego;
    }

    public void setCantGranadasFuego(int cantGranadasFuego) {
        this.cantGranadasFuego = cantGranadasFuego;
    }

    public int getCantVidaChafa() {
        return cantVidaChafa;
    }

    public void setCantVidaChafa(int cantVidaChafa) {
        this.cantVidaChafa = cantVidaChafa;
    }

    public int getCantVidaChida() {
        return cantVidaChida;
    }

    public void setCantVidaChida(int cantVidaChida) {
        this.cantVidaChida = cantVidaChida;
    }

    public int getCantMaximasGranadas() {
        return cantMaximasGranadas;
    }

    public void setCantMaximasGranadas(int cantMaximasGranadas) {
        this.cantMaximasGranadas = cantMaximasGranadas;
    }

    public DestinoGranada getDestinoGranada() {
        return destinoGranada;
    }

    public void setDestinoGranada(DestinoGranada destinoGranada) {
        this.destinoGranada = destinoGranada;
    }

    public ArrayList<Granada> getGranadas() {
        return granadas;
    }

    public void setGranadas(ArrayList<Granada> granadas) {
        this.granadas = granadas;
    }

    public boolean isDesplegarGranada() {
        return desplegarGranada;
    }

    public void setDesplegarGranada(boolean desplegarGranada) {
        this.desplegarGranada = desplegarGranada;
    }

    public int getCantMaximasVidasChafas() {
        return cantMaximasVidasChafas;
    }

    public void setCantMaximasVidasChafas(int cantMaximasVidasChafas) {
        this.cantMaximasVidasChafas = cantMaximasVidasChafas;
    }

    public int getCantMaximasVidasChidas() {
        return cantMaximasVidasChidas;
    }

    public void setCantMaximasVidasChidas(int cantMaximasVidasChidas) {
        this.cantMaximasVidasChidas = cantMaximasVidasChidas;
    }

    public int getRecuperaMuchaVida() {
        return recuperaMuchaVida;
    }

    public void setRecuperaMuchaVida(int recuperaMuchaVida) {
        this.recuperaMuchaVida = recuperaMuchaVida;
    }

    public int getContDrone() {
        return contDrone;
    }

    public void setContDrone(int contDrone) {
        this.contDrone = contDrone;
    }

    public int getContNinja() {
        return contNinja;
    }

    public void setContNinja(int contNinja) {
        this.contNinja = contNinja;
    }

    public int getContHada() {
        return contHada;
    }

    public void setContHada(int contHada) {
        this.contHada = contHada;
    }

    public int getContSwat() {
        return contSwat;
    }

    public void setContSwat(int contSwat) {
        this.contSwat = contSwat;
    }

    public int getContTicksRecuperarVida() {
        return contTicksRecuperarVida;
    }

    public void setContTicksRecuperarVida(int contTicksRecuperarVida) {
        this.contTicksRecuperarVida = contTicksRecuperarVida;
    }

    public boolean isNuevaGranada() {
        return nuevaGranada;
    }

    public void setNuevaGranada(boolean nuevaGranada) {
        this.nuevaGranada = nuevaGranada;
    }

    public boolean isNuevaGranadaHielo() {
        return nuevaGranadaHielo;
    }

    public void setNuevaGranadaHielo(boolean nuevaGranadaHielo) {
        this.nuevaGranadaHielo = nuevaGranadaHielo;
    }
    
    
    
}










