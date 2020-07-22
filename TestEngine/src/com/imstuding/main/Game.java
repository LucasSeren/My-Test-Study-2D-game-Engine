/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imstuding.main;

import com.imstuding.entities.Entity;
import com.imstuding.entities.Player;
import com.imstuding.grafs.Spritesheet;
import com.imstuding.world.World;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
/**
 *
 * @author lucas
 */
public class Game extends Canvas implements Runnable,KeyListener{
    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 240;
    private final int HEIGHT = 240;
    private final int SCALE = 3;
    
    private BufferedImage image;
private Player player;
    public List<Entity> entities;
    public static Spritesheet spritesheet;
    
    public static World world;
    
    public Game(){ 
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<Entity>();
        spritesheet = new Spritesheet("spritesheet.png");  
         world = new World("map.png");
        player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 16, 16));
        entities.add(player);
    }
    
    public void initFrame (){
        frame = new JFrame("TestEngine");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public synchronized void start (){
        thread = new Thread (this);
        isRunning = true;
        thread.start();
    }
    
     public synchronized void stop (){  
        isRunning = false;
  try{
      thread.join();
      
  } catch (InterruptedException e) {
      e.printStackTrace();
  }
    }
    public static void main (String args[]){
        Game game = new Game();
        game.start();
    }
    
      public void tick(){
        for (int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            if(e instanceof Player){
                
            }
            e.tick();
        }
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        //Game Render
        //Graphics2D g2 = (Graphics2D) g;
        world.render(g);
           for (int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.render(g);
        }
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
        bs.show();
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while(isRunning){
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
           lastTime = now;
           if(delta>= 1) {
               tick();
               render();
               frames++;
               delta--;
           }
           if(System.currentTimeMillis() - timer >= 1000){
               System.out.println("FPS: "+ frames);
               frames = 0;
               timer+=1000;
           }
        }
        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
if(e.getKeyCode() == KeyEvent.VK_RIGHT || 
        e.getKeyCode() == KeyEvent.VK_D){
    //Execute action 
    Player.right = true;
}else if(e.getKeyCode()== KeyEvent.VK_LEFT || 
        e.getKeyCode() == KeyEvent.VK_A){
    Player.left = true;
    }
if(e.getKeyCode() == KeyEvent.VK_UP || 
        e.getKeyCode() == KeyEvent.VK_W){
    Player.up = true;
    //Execute action
}else if(e.getKeyCode()== KeyEvent.VK_DOWN || 
        e.getKeyCode() == KeyEvent.VK_S){
    Player.down = true;
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_RIGHT || 
        e.getKeyCode() == KeyEvent.VK_D){
    //Execute action 
    Player.right = false;
}else if(e.getKeyCode()== KeyEvent.VK_LEFT || 
        e.getKeyCode() == KeyEvent.VK_A){
    Player.left = false;
    }
if(e.getKeyCode() == KeyEvent.VK_UP || 
        e.getKeyCode() == KeyEvent.VK_W){
    Player.up = false;
    //Execute action
}else if(e.getKeyCode()== KeyEvent.VK_DOWN || 
        e.getKeyCode() == KeyEvent.VK_S){
    Player.down = false;
    }
    }
}
