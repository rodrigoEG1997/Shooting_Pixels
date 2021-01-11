/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.image.BufferedImage;

/**
 *
<<<<<<< HEAD
 * @author Rodrigo
=======
 * @author alextrujillo
>>>>>>> 6e5bd9b21d82ef6f453b404d67109339029e1de6
 */
public class SpreadSheet {
    private BufferedImage sheet;
    
    public SpreadSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
}
