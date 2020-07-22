/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imstuding.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author lucas
 */
public class World {

private Tile[] tiles;
public static int WIDTH,HEIGTH;
    
    public World(String path) {
        try {
            BufferedImage map;
            map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth()*map.getHeight()];
            WIDTH = map.getWidth();
            HEIGTH= map.getHeight();
            tiles = new Tile[map.getWidth()* map.getHeight()];
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
            for(int xx =0; xx< map.getWidth(); xx++){
                for(int yy =0;yy< map.getHeight();yy++){
                    int pixelCurrent = pixels[xx +(yy*map.getWidth())];
                    
                    if(pixelCurrent == 0xFF000000){
                        //floor
                        tiles[xx+(yy * WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
                    }else if(pixelCurrent== 0xFFFFFFFF) {
                        //wall
                         tiles[xx+(yy * WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_WALL);
                    }else if(pixelCurrent == 0xFF639bff){
                         tiles[xx+(yy * WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
                        //player
                    }else{
                         tiles[xx+(yy * WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
                    }
                }
            }
        } catch (IOException e) {
        }
    }
    public void  render(Graphics g){
        for(int xx = 0; xx< WIDTH; xx++){
            for(int yy = 0; yy <HEIGTH; yy++){
                Tile tile = tiles[xx + (yy* WIDTH)];
                tile.render(g);
            }
        }
    }
    
}
