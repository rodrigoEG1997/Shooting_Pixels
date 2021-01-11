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
public class SobrevivienteEscudo extends Item {
    private Game game;
    private int piso;
    private int vida;
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
    
    private Animation survivor_escudo_up = new Animation(Assets.survivor_escudo_up, 100);
    private Animation survivor_escudo_right = new Animation(Assets.survivor_escudo_right, 100);
    private Animation survivor_escudo_down = new Animation(Assets.survivor_escudo_down, 100);
    private Animation survivor_escudo_left = new Animation(Assets.survivor_escudo_left, 100);
    
    
    public SobrevivienteEscudo(int x, int y, int width, int height,int piso,Game game) {
        super(x, y, width, height);
        this.game = game;
        this.piso = piso;
    }
      @Override
    public  Rectangle getBounds(){
        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }


    @Override
    public void tick() {
        CambioPiso();
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
            System.out.println("1");
            voy = (int) (Math.random() * 36) + 1;
            closestNode = ClosestNode();
            path.add(closestNode);
            NodoMedio(closestNode,voy);
            bpath = true;
        }
        ComponentesMovimiento();
        movimiento(); 
        
    }

    @Override
    public void render(Graphics g) {
           
        if(cuadrante == 1){
            if(pixelsx >= pixelsy){
                g.drawImage(survivor_escudo_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }else{
                 g.drawImage(survivor_escudo_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }

        }else if(cuadrante == 2 ){
            if(pixelsx >= pixelsy){
                g.drawImage(survivor_escudo_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }else{
                 g.drawImage(survivor_escudo_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }else if(cuadrante == 3){
            if(pixelsx >= pixelsy){
                g.drawImage(survivor_escudo_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }else{
                 g.drawImage(survivor_escudo_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }else if(cuadrante == 4 ){
            if(pixelsx >= pixelsy){
                g.drawImage(survivor_escudo_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }else{
                 g.drawImage(survivor_escudo_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            }
        }
         
   
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
}
