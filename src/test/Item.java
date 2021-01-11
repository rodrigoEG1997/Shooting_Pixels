/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author 
 */
public abstract class Item {

    /**
     *
     */
    protected int x;        // to store x position

    /**
     *
     */
    protected int y;        // to store y position
    private int width;      // to store width   
    private int height;     //to store height
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     * @param width
     * @param height
     */
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get x value
     * @return x 
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     * @return y 
     */
    public int getY() {
        return y;
    }
    
    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    public boolean intersects(Object obj){
        //check if the object is an Item
        return (obj instanceof Item &&
            //return if this rectangle intersects obj rectangle
                this.getBounds().intersects(((Item) obj).getBounds()));        
    }
    
    
    public abstract Rectangle getBounds();

    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
}