/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author 
 */
public class Display {
    
    private JFrame jframe; //app class
    private Canvas canvas; //dsplay images
    private String title; //titulo
    private int width; //  ancho
    private int height; //alto


    /**
     *initializes the values for the application game
     *@param title to display the title of the window
     *@param width to set the width
     *@param height to set the height
     */

    public Display(String title, int width, int height){
        this.title=title;
        this.width=width;
        this.height=height;
        createDisplay();
    }

    /**
     *
     */
    public void createDisplay(){
        jframe = new JFrame(title); //create de app windows
        jframe.setSize(width,height); //set de size of the windows

        //setting not resizable, visible and possible to close
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        //creating the canvas to paint and setting	size
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        //adding the canvas to the app window and packing to
        //get the right dimensions
        jframe.add(canvas);
        jframe.pack();
    }
    
    // to get canvas on the game

    /**
     *
     * @return
     */
    public Canvas getCanvas(){
        return canvas;
    }

    /**
     *
     * @return
     */
    public JFrame getJframe(){
        return jframe;
    }

}