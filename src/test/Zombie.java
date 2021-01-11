package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import static test.Assets.zombie_normal_down;
import static test.Assets.zombie_normal_left;
import static test.Assets.zombie_normal_right;
import static test.Assets.zombie_normal_up;

/**
 *
 * VERSION 2222222
 */
public class Zombie extends Item {
    private Game game;
    /*tipo de zombie 
        1. el normal 
        2. el flash(rapido)
        3. el mamado(mas vida)
        4. el kamikaze(explota cuando llega a su objetivo o cuando se le mata)
        la inicilizacion se hara desde game solo cambiar atributo velocidad o vida para 2 y 3 
        y para el 4 hacer un if de cuando muera deje una explosion
    */
    
    private Animation zombie_normal_up;
    private Animation zombie_normal_right;
    private Animation zombie_normal_down;
    private Animation zombie_normal_left;
    
    private Animation zombie_flash_up;
    private Animation zombie_flash_right;
    private Animation zombie_flash_down;
    private Animation zombie_flash_left;
    
    private Animation zombie_mamado_up;
    private Animation zombie_mamado_right;
    private Animation zombie_mamado_down;
    private Animation zombie_mamado_left;
    
    private Animation zombie_kamikaze_up;
    private Animation zombie_kamikaze_right;
    private Animation zombie_kamikaze_down;
    private Animation zombie_kamikaze_left;
    //Zombies Congelados
    private Animation zombie_normal_up_congelado;
    private Animation zombie_normal_right_congelado;
    private Animation zombie_normal_down_congelado;
    private Animation zombie_normal_left_congelado;
    
    private Animation zombie_flash_up_congelado;
    private Animation zombie_flash_right_congelado;
    private Animation zombie_flash_down_congelado;
    private Animation zombie_flash_left_congelado;
    
    private Animation zombie_mamado_up_congelado;
    private Animation zombie_mamado_right_congelado;
    private Animation zombie_mamado_down_congelado;
    private Animation zombie_mamado_left_congelado;
    
    private Animation zombie_kamikaze_up_congelado;
    private Animation zombie_kamikaze_right_congelado;
    private Animation zombie_kamikaze_down_congelado;
    private Animation zombie_kamikaze_left_congelado;
    
    private int tipoZombie; 
    private int piso;
    private int objetivo = 0;
    private int cont = -1;
    private boolean goalb = false;
    private int goalp;
    private int xgoal = 0;
    private int ygoal = 0;
    private int velocity = 1;
    private int pixelsx = 1;
    private int pixelsy = 1;
    private int cuadrante = 1;
    private int simon = 0;
    private int closestNode = 1;
    private boolean bpath = false;
    private ArrayList<Integer> path = new ArrayList<>();
    private int voy;
    private int nextPath;
    private int ContTicks = 30;
    private double vidaZ;
    private double vidaEntera;
    private int xMentis;
    private int yMentis;
    private int contTicks=0;
    private boolean intersectTower=false;
    private boolean notFinal = true;

    private boolean colision = false;
    private int camino;
    private int objetivoSuperviviente; //para saber a cual de todos los supervivientes escojera 
    private double grados;
    
    private boolean congelado;
    private int ticksCongelado = 0;
    private int contCongelado = 0;
    private boolean bAtaque = true;
    private int contAtaque = 0;
    private int dano = 5;
    
    /**
     *
     * @param tipoZombie
     * @param x
     * @param y
     * @param width
     * @param height
     * @param piso
     * @param vidaZ
     * @param game
     */
    public Zombie(int tipoZombie,int x, int y, int width, int height,int piso, int vidaZ, double vidaEntera,boolean colision, Game game){
        super(x,y,width,height);
        this.tipoZombie = tipoZombie;
        this.game = game;
        this.piso = piso;
        this.vidaZ = game.getVidaZombie();
        this.congelado = false;
        this.vidaEntera = vidaEntera;
        objetivo = (int) (Math.random() * 2);
        if( objetivo == 0){
            objetivoSuperviviente = (int) (Math.random() * 3); 
        }
        this.colision = colision;
        switch (tipoZombie) {
            case 1:
                zombie_normal_up = new Animation(Assets.zombie_normal_up, 100);
                zombie_normal_right = new Animation(Assets.zombie_normal_right, 100);
                zombie_normal_down = new Animation(Assets.zombie_normal_down, 100);
                zombie_normal_left = new Animation(Assets.zombie_normal_left, 100);
                
                zombie_normal_up_congelado = new Animation(Assets.zombie_normal_up_congelado, 100);
                zombie_normal_right_congelado  = new Animation(Assets.zombie_normal_right_congelado, 100);
                zombie_normal_down_congelado  = new Animation(Assets.zombie_normal_down_congelado, 100);
                zombie_normal_left_congelado  = new Animation(Assets.zombie_normal_left_congelado, 100);
                break;
            case 2:
                zombie_flash_up = new Animation(Assets.zombie_flash_up, 100);
                zombie_flash_right = new Animation(Assets.zombie_flash_right, 100);
                zombie_flash_down = new Animation(Assets.zombie_flash_down, 100);
                zombie_flash_left = new Animation(Assets.zombie_flash_left, 100);
                
                zombie_flash_up_congelado  = new Animation(Assets.zombie_flash_up_congelado , 100);
                zombie_flash_right_congelado  = new Animation(Assets.zombie_flash_right_congelado , 100);
                zombie_flash_down_congelado  = new Animation(Assets.zombie_flash_down_congelado , 100);
                zombie_flash_left_congelado  = new Animation(Assets.zombie_flash_left_congelado , 100);
                break;
            case 3:
                zombie_mamado_up = new Animation(Assets.zombie_mamado_up, 100);
                zombie_mamado_right = new Animation(Assets.zombie_mamado_right, 100);
                zombie_mamado_down = new Animation(Assets.zombie_mamado_down, 100);
                zombie_mamado_left = new Animation(Assets.zombie_mamado_left, 100);
                
                zombie_mamado_up_congelado  = new Animation(Assets.zombie_mamado_up_congelado , 100);
                zombie_mamado_right_congelado  = new Animation(Assets.zombie_mamado_right_congelado , 100);
                zombie_mamado_down_congelado  = new Animation(Assets.zombie_mamado_down_congelado , 100);
                zombie_mamado_left_congelado  = new Animation(Assets.zombie_mamado_left_congelado , 100);
                break;
            case 4:
                zombie_kamikaze_up = new Animation(Assets.zombie_kamikaze_up, 100);
                zombie_kamikaze_right = new Animation(Assets.zombie_kamikaze_right, 100);
                zombie_kamikaze_down = new Animation(Assets.zombie_kamikaze_down, 100);
                zombie_kamikaze_left = new Animation(Assets.zombie_kamikaze_left, 100);
                
                zombie_kamikaze_up_congelado  = new Animation(Assets.zombie_kamikaze_up_congelado , 100);
                zombie_kamikaze_right_congelado  = new Animation(Assets.zombie_kamikaze_right_congelado , 100);
                zombie_kamikaze_down_congelado  = new Animation(Assets.zombie_kamikaze_down_congelado , 100);
                zombie_kamikaze_left_congelado  = new Animation(Assets.zombie_kamikaze_left_congelado , 100);
                break;
            default:
                break;
        }
    }

    @Override
    public void tick() {
        //control de ataque de zombie
        if(bAtaque == false){
            contAtaque++;
            if(contAtaque >= 75 ){
                bAtaque = true;
                contAtaque = 0;
            }
        }
        
        //System.out.println(congelado);
        if(congelado){
            contCongelado++;
            if(contCongelado >= ticksCongelado ){
                //System.out.println("lol");
                congelado = false;
                contCongelado = 0;
            }
        }
        else{

            if(ContTicks == 30){
                ContTicks=0;
                bpath = false;
                path.clear();
            }
            ContTicks++;

            //significa que va directo por la torre
            if(objetivo == 0){
                ObjetivoTorreNuevo();
                //ObjetivoTorre();
            }

            //el zombie quiere ir por los supervivientes
            else{
                ObjetivoSupervivientes();
            }

            if( !(this.intersects(game.getTower()) || this.intersects(game.getPlayer()) || this.intersects(game.getRambo()) || this.intersects(game.getRocky()) )  ){
                // sacar los componentes de x y y con formulaso para movimiento
                ComponentesMovimiento();
                //movimiento de los zombies
                movimiento();  
            }
            
            contTicks++;
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
        grados = Math.toDegrees( Math.acos( dx / distance ) ) ;
        
        tipoZombieAnimationTick();
        
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
        }else{
            if(my < py){
                this.cuadrante = 2;
            }
            else{
                this.cuadrante = 3;
            }           
        }
    }
    
    public void buscaEnArreglos(ArrayList A1, ArrayList A2){
        //System.out.println("entrada numero 1    " + cont );
        //System.out.println(xgoal + "    " + ygoal);
        if (cont < A1.size() - 3 ){
            cont = cont + (int) (Math.random() * 3)+1;
            xgoal = (int) A1.get(cont);
            ygoal = (int) A2.get(cont);
            cont = (((cont / 3) + 1) * 3) - 1;
            //System.out.println(xgoal + "        " + ygoal);
        }
        else{
            //System.out.println(cont);
            if(cont == A1.size() - 3){
                //System.out.println("here");
                cont++;
                xgoal = (int) A1.get(cont);
                ygoal = (int) A2.get(cont);
            }
            else{
                //System.out.println(goalp);
                cont++;
                xgoal = (int) A1.get(cont);
                ygoal = (int) A2.get(cont);
            }
            
            if(cont == A1.size() - 1 ){
                cont = -1;
            }
        }
    }
    
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        
        return Math.sqrt(deltax2 + deltay2);
    }
    
    public int ClosestNode(){
        int temp = closestNode;
        Iterator itr = game.getFloyd().getNodos().iterator();
        while(itr.hasNext()){
            Node nod = (Node) itr.next();
            if(nod.getPiso() == this.getPiso()){
                if( DistanciaDosPuntos(this.getX() + (this.getWidth() / 2) , nod.getX(), this.getY() + (this.getHeight() / 2) ,nod.getY()) < DistanciaDosPuntos(this.getX() + (this.getWidth() / 2),game.getFloyd().getNodos().get(temp).getX(), this.getY() + (this.getHeight() / 2),game.getFloyd().getNodos().get(temp).getY())){
                    temp = nod.getId();
                }
            }
        }
        return temp;
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
    
    public void ObjetivoTorre(){
            //System.out.println("por torre");
            if(!goalb){
                // escojer path cuando esta en el primer piso
                switch (piso) {
                    case 1:
                        // escojer escalera por la cual subira de nivel
                        if(!goalb && cont == -1) {
                            //random de 1 a 5
                            goalp = (int) (Math.random() * 5)+1;

                            //goalp = 1; //para probar con la primera

                            //goalp = 5; //para probar con la primera

                        }   
                        
                        //escalera uno
                        switch (goalp) {
                            case 1:
                                buscaEnArreglos(game.getF1P1X(), game.getF1P1Y());
                                break;
                            case 2:
                                buscaEnArreglos(game.getF1P2X(), game.getF1P2Y());
                                break;
                            case 3:
                                buscaEnArreglos(game.getF1P3X(), game.getF1P3Y());
                                break;
                            case 4:
                                buscaEnArreglos(game.getF1P4X(), game.getF1P4Y());
                                break;
                            case 5:
                                buscaEnArreglos(game.getF1P5X(), game.getF1P5Y());
                                break;
                            default:
                                break;
                        }
                        break;
                        
                    case 2:
                        //System.out.println("wauttt");
                        //si vienen de la escalera 2 va a la 6 directo
                        switch (goalp) {
                            case 2:
                                //System.out.println("simon");
                                buscaEnArreglos(game.getF2P62X(), game.getF2P62Y());
                                simon = 1;
                                break;
                            case 5:
                                buscaEnArreglos(game.getF2P75X(), game.getF2P75Y());
                                simon = 2;
                                break;
                            default:
                                //System.out.println(simon);
                                // random para escojer puerta
                                if(cont == -1) {
                                    //random de 1 a 5
                                    simon = (int) (Math.random() * 2)+1;
                                    //simon = 1; //para probar con la primera
                                }
                                // ir a la puerta 6
                                if(simon == 1){
                                    buscaEnArreglos(game.getF2P63X(), game.getF2P63Y());
                                }

                                //ir a la puerta 7
                                else{
                                    buscaEnArreglos(game.getF2P73X(), game.getF2P73Y());
                                }
                                break;
                        }
                        break;
                    
                    case 3:
                        xgoal = game.getTower().getX();
                        ygoal = game.getTower().getY();
                        //System.out.println("lol");
                        break;
                    default:
                        break;
                }
                goalb = true;
            }
            
            //cuando llegue a su punto objetivo apaga el boleano de goalb para buscar el siguiente punto objetivo
            if( goalb && getX() + (getWidth() / 2) - 5 <= xgoal && getX() + (getWidth() / 2) + 5 >= xgoal && getY() + (getHeight() / 2) - 5 <= ygoal && getY() + (getHeight() / 2) + 5 >= ygoal){
                goalb = false;
            }
    }
    
    public void ObjetivoTorreNuevo(){
        if(notFinal){
            if(!bpath){
                closestNode = ClosestNode();
                switch (objetivoSuperviviente) {
                    case 0:
                        voy = 28;
                        break;
                    case 1:
                        voy = 27;
                        break;
                    default: 
                        voy = 29;
                        break;
                }
                              
                path.add(closestNode);
                NodoMedio(closestNode,voy);
                bpath = true;
            }
            if(!goalb){
                nextPath = path.get(0);
                xgoal = game.getFloyd().getNodos().get(nextPath).getX() ;
                ygoal = game.getFloyd().getNodos().get(nextPath).getY() ;
                goalb = true;
            }

            if(getX() + (getWidth() / 2) - 5 <= xgoal && getX() + (getWidth() / 2) + 5 >= xgoal && getY() + (getHeight() / 2) - 5 <= ygoal && getY() + (getHeight() / 2) + 5 >= ygoal){
                goalb = false;
                path.remove(0);
            }            

            if(path.isEmpty()){
                if( piso == 3){
                   notFinal = false; 
                }
                else{
                    objetivoSuperviviente = 2;
                    bpath = false;
                }
            }
        }
        else{
            //System.out.println("tranqui hermano");
        }
        
    }
    
    public void ObjetivoSupervivientes(){
            if(!bpath){
                closestNode = ClosestNode();
                // player
                voy = game.getPlayer().ClosestNode(); 
                objetivoSuperviviente = 0;
                if( game.isSwatActivo()){
                    int temp = game.getRambo().ClosestNode();
                    
                    if( game.getFloyd().getMasCortos()[closestNode][temp] < game.getFloyd().getMasCortos()[closestNode][voy] ){
                        voy = temp;
                        objetivoSuperviviente = 1;
                    }
                }
                if( game.isNinjaActivo() ){
                    int temp = game.getRocky().ClosestNode();
                    if( game.getFloyd().getMasCortos()[closestNode][temp] < game.getFloyd().getMasCortos()[closestNode][voy] ){
                        voy = temp;
                        objetivoSuperviviente = 2;
                    }
                }                
                path.add(closestNode);
                NodoMedio(closestNode,voy);
                bpath = true;
            }
            
            if(!goalb){
                nextPath = path.get(0);
                //System.out.println(objetivoSuperviviente);
                switch (objetivoSuperviviente) {
                    case 0:
                        if(DistanciaDosPuntos(x,game.getFloyd().getNodos().get(nextPath).getX(),y,game.getFloyd().getNodos().get(nextPath).getY()) > DistanciaDosPuntos(x,game.getPlayer().getX(),y,game.getPlayer().getY()) && piso == game.getPlayer().getPiso() ){
                            xgoal = game.getPlayer().getX();
                            ygoal = game.getPlayer().getY();
                        }
                        else if(game.getPlayer().ClosestNode() == closestNode){
                            xgoal = game.getPlayer().getX();
                            ygoal = game.getPlayer().getY();
                        }   
                        else{
                            xgoal = game.getFloyd().getNodos().get(nextPath).getX() ;
                            ygoal = game.getFloyd().getNodos().get(nextPath).getY() ;
                        }
                        break;
                    case 1:
                        if(DistanciaDosPuntos(x,game.getFloyd().getNodos().get(nextPath).getX(),y,game.getFloyd().getNodos().get(nextPath).getY()) > DistanciaDosPuntos(x,game.getRambo().getX(),y,game.getRambo().getY()) && piso == game.getRambo().getPiso() ){
                            xgoal = game.getRambo().getX();
                            ygoal = game.getRambo().getY();
                        }
                        else if(game.getRambo().ClosestNode() == closestNode){
                            xgoal = game.getRambo().getX();
                            ygoal = game.getRambo().getY();
                        }   
                        else{
                            xgoal = game.getFloyd().getNodos().get(nextPath).getX() ;
                            ygoal = game.getFloyd().getNodos().get(nextPath).getY() ;
                        }
                        break;
                    default:
                        //System.out.println("aqui");
                        if(DistanciaDosPuntos(x,game.getFloyd().getNodos().get(nextPath).getX(),y,game.getFloyd().getNodos().get(nextPath).getY()) > DistanciaDosPuntos(x,game.getRocky().getX(),y,game.getRocky().getY()) && piso == game.getRocky().getPiso() ){
                            xgoal = game.getRocky().getX();
                            ygoal = game.getRocky().getY();
                        }
                        else if(game.getRocky().ClosestNode() == closestNode){
                            xgoal = game.getRocky().getX();
                            ygoal = game.getRocky().getY();
                        }   
                        else{
                            xgoal = game.getFloyd().getNodos().get(nextPath).getX() ;
                            ygoal = game.getFloyd().getNodos().get(nextPath).getY() ;
                        }
                        break;
                    
                }
                goalb = true;
            }
            
            if(getX() + (getWidth() / 2) - 5 <= xgoal && getX() + (getWidth() / 2) + 5 >= xgoal && getY() + (getHeight() / 2) - 5 <= ygoal && getY() + (getHeight() / 2) + 5 >= ygoal){
                goalb = false;
                path.remove(0);
            }            
            
            if(path.isEmpty()){
                bpath = false;
                goalb = false;
            }
    }
    
    public void movimiento(){
        if(!notFinal){
            cuadrante = 2;
            pixelsy = 0;
            pixelsx = velocity;
        }
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
        
    @Override
    public void render(Graphics g){        
        switch (tipoZombie) {
            case 1: // NORMAL
                if(cuadrante == 1){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }

                }else if(cuadrante == 2 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                        
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }
                    
                }else if(cuadrante == 3){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }   
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    } 
                }else if(cuadrante == 4 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_normal_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_normal_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                    }else{
                        if(pixelsx >= pixelsy){
                           g.drawImage(zombie_normal_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }else{
                            g.drawImage(zombie_normal_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }
                    }
                }
                break;
            case 2: // FLASH
                if(cuadrante == 1){
                     if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }

                }else if(cuadrante == 2 ){ 
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                        
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }
                    
                }else if(cuadrante == 3){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }   
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    } 
                }else if(cuadrante == 4 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_flash_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_flash_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                    }else{
                        if(pixelsx >= pixelsy){
                           g.drawImage(zombie_flash_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }else{
                            g.drawImage(zombie_flash_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }
                    }
                }
                break;
            case 3: // MAMADO
                if(cuadrante == 1){
                     if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }

                }else if(cuadrante == 2 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                        
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }
                    
                }else if(cuadrante == 3){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }   
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    } 
                }else if(cuadrante == 4 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_mamado_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_mamado_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                    }else{
                        if(pixelsx >= pixelsy){
                           g.drawImage(zombie_mamado_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }else{
                            g.drawImage(zombie_mamado_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }
                    }
                }
                break;
            case 4:
                 //Kamikaze
                if(cuadrante == 1){
                     if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }

                }else if(cuadrante == 2 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_up_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                        
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_up.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    }
                    
                }else if(cuadrante == 3){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_left_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }   
                    }else{
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_left.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }
                    } 
                }else if(cuadrante == 4 ){
                    if(isCongelado()){
                        if(pixelsx >= pixelsy){
                            g.drawImage(zombie_kamikaze_right_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        }else{
                             g.drawImage(zombie_kamikaze_down_congelado.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                        } 
                    }else{
                        if(pixelsx >= pixelsy){
                           g.drawImage(zombie_kamikaze_right.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }else{
                            g.drawImage(zombie_kamikaze_down.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                       }
                    }
                }
                break;
            default:
                break;
        }
        //agregar la vida visible de los zombies
        g.setColor(Color.red);
        g.fillRect(getX(), (int)(getY()-getHeight()*.1), (int) (getWidth()*(vidaZ/vidaEntera)),(int) (getHeight()-getHeight()*.9));
    }
    
    
   public void tipoZombieAnimationTick(){
        if(tipoZombie == 1 && cuadrante == 1){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_normal_right_congelado.tick();
                }else{
                    this.zombie_normal_right.tick();
                }
            }else{
                if(isCongelado()){
                    this.zombie_normal_up_congelado.tick();
                }else{
                    this.zombie_normal_up.tick();
                }
                 
            }
        }else if(tipoZombie ==1 &&  cuadrante == 2 ){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_normal_left_congelado.tick();
                }else{
                    this.zombie_normal_left.tick();
                }
            }else{
                if(isCongelado()){
                    this.zombie_normal_up_congelado.tick();
                }else{
                    this.zombie_normal_up.tick();
                }
            }
        }else if(tipoZombie == 1 &&  cuadrante == 3){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_normal_left_congelado.tick();
                }else{
                    this.zombie_normal_left.tick();
                }
            }else{
                if(isCongelado()){
                    this.zombie_normal_down_congelado.tick();
                }else{
                    this.zombie_normal_down.tick();
                }
            }
        }else if(tipoZombie == 1 && cuadrante == 4 ){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_normal_right_congelado.tick();
                }else{
                    this.zombie_normal_right.tick();
                }
            }else{
                if(isCongelado()){
                     this.zombie_normal_down_congelado.tick();
                }else{
                     this.zombie_normal_down.tick();
                }
            }
        }
        
        //Flash
       if(tipoZombie == 2 && cuadrante == 1){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_flash_right_congelado.tick();
                    }else{
                        this.zombie_flash_right.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_flash_up_congelado.tick();
                    }else{
                        this.zombie_flash_up.tick();
                    }

                }
            }else if(tipoZombie ==2 &&  cuadrante == 2 ){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_flash_left_congelado.tick();
                    }else{
                        this.zombie_flash_left.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_flash_up_congelado.tick();
                    }else{
                        this.zombie_flash_up.tick();
                    }
                }
            }else if(tipoZombie == 2 &&  cuadrante == 3){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_flash_left_congelado.tick();
                    }else{
                        this.zombie_flash_left.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_flash_down_congelado.tick();
                    }else{
                        this.zombie_flash_down.tick();
                    }
                }
            }else if(tipoZombie == 2 && cuadrante == 4 ){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_flash_right_congelado.tick();
                    }else{
                        this.zombie_flash_right.tick();
                    }
                }else{
                    if(isCongelado()){
                         this.zombie_flash_down_congelado.tick();
                    }else{
                         this.zombie_flash_down.tick();
                    }
                }
            }
        
        //Mamado
         if(tipoZombie == 3 && cuadrante == 1){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_mamado_right_congelado.tick();
                    }else{
                        this.zombie_mamado_right.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_mamado_up_congelado.tick();
                    }else{
                        this.zombie_mamado_up.tick();
                    }

                }
            }else if(tipoZombie ==3 &&  cuadrante == 2 ){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_mamado_left_congelado.tick();
                    }else{
                        this.zombie_mamado_left.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_mamado_up_congelado.tick();
                    }else{
                        this.zombie_mamado_up.tick();
                    }
                }
            }else if(tipoZombie == 3 &&  cuadrante == 3){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_mamado_left_congelado.tick();
                    }else{
                        this.zombie_mamado_left.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_mamado_down_congelado.tick();
                    }else{
                        this.zombie_mamado_down.tick();
                    }
                }
            }else if(tipoZombie == 3 && cuadrante == 4 ){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_mamado_right_congelado.tick();
                    }else{
                        this.zombie_mamado_right.tick();
                    }
                }else{
                    if(isCongelado()){
                         this.zombie_mamado_down_congelado.tick();
                    }else{
                         this.zombie_mamado_down.tick();
                    }
                }
            }
        
        //Kamikaze
       if(tipoZombie == 4 && cuadrante == 1){
            if(pixelsx >= pixelsy){
                if(isCongelado()){
                    this.zombie_kamikaze_right_congelado.tick();
                    }else{
                        this.zombie_kamikaze_right.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_kamikaze_up_congelado.tick();
                    }else{
                        this.zombie_kamikaze_up.tick();
                    }

                }
            }else if(tipoZombie == 4 &&  cuadrante == 2 ){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_kamikaze_left_congelado.tick();
                    }else{
                        this.zombie_kamikaze_left.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_kamikaze_up_congelado.tick();
                    }else{
                        this.zombie_kamikaze_up.tick();
                    }
                }
            }else if(tipoZombie == 4 &&  cuadrante == 3){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_kamikaze_left_congelado.tick();
                    }else{
                        this.zombie_kamikaze_left.tick();
                    }
                }else{
                    if(isCongelado()){
                        this.zombie_kamikaze_down_congelado.tick();
                    }else{
                        this.zombie_kamikaze_down.tick();
                    }
                }
            }else if(tipoZombie == 4 && cuadrante == 4 ){
                if(pixelsx >= pixelsy){
                    if(isCongelado()){
                        this.zombie_kamikaze_right_congelado.tick();
                    }else{
                        this.zombie_kamikaze_right.tick();
                    }
                }else{
                    if(isCongelado()){
                         this.zombie_kamikaze_down_congelado.tick();
                    }else{
                         this.zombie_kamikaze_down.tick();
                    }
                }
            }
        
    }
    
    // GETTERS AND SETTERS ============================================================
    


    public int getTicksCongelado() {
        return ticksCongelado;
    }

    public void setTicksCongelado(int ticksCongelado) {
        this.ticksCongelado = ticksCongelado;
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
    
    /**
     *
     * @return
     */
    @Override
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }

    /**
     *
     * @return
     */
    public double getVidaZ() {
        return vidaZ;
    }

    /**
     *
     * @param vidaZ
     */
    public void setVidaZ(double vidaZ) {
        this.vidaZ = vidaZ;
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

    public int getTipoZombie() {
        return tipoZombie;
    }

    public void setTipoZombie(int tipoZombie) {
        this.tipoZombie = tipoZombie;
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

    public int getSimon() {
        return simon;
    }

    public void setSimon(int simon) {
        this.simon = simon;
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
        return ContTicks;
    }

    public void setContTicks(int ContTicks) {
        this.ContTicks = ContTicks;
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

    public boolean isIntersectTower() {
        return intersectTower;
    }

    public void setIntersectTower(boolean intersectTower) {
        this.intersectTower = intersectTower;
    }

    public boolean isCongelado() {
        return congelado;
    }

    public void setCongelado(boolean congelado) {
        this.congelado = congelado;
    }

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }

    public int getCamino() {
        return camino;
    }

    public void setCamino(int camino) {
        this.camino = camino;
    }

    public int getObjetivoSuperviviente() {
        return objetivoSuperviviente;
    }

    public void setObjetivoSuperviviente(int objetivoSuperviviente) {
        this.objetivoSuperviviente = objetivoSuperviviente;
    }

    public Animation getZombie_normal_up() {
        return zombie_normal_up;
    }

    public void setZombie_normal_up(Animation zombie_normal_up) {
        this.zombie_normal_up = zombie_normal_up;
    }

    public Animation getZombie_normal_right() {
        return zombie_normal_right;
    }

    public void setZombie_normal_right(Animation zombie_normal_right) {
        this.zombie_normal_right = zombie_normal_right;
    }

    public Animation getZombie_normal_down() {
        return zombie_normal_down;
    }

    public void setZombie_normal_down(Animation zombie_normal_down) {
        this.zombie_normal_down = zombie_normal_down;
    }

    public Animation getZombie_normal_left() {
        return zombie_normal_left;
    }

    public void setZombie_normal_left(Animation zombie_normal_left) {
        this.zombie_normal_left = zombie_normal_left;
    }

    public Animation getZombie_flash_up() {
        return zombie_flash_up;
    }

    public void setZombie_flash_up(Animation zombie_flash_up) {
        this.zombie_flash_up = zombie_flash_up;
    }

    public Animation getZombie_flash_right() {
        return zombie_flash_right;
    }

    public void setZombie_flash_right(Animation zombie_flash_right) {
        this.zombie_flash_right = zombie_flash_right;
    }

    public Animation getZombie_flash_down() {
        return zombie_flash_down;
    }

    public void setZombie_flash_down(Animation zombie_flash_down) {
        this.zombie_flash_down = zombie_flash_down;
    }

    public Animation getZombie_flash_left() {
        return zombie_flash_left;
    }

    public void setZombie_flash_left(Animation zombie_flash_left) {
        this.zombie_flash_left = zombie_flash_left;
    }

    public Animation getZombie_mamado_up() {
        return zombie_mamado_up;
    }

    public void setZombie_mamado_up(Animation zombie_mamado_up) {
        this.zombie_mamado_up = zombie_mamado_up;
    }

    public Animation getZombie_mamado_right() {
        return zombie_mamado_right;
    }

    public void setZombie_mamado_right(Animation zombie_mamado_right) {
        this.zombie_mamado_right = zombie_mamado_right;
    }

    public Animation getZombie_mamado_down() {
        return zombie_mamado_down;
    }

    public void setZombie_mamado_down(Animation zombie_mamado_down) {
        this.zombie_mamado_down = zombie_mamado_down;
    }

    public Animation getZombie_mamado_left() {
        return zombie_mamado_left;
    }

    public void setZombie_mamado_left(Animation zombie_mamado_left) {
        this.zombie_mamado_left = zombie_mamado_left;
    }

    public Animation getZombie_kamikaze_up() {
        return zombie_kamikaze_up;
    }

    public void setZombie_kamikaze_up(Animation zombie_kamikaze_up) {
        this.zombie_kamikaze_up = zombie_kamikaze_up;
    }

    public Animation getZombie_kamikaze_right() {
        return zombie_kamikaze_right;
    }

    public void setZombie_kamikaze_right(Animation zombie_kamikaze_right) {
        this.zombie_kamikaze_right = zombie_kamikaze_right;
    }

    public Animation getZombie_kamikaze_down() {
        return zombie_kamikaze_down;
    }

    public void setZombie_kamikaze_down(Animation zombie_kamikaze_down) {
        this.zombie_kamikaze_down = zombie_kamikaze_down;
    }

    public Animation getZombie_kamikaze_left() {
        return zombie_kamikaze_left;
    }

    public void setZombie_kamikaze_left(Animation zombie_kamikaze_left) {
        this.zombie_kamikaze_left = zombie_kamikaze_left;
    }

    public Animation getZombie_normal_up_congelado() {
        return zombie_normal_up_congelado;
    }

    public void setZombie_normal_up_congelado(Animation zombie_normal_up_congelado) {
        this.zombie_normal_up_congelado = zombie_normal_up_congelado;
    }

    public Animation getZombie_normal_right_congelado() {
        return zombie_normal_right_congelado;
    }

    public void setZombie_normal_right_congelado(Animation zombie_normal_right_congelado) {
        this.zombie_normal_right_congelado = zombie_normal_right_congelado;
    }

    public Animation getZombie_normal_down_congelado() {
        return zombie_normal_down_congelado;
    }

    public void setZombie_normal_down_congelado(Animation zombie_normal_down_congelado) {
        this.zombie_normal_down_congelado = zombie_normal_down_congelado;
    }

    public Animation getZombie_normal_left_congelado() {
        return zombie_normal_left_congelado;
    }

    public void setZombie_normal_left_congelado(Animation zombie_normal_left_congelado) {
        this.zombie_normal_left_congelado = zombie_normal_left_congelado;
    }

    public Animation getZombie_flash_up_congelado() {
        return zombie_flash_up_congelado;
    }

    public void setZombie_flash_up_congelado(Animation zombie_flash_up_congelado) {
        this.zombie_flash_up_congelado = zombie_flash_up_congelado;
    }

    public Animation getZombie_flash_right_congelado() {
        return zombie_flash_right_congelado;
    }

    public void setZombie_flash_right_congelado(Animation zombie_flash_right_congelado) {
        this.zombie_flash_right_congelado = zombie_flash_right_congelado;
    }

    public Animation getZombie_flash_down_congelado() {
        return zombie_flash_down_congelado;
    }

    public void setZombie_flash_down_congelado(Animation zombie_flash_down_congelado) {
        this.zombie_flash_down_congelado = zombie_flash_down_congelado;
    }

    public Animation getZombie_flash_left_congelado() {
        return zombie_flash_left_congelado;
    }

    public void setZombie_flash_left_congelado(Animation zombie_flash_left_congelado) {
        this.zombie_flash_left_congelado = zombie_flash_left_congelado;
    }

    public Animation getZombie_mamado_up_congelado() {
        return zombie_mamado_up_congelado;
    }

    public void setZombie_mamado_up_congelado(Animation zombie_mamado_up_congelado) {
        this.zombie_mamado_up_congelado = zombie_mamado_up_congelado;
    }

    public Animation getZombie_mamado_right_congelado() {
        return zombie_mamado_right_congelado;
    }

    public void setZombie_mamado_right_congelado(Animation zombie_mamado_right_congelado) {
        this.zombie_mamado_right_congelado = zombie_mamado_right_congelado;
    }

    public Animation getZombie_mamado_down_congelado() {
        return zombie_mamado_down_congelado;
    }

    public void setZombie_mamado_down_congelado(Animation zombie_mamado_down_congelado) {
        this.zombie_mamado_down_congelado = zombie_mamado_down_congelado;
    }

    public Animation getZombie_mamado_left_congelado() {
        return zombie_mamado_left_congelado;
    }

    public void setZombie_mamado_left_congelado(Animation zombie_mamado_left_congelado) {
        this.zombie_mamado_left_congelado = zombie_mamado_left_congelado;
    }

    public Animation getZombie_kamikaze_up_congelado() {
        return zombie_kamikaze_up_congelado;
    }

    public void setZombie_kamikaze_up_congelado(Animation zombie_kamikaze_up_congelado) {
        this.zombie_kamikaze_up_congelado = zombie_kamikaze_up_congelado;
    }

    public Animation getZombie_kamikaze_right_congelado() {
        return zombie_kamikaze_right_congelado;
    }

    public void setZombie_kamikaze_right_congelado(Animation zombie_kamikaze_right_congelado) {
        this.zombie_kamikaze_right_congelado = zombie_kamikaze_right_congelado;
    }

    public Animation getZombie_kamikaze_down_congelado() {
        return zombie_kamikaze_down_congelado;
    }

    public void setZombie_kamikaze_down_congelado(Animation zombie_kamikaze_down_congelado) {
        this.zombie_kamikaze_down_congelado = zombie_kamikaze_down_congelado;
    }

    public Animation getZombie_kamikaze_left_congelado() {
        return zombie_kamikaze_left_congelado;
    }

    public void setZombie_kamikaze_left_congelado(Animation zombie_kamikaze_left_congelado) {
        this.zombie_kamikaze_left_congelado = zombie_kamikaze_left_congelado;
    }

    public double getGrados() {
        return grados;
    }

    public void setGrados(double grados) {
        this.grados = grados;
    }

    public boolean isNotFinal() {
        return notFinal;
    }

    public void setNotFinal(boolean notFinal) {
        this.notFinal = notFinal;
    }

    public int getContCongelado() {
        return contCongelado;
    }

    public void setContCongelado(int contCongelado) {
        this.contCongelado = contCongelado;
    }

    public boolean isbAtaque() {
        return bAtaque;
    }

    public void setbAtaque(boolean bAtaque) {
        this.bAtaque = bAtaque;
    }

    public int getContAtaque() {
        return contAtaque;
    }

    public void setContAtaque(int contAtaque) {
        this.contAtaque = contAtaque;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
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
    
}