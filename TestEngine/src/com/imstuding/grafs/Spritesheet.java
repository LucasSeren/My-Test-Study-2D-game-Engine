/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imstuding.grafs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author lucas
 */
public class Spritesheet {
    private BufferedImage spritesheet;
    
    public Spritesheet(String path){
       try{
           spritesheet = ImageIO.read(getClass().getResource(path));
       }
    catch (IOException e){
        e.printStackTrace();
    }
        
    }
    public BufferedImage getSprite(int x,int y, int width, int heigth){
            return spritesheet.getSubimage(x, y, width, heigth);
            
        }
        }
