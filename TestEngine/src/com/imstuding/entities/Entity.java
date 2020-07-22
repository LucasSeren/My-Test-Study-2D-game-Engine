/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imstuding.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author lucas
 */
public class Entity {
  protected int x;
    protected int y;
     protected int width;
      protected int heigth;
      
      private BufferedImage sprite;
      
      public  Entity (int x,int y,int width,int height,BufferedImage sprite){
          this.x = x;
          this.y = y;
          this.width = width;
          this.heigth = height;
          this.sprite=sprite;
      }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }
    public void tick(){
     
    }
    
    public void render (Graphics g){
        g.drawImage(sprite,this.getX(),this.getY(),null);
    }
}
