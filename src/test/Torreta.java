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

/**
 *
 * @author ALBER
 */
public class Torreta extends Item {
    private Game game;
    private int objZom;
    private boolean objetivoZombie;
    private int objX;
    private int objY;
    private int rangoDisparo;
    private int piso = 3;
    private int dano = 50;
    private int contDisparo = 0;
    private int cantBalas;  //this
    private int velocidadDisparo; //this
    
     //To know the direction
    private double grados;
    private int cuadrante=1;
    int pixelsx;
    int pixelsy;
    private boolean shooting;
    
    private Animation torretaUp = new Animation(Assets.torretaUp_2, 100); 
    private Animation torreta45 = new Animation(Assets.torreta45_2, 100);
    private Animation torretaRight = new Animation(Assets.torretaRight_2, 100);
    private Animation torreta135 = new Animation(Assets.torreta135_2, 100);
    private Animation torretaDown = new Animation(Assets.torretaDown_2, 100);
    private Animation torreta225 = new Animation(Assets.torreta225_2, 100);
    private Animation torretaLeft = new Animation(Assets.torretaLeft_2, 100);
    private Animation torreta315 = new Animation(Assets.torreta315_2, 100);
    
    private Animation torretaUp_disparo = new Animation(Assets.torretaUp_disparo_2, 100);
    private Animation torreta45_disparo = new Animation(Assets.torreta45_disparo_2, 100);
    private Animation torretaRight_disparo = new Animation(Assets.torretaRight_disparo_2, 100); 
    private Animation torreta135_disparo = new Animation(Assets.torreta135_disparo_2, 100);
    private Animation torretaDown_disparo = new Animation(Assets.torretaDown_disparo_2, 100);
    private Animation torreta225_disparo = new Animation(Assets.torreta225_disparo_2, 100);
    private Animation torretaLeft_disparo = new Animation(Assets.torretaLeft_disparo_2, 100);
    private Animation torreta315_disparo = new Animation(Assets.torreta315_disparo_2, 100);
    
    public Torreta(int x, int y, int width, int height, int piso, int cantBalas,int rangoDisparo, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.piso = piso;
        this.cantBalas = cantBalas;
        this.rangoDisparo = rangoDisparo;
        cuadrante = 1;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCantBalas() {
        return cantBalas;
    }

    public void setCantBalas(int cantBalas) {
        this.cantBalas = cantBalas;
    }

    
    
    
    @Override
    public Rectangle getBounds(){
        return new Rectangle (getX(), getY(),getWidth(), getHeight());
    }
    
    @Override
    public void tick() {
        objZom = -1;
        for(int i = 0;  i < game.getZombies().size(); i++){
            if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) < rangoDisparo && game.getZombies().get(i).getPiso() == piso ){
                if(objZom == -1){
                    objZom = i;
                    objetivoZombie = true;
                    objX = game.getZombies().get(i).getX() + (game.getZombies().get(i).getWidth() / 2) ;
                    objY = game.getZombies().get(i).getY() + (game.getZombies().get(i).getHeight() / 2);
                }else{
                    if( DistanciaDosPuntos(game.getZombies().get(i).getX() , x + this.getWidth() / 2, game.getZombies().get(i).getY(),y + this.getHeight() ) <  DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() )){
                        objZom = i;
                        objetivoZombie = true;
                        objX = game.getZombies().get(i).getX() + (game.getZombies().get(i).getWidth() / 2) ;
                        objY = game.getZombies().get(i).getY() + (game.getZombies().get(i).getHeight() / 2);
                        shooting = true;
                    }else{
                    shooting = false;
                    }
                }
            }
        }
        
        if( objZom != -1 ){
            if( DistanciaDosPuntos(game.getZombies().get(objZom).getX() , x + this.getWidth() / 2, game.getZombies().get(objZom).getY(),y + this.getHeight() ) > rangoDisparo || game.getZombies().get(objZom).getPiso() != piso ){
                objetivoZombie = false;
                objZom = -1;
            }
        }
        
        //la torreta va a  le va a disparar a un zombie
        if(objetivoZombie && contDisparo >= 10 ){
            game.getBullets().add( new Bullet(x + (this.getWidth() / 2), y + (this.getHeight() / 2), 5 , 5 , 5 ,objX,objY,piso,  2,dano,game ) );
            cantBalas--;
            objetivoZombie = false;
            contDisparo = 0;
        }
        contDisparo++;
    }


    @Override
    public void render(Graphics g) {
        Font leter = new Font ("Arial", 1, 12);
                g.setFont (leter);
                g.setColor(Color.BLACK);
                //dinero player
                g.drawString ("" + cantBalas , (int) (getX()) , (int) (getY()-(getHeight()*.5)));
         switch (cuadrante) {
            case 1:
                if(shooting){
                    if(grados >0 && grados <= 22.5){
                        g.drawImage(torretaRight_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else if (grados > 22.5 && grados <= 67.5){
                        g.drawImage(torreta45_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else{
                        g.drawImage(torretaUp_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                
                }else{
                    if(grados >0 && grados <= 22.5){
                        g.drawImage(torretaRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else if (grados > 22.5 && grados <= 67.5){
                        g.drawImage(torreta45.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else{
                        g.drawImage(torretaUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
                 break;
            case 2:
                if(shooting){
                    if(grados >0 && grados <= 22.5){
                        g.drawImage(torretaUp_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else if (grados > 22.5 && grados <= 67.5){
                        g.drawImage(torreta135_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else{
                        g.drawImage(torretaLeft_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                
                }else{
                    if(grados >0 && grados <= 22.5){
                        g.drawImage(torretaUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else if (grados > 22.5 && grados <= 67.5){
                        g.drawImage(torreta135.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else{
                        g.drawImage(torretaLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
                break;
            case 3:
                if(shooting){
                    if(grados >0 && grados <= 22.5){
                        g.drawImage(torretaLeft_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else if (grados > 22.5 && grados <= 67.5){
                        g.drawImage(torreta225_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else{
                        g.drawImage(torretaDown_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                
                }else{
                
                    if(grados >0 && grados <= 22.5){
                        g.drawImage(torretaLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }else if (grados > 22.5 && grados <= 67.5){
                        g.drawImage(torreta225.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    } else{
                        g.drawImage(torretaDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                    }
                }
                break;
            case 4:
                 if(shooting){
                    if(grados >0 && grados <= 22.5){
                       g.drawImage(torretaDown_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }else if (grados > 22.5 && grados <= 67.5){
                       g.drawImage(torreta315_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   } else{
                       g.drawImage(torretaRight_disparo.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }
                }else{
                    if(grados >0 && grados <= 22.5){
                       g.drawImage(torretaDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }else if (grados > 22.5 && grados <= 67.5){
                       g.drawImage(torreta315.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   } else{
                       g.drawImage(torretaRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                   }
                 }
                break;
            default:
                break;
        }
    }
    
    public double DistanciaDosPuntos(int x1,int x2, int y1, int y2){
        double deltax = x2 - x1;
        double deltay = y2 - y1;
        
        double deltax2 = deltax * deltax;
        double deltay2 = deltay * deltay;
        double distancia = Math.sqrt(deltax2 + deltay2);
        updateAndTick(deltax ,distancia);
        return distancia;
    }
    
    public void updateAndTick(double deltax, double distancia){
        int px = getX()+ (getWidth()/2) ;
        int py = getY()+ (getHeight()/2);
        if(objX > x){
            //lado derecho del personaje
            if(objY < y){
                this.cuadrante = 1;
            }
            else{
                this.cuadrante = 4;
            }
        }
        else{
            if(objY < y){
                this.cuadrante = 2;
            }
            else{
                this.cuadrante = 3;
            }           
        }
        grados = Math.toDegrees( Math.acos( deltax / distancia ) ) ;
        // 1 == velocity (checar clase zombies)
        double pix = 1 * Math.cos(Math.toRadians(grados));
        pixelsx = (int) pix;
        if(pix - pixelsx >= .5 ){
            pixelsx++;
        }
        pix = 1 * Math.sin(Math.toRadians(grados));
        pixelsy = (int) pix;
        if(pix - pixelsy >= .5){
            pixelsy++;
        }
        
         switch (cuadrante) {
            case 1:
                if(shooting){
                     if(grados >0 && grados <= 22.5){
                        this.torretaRight_disparo.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        this.torreta45_disparo.tick();
                    } else{
                        this.torretaUp_disparo.tick();
                    }                
                }else{
                    if(grados >0 && grados <= 22.5){
                        this.torretaRight.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        this.torreta45.tick();
                    } else{
                        this.torretaUp.tick();
                    }
                }
                break;
            case 2:
                if(shooting){
                    if(grados >0 && grados <= 22.5){
                        this.torretaUp_disparo.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        torreta135_disparo.tick();
                    } else{
                        this.torretaLeft_disparo.tick();
                    }
                }else{
                    if(grados >0 && grados <= 22.5){
                        this.torretaUp.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        torreta135.tick();
                    } else{
                        this.torretaLeft.tick();
                    }
     
                }
                
                break;
            case 3:
                if(shooting){
                    if(grados >0 && grados <= 22.5){
                        this.torretaLeft_disparo.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        this.torreta225_disparo.tick();
                    }else{
                        this.torretaDown_disparo.tick();
                    }
                }else{
                    if(grados >0 && grados <= 22.5){
                        this.torretaLeft.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        this.torreta225.tick();
                    }else{
                        this.torretaDown.tick();
                    }
                }            
                break;
            case 4:
                if(shooting){
                    if(grados >0 && grados <= 22.5){
                       this.torretaDown_disparo.tick();
                   }else if (grados > 22.5 && grados <= 67.5){
                       this.torreta315_disparo.tick();
                   } else{
                       this.torretaRight_disparo.tick();
                   }
                }else{
                     if(grados >0 && grados <= 22.5){
                        this.torretaDown.tick();
                    }else if (grados > 22.5 && grados <= 67.5){
                        this.torreta315.tick();
                    } else{
                        this.torretaRight.tick();
                    }
                }
                break;
            default:
                break;
        }
         shooting = false;
        //System.out.println("Grados:" + grados + " Cuadrante:" + cuadrante);
    }
    
}
