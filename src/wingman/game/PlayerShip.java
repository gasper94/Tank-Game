package wingman.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;
import wingman.modifiers.AbstractGameModifier;
import wingman.modifiers.weapons.Weapon;

/**
 *
 * @author Ulises
 */
public class PlayerShip extends Ship implements Observer {

    /**
     *
     */
    protected int lives;

    /**
     *
     */
    protected int score;

    /**
     *
     */
    protected Point resetPoint;

    /**
     *
     */
    public int respawnCounter;

    /**
     *
     */
    protected int lastFired=0;

    /**
     *
     */
    protected boolean isFiring=false;
    // movement flags
    public int left=0,

    /**
     *
     */
    right=0,

    /**
     *
     */
    up=0,

    /**
     *
     */
    down=0;

    /**
     *
     */
    protected String name;

    /**
     *
     * @param location
     * @param speed
     * @param img
     * @param controls
     * @param name
     */
    public PlayerShip(Point location, Point speed, Image img, int[] controls, String name) {
        super(location,speed,100,img);
        resetPoint = new Point(location);
        this.gunLocation = new Point(18,0);
        
        this.name = name;
        weapon = new Weapon();
        lives = 2;
        health = 100;
        strength = 100;
        score = 0;
        respawnCounter=0;
    }

    /**
     *
     * @param g
     * @param observer
     */
    public void draw(Graphics g, ImageObserver observer) {
    	if(respawnCounter<=0)
    		g.drawImage(img, location.x, location.y, observer);
    	else if(respawnCounter==80){
    		respawnCounter -=1;
    	}
    	else if(respawnCounter<80){
    		if(respawnCounter%2==0) g.drawImage(img, location.x, location.y, observer);
    		respawnCounter -= 1;
    	}
    	else
    		respawnCounter -= 1;
    }
    
    /**
     *
     * @param damageDone
     */
    public void damage(int damageDone){
    	if(respawnCounter<=0)
    		super.damage(damageDone);
    }
    
    /**
     *
     * @param w
     * @param h
     */
    public void update(int w, int h) {
    	if((location.x>0 || right==1) && (location.x<w-width || left==1)){
    		location.x+=(right-left)*speed.x;
    	}
    	if((location.y>0 || down==1) && (location.y<h-height || up==1)){
    		location.y+=(down-up)*speed.x;
    	}
    }
    
    /**
     *
     */
    public void startFiring(){
    	isFiring=true;
    }
    
    /**
     *
     */
    public void stopFiring(){
    	isFiring=false;
    }
    
    /**
     *
     */
    public void fire()
    {
    	if(respawnCounter<=0){
    		weapon.fireWeapon(this);
    		//GameSounds.play("Resources/snd_explosion1.wav");
    	}
    }
    
    /**
     *
     */
    public void die(){
    	this.show=false;
    	TankExplosion explosion = new TankExplosion(new Point(location.x,location.y));
    	lives-=1;
    	if(lives>=0){
    		reset();
    	}
    	else{
    		this.motion.delete(this);
    	}
    }
    
    /**
     *
     */
    public void reset(){
    	this.setLocation(resetPoint);
    	health=strength;
    	respawnCounter=160;
    	this.weapon = new Weapon();
    }
    
    /**
     *
     * @return
     */
    public int getLives(){
    	return this.lives;
    }
    
    /**
     *
     * @return
     */
    public int getScore(){
    	return this.score;
    }
    
    /**
     *
     * @return
     */
    public String getName(){
    	return this.name;
    }
    
    /**
     *
     * @param increment
     */
    public void incrementScore(int increment){
    	score += increment;
    }
    
    /**
     *
     * @return
     */
    public boolean isDead(){
    	if(lives<0 && health<=0)
    		return true;
    	else
    		return false;
    }
    
	public void update(Observable o, Object arg) {
		AbstractGameModifier modifier = (AbstractGameModifier) o;
		modifier.read(this);
	}
}
