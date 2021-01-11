/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase nodo que guarda un x, y, piso, id
 * @author ALBER
 */
public class Node {
    private int x;
    private int y;
    private int id;
    private int piso;
    private Game game;
            
    /**
     * Inicializacion 
     * @param x
     * @param y
     * @param id
     * @param piso
     * @param game 
     */
    public Node(int x, int y, int id,int piso,Game game){
        this.x = x;
        this.y = y;
        this.id = id;
        this.game = game;
        this.piso = piso;
    }
    
    /**
     * devolver x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Cambiar valor de x
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Devolver valor de y
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Cambiar valor de y
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Devolver valor de id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Cambiar valor de id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolver valor de game
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Cambiar valor de game
     * @param game 
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Devolver valor de piso
     * @return piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * Cambiar valor de piso
     * @param piso 
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }
    
    /**
     * Funcion para desplegar en la pantalla
     * @param g 
     */
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), 5, 5);
    }
    
}
