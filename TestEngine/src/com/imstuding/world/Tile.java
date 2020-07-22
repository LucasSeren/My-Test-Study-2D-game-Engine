/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imstuding.world;

import com.imstuding.main.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author lucas
 */
public class Tile {
    
    public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
    public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
       
    private BufferedImage sprite;
    private int x,y;
    
    public Tile(int x, int y, BufferedImage sprite){
        this.x=x;
        this.y=y;
        this.sprite = sprite;
    }
    
   public void render(Graphics g){
       g.drawImage(sprite,x,y,null);
   }
}
