package model.entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Particle {
	 
    private int x;
    private int y;
    private int speedx;
    private int speedy;
    private int size;
    private Color color;
    private float alpha;
    private int life;
    private boolean toggle;
    
    public Particle(int x, int y, int size, int visible){
        this.x = x;
        this.y = y;
        int random = (int)(Math.random() * 4);
        if(random == 0){
            this.speedx = (int) (Math.random()*3);
            this.speedy = (int) (Math.random()*-3);
        }
        else if(random == 1){
        	this.speedx = (int) (Math.random()*-3);
        	this.speedy = (int) (Math.random()*3);
        }else if(random == 2){
        	this.speedx = (int) (Math.random()*-3);
        	this.speedy = (int) (Math.random()*-3);
        }else{
        	this.speedx = (int) (Math.random()*3);
        	this.speedy = (int) (Math.random()*3);
        }
        this.size = size;
        this.alpha = 1f;
        Random rand = new Random();
        	int i = rand.nextInt(3);
        	switch(i){
        	case 0: this.color = Color.BLUE;
        	break;
        	case 1: this.color = new Color(0,127,255);
        	break;
        	case 2: this.color = new Color(124,158,217);
        	break;
        }
        if(visible == 1){
        	toggle = true;
        }
    }
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    public int getDX(){
    	return speedx;
    }
    
    public int getDY(){
    	return speedy;
    }
 
    public boolean update(boolean bool){
    	if(bool){
        x += -speedx;
        y += -speedy;
    	}else{
    	x += speedx;
        y += speedy;  	
        }
        life++;
        if(!toggle){
        alpha=alpha-0.03f;
        }else{
        
        int random = (int)(Math.random() * 4);
        if(random == 0){
        	alpha=alpha-0.01f;
        }else if(random == 1){
        	alpha=alpha-0.02f;
        }else if(random == 2){
        	alpha=alpha-0.03f;
        }else{
        	alpha=alpha-0.005f;
        }
        }
        if(alpha == 0.1f){
        	alpha = 0.0f;
        	return true;
        }
        return false;
    }
    
    public float getAlpha(){
    	return alpha;
    }
    
    public int getLife(){
    	return life;
    }
    
    public void setX(int x){
    	this.x = x;
    }
    
    public void setY(int y){
    	this.y = y;
    }
    
    private AlphaComposite makeComposite(float alpha) {
    	  int type = AlphaComposite.SRC_OVER;
    	  return(AlphaComposite.getInstance(type, alpha));
    	 }
 
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        if(alpha > 0.0f){
        g2.setComposite(makeComposite(alpha));
        g2.setPaint(color);
        } else {
        g2.setPaint(new Color(1,0,0,0.0f));
        }
        g2.fillOval(x-(size/2), y-(size/2), size, size);
        g2.dispose();
    }
}