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
public class SobrevivienteRocky extends Item {

    private Game game;
    private int piso;
    private int vida;
    private boolean valBalas = true;
    private int closestNode = 10;
    private boolean goalb = false;
    private int goalp;
    private int xgoal = 0;
    private int ygoal = 0;
    private int velocity = 10;
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
    private int rango;
    private int objX;
    private int objY;
    private int dano = 200;
    private boolean goalz = false;
    private int contGoal = 0;
    private int velocidadAtaque = 100;
    private int velocidadMovimiento = 1;
    private int contAtaque= 0;
    private boolean bspin = false;
    private int zombiesTickPasado;
    private int vidaMaxima;
    
    
    private ArrayList<Integer> areax;
    private ArrayList<Integer> areay;
    private boolean healer = true;
    
    private Animation survivor_rocky_up = new Animation(Assets.survivor_rocky_up, 100);
    private Animation survivor_rocky_right = new Animation(Assets.survivor_rocky_right, 100);
    private Animation survivor_rocky_down = new Animation(Assets.survivor_rocky_down, 100);
    private Animation survivor_rocky_left = new Animation(Assets.survivor_rocky_left, 100);
    private Animation survivor_rocky_spin = new Animation(Assets.survivor_rocky_vueltaCulera,30);
    
    
    public SobrevivienteRocky(int x, int y, int width, int height,int dano,int vida,int velocity,int piso,int velocidadAtaque, Game game) {
        super(x, y, width, height);
        this.dano = dano;
        this.game = game;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.velocity = velocity;
        this.piso = piso;
        this.velocidadAtaque = velocidadAtaque;
        rango = 150;
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
    
      @Override
    public  Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }

    public void setDano(int dano) {
        this.dano = dano;
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


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
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

    public boolean isGoalz() {
        return goalz;
    }

    public void setGoalz(boolean goalz) {
        this.goalz = goalz;
    }

    public int getContGoal() {
        return contGoal;
    }

    public void setContGoal(int contGoal) {
        this.contGoal = contGoal;
    }

    public int getVelocidadAtaque() {
        return velocidadAtaque;
    }

    public void setVelocidadAtaque(int velocidadAtaque) {
        this.velocidadAtaque = velocidadAtaque;
    }


    public int getVelocidadMovimiento() {
        return velocidadMovimiento;
    }

    public void setVelocidadMovimiento(int velocidadMovimiento) {
        this.velocidadMovimiento = velocidadMovimiento;
    }

    public boolean isHealer() {
        return healer;
    }

    public void setHealer(boolean healer) {
        this.healer = healer;
    }

    public Animation getSurvivor_rocky_up() {
        return survivor_rocky_up;
    }

    public void setSurvivor_rocky_up(Animation survivor_rocky_up) {
        this.survivor_rocky_up = survivor_rocky_up;
    }

    public Animation getSurvivor_rocky_right() {
        return survivor_rocky_right;
    }

    public void setSurvivor_rocky_right(Animation survivor_rocky_right) {
        this.survivor_rocky_right = survivor_rocky_right;
    }

    public Animation getSurvivor_rocky_down() {
        return survivor_rocky_down;
    }

    public void setSurvivor_rocky_down(Animation survivor_rocky_down) {
        this.survivor_rocky_down = survivor_rocky_down;
    }

    public Animation getSurvivor_rocky_left() {
        return survivor_rocky_left;
    }

    public void setSurvivor_rocky_left(Animation survivor_rocky_left) {
        this.survivor_rocky_left = survivor_rocky_left;
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

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    
    
    @Override
    public void tick() {
        contAtaque++;
        ///////////////
        //
        //Faltan la colsion de este sobreviviente con los zombies y que haga algo
        ////////////
        
        //cuando no tiene objetivo de un zombie
        if( game.getZombies().size() > 0 ){
            if( zombiesTickPasado != game.getZombies().size() ){
                zombiesTickPasado = game.getZombies().size();
                goalz = false;
            }
            if( !goalz ){
                objZom = 0;
                for(int i = 0;  i < game.getZombies().size(); i++){
                    if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) <  DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() )){
                        objZom = i;
                    }
                } 
                goalz = true;
                voy = game.getZombies().get(objZom).ClosestNode() ;
            }

            //si ya tiene un path predefinido seguirlo
            if(bpath){
                if(goalb){
                    if(getX() + (getWidth() / 2) - 5 <= xgoal && getX() + (getWidth() / 2) + 5 >= xgoal && getY() + (getHeight() / 2) - 5 <= ygoal && getY() + (getHeight() / 2) + 5 >= ygoal){
                        goalb = false;
                        path.remove(0);
                    }
                    else if(game.getZombies().size() >= objZom){
                        if(game.getZombies().get(objZom).ClosestNode() == ClosestNode()){
                            xgoal = game.getZombies().get(objZom).getX();
                            ygoal = game.getZombies().get(objZom).getY();
                        }
                    }
                    if(path.isEmpty()){
                        bpath = false;
                        goalb = false;
                        goalz = false;
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
                closestNode = ClosestNode();
                path.add(closestNode);
                NodoMedio(closestNode,voy);
                bpath = true;
            }     
            
            if(game.getZombies().get(objZom).intersects(this) && contAtaque >= velocidadAtaque){
                Explosion exp = new Explosion(2,this.getX() ,this.getY() , this.getWidth(), this.getHeight(), 50,this.getPiso(),game );
                game.getExplosiones().add(exp);
                contAtaque = 0;
                //sound = new SoundClipTest("boom"); // SONIDO DE BOOOM!!!
                int xExp = exp.getX();
                int yExp = exp.getY();
                double radioExp = 100;

                //colision de explosion con otro zombie
                Iterator itr3 = game.getZombies().iterator();
                while(itr3.hasNext()){
                    Zombie zomb = (Zombie) itr3.next();
                    if(DistanciaDosPuntos( zomb.getX() + (zomb.getWidth() / 2), xExp, zomb.getY() + (zomb.getHeight() / 2) , yExp ) <= radioExp && zomb.getPiso() == piso ){
                        zomb.setVidaZ(zomb.getVidaZ() - dano);
                    }                                  
                }
                bspin = true;
                survivor_rocky_spin = new Animation(Assets.survivor_rocky_vueltaCulera,30);

            }
            
            ComponentesMovimiento();
            movimiento();
            animacionTicks();
            arreglosArea();
            CambioPiso();
        }
        
        if(contGoal >= 300){
            contGoal = 0;
            bpath = false;
            goalz = false;
            goalb = false;
            path.clear();
        }
        contGoal++;
    }

    @Override
    public void render(Graphics g) {
        if(bspin){
            g.drawImage(survivor_rocky_spin.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            if(survivor_rocky_spin.getIndex() == 7){
                bspin = false;
            }
        }
        else{
            switch (cuadrante) {
                case 1:
                    if(pixelsx >= pixelsy){
                        g.drawImage(survivor_rocky_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else{
                        g.drawImage(survivor_rocky_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }   break;
                case 2:
                    if(pixelsx >= pixelsy){
                        g.drawImage(survivor_rocky_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else{
                        g.drawImage(survivor_rocky_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }   break;
                case 3:
                    if(pixelsx >= pixelsy){
                        g.drawImage(survivor_rocky_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else{
                        g.drawImage(survivor_rocky_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }   break;
                case 4:
                    if(pixelsx >= pixelsy){
                        g.drawImage(survivor_rocky_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else{
                        g.drawImage(survivor_rocky_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }   break;
                default:
                    break;
            }
        }
        
    //agregar la vida visible de los zombies
    g.setColor(Color.green);
    g.fillRect(getX(), (int)(getY()-getHeight()*.1), (int) (getWidth()*(vida/vidaMaxima)),(int) (getHeight()-getHeight()*.9));
    
         
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
    
    public void animacionTicks(){
        if(bspin){
            this.survivor_rocky_spin.tick();
        }
        if( cuadrante == 1){
            if(pixelsx >= pixelsy){
                this.survivor_rocky_right.tick();
            }
            else{
                this.survivor_rocky_up .tick();                 
            }
        }else if( cuadrante == 2 ){
            if(pixelsx >= pixelsy){
                this.survivor_rocky_left.tick();
            }
            else{
                this.survivor_rocky_up .tick();    
            }
        }else if( cuadrante == 3){
            if(pixelsx >= pixelsy){
                this.survivor_rocky_left.tick();    
            }
            else{
                this.survivor_rocky_down .tick();
            }
        }else if(cuadrante == 4 ){
            if(pixelsx >= pixelsy){
                this.survivor_rocky_right.tick();
            }
            else{
                this.survivor_rocky_down.tick();    
            }
        }
    }
    
}
