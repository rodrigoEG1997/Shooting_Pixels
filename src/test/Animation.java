/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.image.BufferedImage;

/**
 *
 * @author alextrujillo
 */
public class Animation {
    private int speed;
    private int index;
    private long lastTime;
    private long timer;
    private BufferedImage[] frames;
    
    /**
     *
     * @param frames
     * @param speed
     */
    public Animation(BufferedImage[] frames,int speed){
        this.frames= frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    /**
     *
     */
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed ){
            index ++;
            timer = 0;
            
            if(index >= frames.length){
            index = 0;
            }
        }
    
    }

    /**
     *
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return
     */
    public long getLastTime() {
        return lastTime;
    }

    /**
     *
     * @param lastTime
     */
    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     *
     * @return
     */
    public long getTimer() {
        return timer;
    }

    /**
     *
     * @param timer
     */
    public void setTimer(long timer) {
        this.timer = timer;
    }

    /**
     *
     * @return
     */
    public BufferedImage[] getFrames() {
        return frames;
    }

    /**
     *
     * @param frames
     */
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
    }
    
    /**
     *
     * @return
     */
    public BufferedImage getCurrentFrame() {
            return frames[index];
        }    
    }
