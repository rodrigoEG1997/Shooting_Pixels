/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Toolkit;

/**
 *
<<<<<<< HEAD:src/shootingpixels/ShootingPixels.java
 * @author alextrujillo
 * @author Esthephany
=======
 * @author 
>>>>>>> 1e94bc5c5e9117727300d8f5a671a8885d6f5c02:src/test/Test.java
 */
public class Test {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        Toolkit tk = Toolkit.getDefaultToolkit();
        
        int x = (int) tk.getScreenSize().getWidth();
        int y = (int) tk.getScreenSize().getHeight();

        x =(int) ((int) x * .75);
        y = (int) ((int) y * .9);
                System.out.println(x + "        " + y);
       // creating a Game object
        Game g = new Game("Juego", /*1600*/x,/*900*/ y);
        //	initializing	the	game
        g.start();
    }
}
