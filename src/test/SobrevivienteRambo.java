/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author ALBER
 */
public class SobrevivienteRambo extends Item {
    private Game game;
    private int piso;
    private double vida;
    private int cantBalas = 40;
    private boolean valBalas = true;
    private int closestNode = 10;
    private int balasTotal = 200;
    private int cartucho = 40;
    private boolean tiempoCargaBalas = false;
    private int cargaTicks=0;
    private int segundosCarga=5;
    private int objetivo = 1;
    private int cont = -1;
    private boolean goalb = false;
    private int goalp;
    private int xgoal = 0;
    private int ygoal = 0;
    private int velocity = 1;
    private int pixelsx = 1;
    private int pixelsy = 1;
    private int cuadrante = 1;
    private boolean bpath = false; //flag para saber si tiene un path como objetivo
    private ArrayList<Integer> path = new ArrayList<>();
    private int voy;
    private int nextPath;
    private int contTicks=0;
    private boolean objetivoZombie = false; //flag para saber si tiene como objetivo un zombie
    private int objZom = -1;
    private boolean ZombieEnRango = false;
    private int rangoDisparo;
    private int objX;
    private int objY;
    private int dano = 20;
    private int contDisparo = 0;
    private ArrayList<Integer> areax;
    private ArrayList<Integer> areay;
    private boolean healer = false;
    private int velocidadDisparo = 20;
    private double vidaMaxima=100;
    SoundClipTest sound;

    private Animation survivor_rambo_up = new Animation(Assets.survivor_rambo_up, 100);
    private Animation survivor_rambo_right = new Animation(Assets.survivor_rambo_right, 100);
    private Animation survivor_rambo_down = new Animation(Assets.survivor_rambo_down, 100);
    private Animation survivor_rambo_left = new Animation(Assets.survivor_rambo_left, 100);
    
    private Animation survivor_rambo_up_s = new Animation(Assets.survivor_rambo_up_shooting, 100);
    private Animation survivor_rambo_right_s = new Animation(Assets.survivor_rambo_right_shooting, 100);
    private Animation survivor_rambo_down_s = new Animation(Assets.survivor_rambo_down_shooting, 100);
    private Animation survivor_rambo_left_s = new Animation(Assets.survivor_rambo_left_shooting, 100);

    
    public SobrevivienteRambo(int x, int y, int width, int height,int dano,int velocidadDisparo,int vida,int rango,int piso, Game game) {
        super(x, y, width, height);
        this.dano = dano;
        this.game = game;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.piso = piso;
        this.velocidadDisparo = velocidadDisparo;
        this.rangoDisparo = rango * 100;
        init();
    }
    
    public void init(){
        areax = new ArrayList<>();
        
        areax.add(0);
        areax.add(0);
        areax.add(0);
        areax.add(0);
        areax.add(0);
        areax.add(0);
        areax.add(0);
        areax.add(0);
        
        areay = new ArrayList<>(); 
        areay.add(0);
        areay.add(0);
        areay.add(0);
        areay.add(0);
        areay.add(0);
        areay.add(0);
        areay.add(0);
        areay.add(0);       
    }

    public int getObjZom() {
        return objZom;
    }

    public void setObjZom(int objZom) {
        this.objZom = objZom;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }


      @Override
    public  Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }
    public ArrayList<Integer> getAreax() {
        return areax;
    }

    public void setAreax(ArrayList<Integer> areax) {
        this.areax = areax;
    }

    public ArrayList<Integer> getAreay() {
        return areay;
    }

    public void setAreay(ArrayList<Integer> areay) {
        this.areay = areay;
    }
    


    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(double vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getCantBalas() {
        return cantBalas;
    }

    public void setCantBalas(int cantBalas) {
        this.cantBalas = cantBalas;
    }

    public boolean isValBalas() {
        return valBalas;
    }

    public void setValBalas(boolean valBalas) {
        this.valBalas = valBalas;
    }

    public int getClosestNode() {
        return closestNode;
    }

    public void setClosestNode(int closestNode) {
        this.closestNode = closestNode;
    }

    public int getBalasTotal() {
        return balasTotal;
    }

    public void setBalasTotal(int balasTotal) {
        this.balasTotal = balasTotal;
    }

    public int getCartucho() {
        return cartucho;
    }

    public void setCartucho(int cartucho) {
        this.cartucho = cartucho;
    }

    public boolean isTiempoCargaBalas() {
        return tiempoCargaBalas;
    }

    public void setTiempoCargaBalas(boolean tiempoCargaBalas) {
        this.tiempoCargaBalas = tiempoCargaBalas;
    }

    public int getCargaTicks() {
        return cargaTicks;
    }

    public void setCargaTicks(int cargaTicks) {
        this.cargaTicks = cargaTicks;
    }

    public int getSegundosCarga() {
        return segundosCarga;
    }

    public void setSegundosCarga(int segundosCarga) {
        this.segundosCarga = segundosCarga;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public boolean isGoalb() {
        return goalb;
    }

    public void setGoalb(boolean goalb) {
        this.goalb = goalb;
    }

    public int getGoalp() {
        return goalp;
    }

    public void setGoalp(int goalp) {
        this.goalp = goalp;
    }

    public int getXgoal() {
        return xgoal;
    }

    public void setXgoal(int xgoal) {
        this.xgoal = xgoal;
    }

    public int getYgoal() {
        return ygoal;
    }

    public void setYgoal(int ygoal) {
        this.ygoal = ygoal;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getPixelsx() {
        return pixelsx;
    }

    public void setPixelsx(int pixelsx) {
        this.pixelsx = pixelsx;
    }

    public int getPixelsy() {
        return pixelsy;
    }

    public void setPixelsy(int pixelsy) {
        this.pixelsy = pixelsy;
    }

    public int getCuadrante() {
        return cuadrante;
    }

    public void setCuadrante(int cuadrante) {
        this.cuadrante = cuadrante;
    }

    public boolean isBpath() {
        return bpath;
    }

    public void setBpath(boolean bpath) {
        this.bpath = bpath;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

    public int getVoy() {
        return voy;
    }

    public void setVoy(int voy) {
        this.voy = voy;
    }

    public int getNextPath() {
        return nextPath;
    }

    public void setNextPath(int nextPath) {
        this.nextPath = nextPath;
    }

    public int getContTicks() {
        return contTicks;
    }

    public void setContTicks(int contTicks) {
        this.contTicks = contTicks;
    }

    public boolean isObjetivoZombie() {
        return objetivoZombie;
    }

    public void setObjetivoZombie(boolean objetivoZombie) {
        this.objetivoZombie = objetivoZombie;
    }

    public boolean isZombieEnRango() {
        return ZombieEnRango;
    }

    public void setZombieEnRango(boolean ZombieEnRango) {
        this.ZombieEnRango = ZombieEnRango;
    }

    public int getRangoDisparo() {
        return rangoDisparo;
    }

    public void setRangoDisparo(int rangoDisparo) {
        this.rangoDisparo = rangoDisparo * 100;
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

    public int getContDisparo() {
        return contDisparo;
    }

    public void setContDisparo(int contDisparo) {
        this.contDisparo = contDisparo;
    }

    public boolean isHealer() {
        return healer;
    }

    public void setHealer(boolean healer) {
        this.healer = healer;
    }

    public int getVelocidadDisparo() {
        return velocidadDisparo;
    }

    public void setVelocidadDisparo(int velocidadDisparo) {
        this.velocidadDisparo = velocidadDisparo;
    }

    public Animation getSurvivor_rambo_up() {
        return survivor_rambo_up;
    }

    public void setSurvivor_rambo_up(Animation survivor_rambo_up) {
        this.survivor_rambo_up = survivor_rambo_up;
    }

    public Animation getSurvivor_rambo_right() {
        return survivor_rambo_right;
    }

    public void setSurvivor_rambo_right(Animation survivor_rambo_right) {
        this.survivor_rambo_right = survivor_rambo_right;
    }

    public Animation getSurvivor_rambo_down() {
        return survivor_rambo_down;
    }

    public void setSurvivor_rambo_down(Animation survivor_rambo_down) {
        this.survivor_rambo_down = survivor_rambo_down;
    }

    public Animation getSurvivor_rambo_left() {
        return survivor_rambo_left;
    }

    public void setSurvivor_rambo_left(Animation survivor_rambo_left) {
        this.survivor_rambo_left = survivor_rambo_left;
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

    @Override
    public void tick() {
        arreglosArea();
        CambioPiso();
        objZom = -1;
        for(int i = 0;  i < game.getZombies().size(); i++){
            if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) < rangoDisparo && game.getZombies().get(i).getPiso() == piso ){
                if(objZom == -1){
                    objZom = i;
                    objetivoZombie = true;
                    objX = game.getZombies().get(i).getX();
                    objY = game.getZombies().get(i).getY();
                }
                else{
                    if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) <  DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() )){
                        objZom = i;
                        objetivoZombie = true;
                        objX = game.getZombies().get(i).getX();
                        objY = game.getZombies().get(i).getY();
                    }
                }
            }
        }
        //System.out.println(objZom);
        if( objZom != -1 ){
            if( DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() ) > rangoDisparo || game.getZombies().get(objZom).getPiso() != piso ){
                //System.out.println("que pe");
                objetivoZombie = false;
                objZom = -1;
            }
        }
        
        //el sobreviviente le va a disparar a un zombie
        if(objetivoZombie ){
            if(contDisparo >= velocidadDisparo){
                game.getBullets().add( new Bullet(x + (this.getWidth() / 2), y + (this.getHeight() / 2), 5 , 5 , 5 ,objX,objY,piso,  2,dano,game ) );
                sound = new SoundClipTest("sound_click"); // SONIDO DE BOOOM!!!
                if(healer){
                    game.getHealer().setMove(true);
                    game.getHealer().changeGoal(x, y, objX, objY);
                }
                objetivoZombie = false;
                contDisparo = 0;
            }
        }
                
        //cuando no tiene objetivo de un zombie
        else{
            //System.out.println("witf");

            //si ya tiene un path predefinido seguirlo
            if(bpath){
                if(goalb){
                    if(getX() + (getWidth() / 2) - 5 <= xgoal && getX() + (getWidth() / 2) + 5 >= xgoal && getY() + (getHeight() / 2) - 5 <= ygoal && getY() + (getHeight() / 2) + 5 >= ygoal){
                        goalb = false;
                        path.remove(0);
                    }
                    if(path.isEmpty()){
                        bpath = false;
                        goalb = false;
                    }
                }
                else{
                    nextPath = path.get(0);
                    xgoal = game.getFloyd().getNodos().get(nextPath).getX() ;
                    ygoal = game.getFloyd().getNodos().get(nextPath).getY() ;
                    goalb = true;
                }
            }
            //si no tiene un path generar un random de nodo en el mapa para seguirlo
            else{       
                //System.out.println("1");
                voy = (int) (Math.random() * 36) + 1;
                closestNode = ClosestNode();
                path.add(closestNode);
                NodoMedio(closestNode,voy);
                bpath = true;
            }
            ComponentesMovimiento();
            movimiento(); 
        }
        contDisparo++;
    }

    @Override
    public void render(Graphics g) {
        if(objetivoZombie){
            int dx = Math.abs(x - objX);
            int dy = Math.abs(y - objY);
            if(objX >= x){
                //cuadrante 1
                if(objY <= y){
                    if(dx >= dy){
                        g.drawImage(survivor_rambo_right_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                    else{
                        g.drawImage(survivor_rambo_up_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
                // cuadrante 4
                else{
                    if(dx >= dy){
                        g.drawImage(survivor_rambo_right_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                    else{
                        g.drawImage(survivor_rambo_down_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
            }
            else{
                //cuadrante 2
                if(objY <= y){
                    if(dx >= dy){
                        g.drawImage(survivor_rambo_left_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                    else{
                        g.drawImage(survivor_rambo_up_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
                // cuadrante 4
                else{
                    if(dx >= dy){
                        g.drawImage(survivor_rambo_left_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                    else{
                        g.drawImage(survivor_rambo_down_s.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
            }
        }
        else{
           switch (cuadrante) {
               case 1:
                   if(pixelsx >= pixelsy){
                       g.drawImage(survivor_rambo_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }else{
                       g.drawImage(survivor_rambo_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }  break;
               case 2:
                   if(pixelsx >= pixelsy){
                       g.drawImage(survivor_rambo_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }else{
                       g.drawImage(survivor_rambo_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }  break;
               case 3:
                   if(pixelsx >= pixelsy){
                       g.drawImage(survivor_rambo_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }else{
                       g.drawImage(survivor_rambo_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }  break;
               case 4:
                   if(pixelsx >= pixelsy){
                       g.drawImage(survivor_rambo_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }else{
                       g.drawImage(survivor_rambo_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }  break;
               default:
                   break;
           }
       }
        
        desplegarVida(g);
    }
    
    public void NodoMedio(int i, int j){
        int mat[][] = game.getFloyd().getPaths();
        //System.out.println(i +"     " + j);
        int k = mat[i][j];
        if(k != i ){
            NodoMedio(i,k);
            path.add(k);
            if(mat[k][j] == k){
                path.add(j);
            }
            else{
                NodoMedio(k,j);
            }
        }
        else{
            path.add(j);
        }
    }
    
    public int ClosestNode(){
        int temp = closestNode;
        Iterator itr = game.getFloyd().getNodos().iterator();
        while(itr.hasNext()){
            Node nod = (Node) itr.next();
            if(nod.getPiso() == piso){
                if( DistanciaDosPuntos(this.getX() + (this.getWidth() / 2) , nod.getX(), this.getY() + (this.getHeight() / 2) ,nod.getY()) < DistanciaDosPuntos(this.getX() + (this.getWidth() / 2),game.getFloyd().getNodos().get(temp).getX(), this.getY() + (this.getHeight() / 2),game.getFloyd().getNodos().get(temp).getY())){
                    temp = nod.getId();
                }
            }
        }
        return temp;
    }
    
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        
        return Math.sqrt(deltax2 + deltay2);
    }
    
    public void movimiento(){
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
    
    public void ComponentesMovimiento(){
        int mx = xgoal;
        int my = ygoal;
        int px = getX()+ (getWidth()/2) ;
        int py = getY()+ (getHeight()/2);
        //System.out.println(mx + "       " + my);
        
        int dx = Math.abs(px - mx);
        int dy = Math.abs(py - my);
        double distance = Math.sqrt(Math.pow((dx), 2) + Math.pow((dy), 2));
        double grados = Math.toDegrees( Math.acos( dx / distance ) ) ;
        //System.out.println(grados);
  
        //NORMAL
        if(cuadrante == 1){
            if(pixelsx >= pixelsy){
                this.survivor_rambo_right.tick();
            }else{
                 this.survivor_rambo_up.tick();
            }
            
        }else if(cuadrante == 2 ){
            if(pixelsx >= pixelsy){
                this.survivor_rambo_left.tick();
            }else{
                 this.survivor_rambo_up.tick();
            }
        }else if(cuadrante == 3){
            if(pixelsx >= pixelsy){
                this.survivor_rambo_left.tick();
            }else{
                 this.survivor_rambo_down.tick();
            }
        }else if(cuadrante == 4 ){
            if(pixelsx >= pixelsy){
                this.survivor_rambo_right.tick();
            }else{
                 this.survivor_rambo_down.tick();
            }
        }
        
        
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
        
        if(mx > px){
            //lado derecho del personaje
            if(my < py){
                this.cuadrante = 1;
            }
            else{
                this.cuadrante = 4;
            }
        }
        else{
            if(my < py){
                this.cuadrante = 2;
            }
            else{
                this.cuadrante = 3;
            }           
        }
    }
    
    public void CambioPiso(){
        Iterator itr = game.getEscaleras().iterator();
        while(itr.hasNext()){
            Escalera esca = (Escalera) itr.next();
            esca.tick();
            if(esca.intersects(this)){
                this.piso = esca.getPiso();
            }
        }
    }
    
    public void arreglosArea(){
        int mx = this.getX() + (this.getWidth() / 2);
        int my = this.getY() + (this.getHeight() / 2);
        
        areax.set(0, mx - 50);
        areax.set(1, mx - 25);
        areax.set(2, mx);
        areax.set(3, mx + 25);
        areax.set(4, mx + 50);
        areax.set(5, mx + 25);
        areax.set(6, mx);
        areax.set(7, mx - 25);

        areay.set(0, my);
        areay.set(1, my - 25);
        areay.set(2, my - 50);
        areay.set(3, my - 25);
        areay.set(4, my);
        areay.set(5, my + 25);
        areay.set(6, my + 50);
        areay.set(7, my + 25);        
    }
    public void desplegarVida(Graphics g){
        g.setColor(Color.green);
        g.fillRect(getX(), (int)(getY()-getHeight() * .1), (int) ((getWidth()) * (vida/vidaMaxima)),(int) (getHeight() - getHeight() * .9));
    }
}
