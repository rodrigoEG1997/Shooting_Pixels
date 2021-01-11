/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rodrigo
 */
public class ObjetoTienda extends Item {
    private Game game;
    private int numObjeto;
    private boolean disponibles = true;
    public java.awt.Image objeto;
    private int costo;
    private int level;
    private int cantidadReal;
    private int cantidadSubir;
    private int levelMax;
    private int oleadaDisponible;
    private int cantMaxima;
    private String nombre;
    private int precioMejora;
    
    private int rango;
    private int velDisparo;
    private int vida;
    private int velAtaque;
    private int velMover;
    private int cantBalas;
    
    private int subirRango;
    private int subirVelDisparo;
    private int subirVida;
    private int subirVelAtaque;
    private int subirVelMover;
    private int subirCantBalas;
    
    
    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param vida
     * @param game
     */
    public ObjetoTienda(int x, int y, int width, int height, int numObjeto,boolean disponible, java.awt.Image image, int costo, String nombre,int level,int cantidadReal,int precioMejora,int levelMax,int cantidadSubir,  Game game){
        super(x,y,width,height);
        this.game = game;
        this.numObjeto = numObjeto;
        this.disponibles = disponibles;
        this.objeto = image;
        this.costo = costo;
        this.nombre = nombre;
        this.level = level;
        this.cantidadReal = cantidadReal;
        this.precioMejora = precioMejora;
        this.levelMax = levelMax;
        this.cantidadSubir = cantidadSubir;
        
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

    public int getNumObjeto() {
        return numObjeto;
    }

    public void setNumObjeto(int numObjeto) {
        this.numObjeto = numObjeto;
    }

    public boolean isDisponibles() {
        return disponibles;
    }

    public void setDisponibles(boolean disponibles) {
        this.disponibles = disponibles;
    }

    public Image getObjeto() {
        return objeto;
    }

    public void setObjeto(Image objeto) {
        this.objeto = objeto;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCantidadReal() {
        return cantidadReal;
    }

    public void setCantidadReal(int cantidadReal) {
        this.cantidadReal = cantidadReal;
    }

    public int getCantidadSubir() {
        return cantidadSubir;
    }

    public void setCantidadSubir(int cantidadSubir) {
        this.cantidadSubir = cantidadSubir;
    }

    public int getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(int levelMax) {
        this.levelMax = levelMax;
    }

    public int getOleadaDisponible() {
        return oleadaDisponible;
    }

    public void setOleadaDisponible(int oleadaDisponible) {
        this.oleadaDisponible = oleadaDisponible;
    }

    public int getCantMaxima() {
        return cantMaxima;
    }

    public void setCantMaxima(int cantMaxima) {
        this.cantMaxima = cantMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioMejora() {
        return precioMejora;
    }

    public void setPrecioMejora(int precioMejora) {
        this.precioMejora = precioMejora;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    

    public int getVelDisparo() {
        return velDisparo;
    }

    public void setVelDisparo(int velDisparo) {
        this.velDisparo = velDisparo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelAtaque() {
        return velAtaque;
    }

    public void setVelAtaque(int velAtaque) {
        this.velAtaque = velAtaque;
    }

    public int getVelMover() {
        return velMover;
    }

    public void setVelMover(int velMover) {
        this.velMover = velMover;
    }

    public int getCantBalas() {
        return cantBalas;
    }

    public void setCantBalas(int cantBalas) {
        this.cantBalas = cantBalas;
    }

    public int getSubirRango() {
        return subirRango;
    }

    public void setSubirRango(int subirRango) {
        this.subirRango = subirRango;
    }

    public int getSubirVelDisparo() {
        return subirVelDisparo;
    }

    public void setSubirVelDisparo(int subirVelDisparo) {
        this.subirVelDisparo = subirVelDisparo;
    }

    public int getSubirVida() {
        return subirVida;
    }

    public void setSubirVida(int subirVida) {
        this.subirVida = subirVida;
    }

    public int getSubirVelAtaque() {
        return subirVelAtaque;
    }

    public void setSubirVelAtaque(int subirVelAtaque) {
        this.subirVelAtaque = subirVelAtaque;
    }

    public int getSubirVelMover() {
        return subirVelMover;
    }

    public void setSubirVelMover(int subirVelMover) {
        this.subirVelMover = subirVelMover;
    }

    public int getSubirCantBalas() {
        return subirCantBalas;
    }

    public void setSubirCantBalas(int subirCantBalas) {
        this.subirCantBalas = subirCantBalas;
    }

    
    
    

    
    
    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g){
        
    }
    
    
    
}
