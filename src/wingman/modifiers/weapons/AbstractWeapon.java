package wingman.modifiers.weapons;

import java.util.Observer;

import wingman.modifiers.AbstractGameModifier;
import wingman.GameWorld;
import wingman.game.Bullet;
import wingman.game.PlayerShip;
import wingman.game.Ship;


/*Weapons are fired by motion controllers on behalf of players or ships
 * They observe motions and are observed by the Game World
 */

/**
 *
 * @author Ulises
 */
public abstract class AbstractWeapon extends AbstractGameModifier {

    /**
     *
     */
    protected Bullet[] bullets;
	boolean friendly;
	int lastFired=0, reloadTime;

    /**
     *
     */
    protected int direction;

    /**
     *
     */
    public int reload = 5;
	
    /**
     *
     */
    public AbstractWeapon(){
	}
	
    /**
     *
     * @param world
     */
    public AbstractWeapon(Observer world){
		super();
		this.addObserver(world);
	}
	
    
    
    /**
     *
     * @param theShip
     */
    
    public void fireWeapon(Ship theShip){
		if(theShip instanceof PlayerShip){
			direction = 1;
		}
		else{
			direction = -1;
		}
	}
	
	/* read is called by Observers when they are notified of a change */

    /**
     *
     * @param theObject
     */
    
	public void read(Object theObject) {
		GameWorld world = (GameWorld) theObject;
		world.addBullet(bullets);	
	}
	
    /**
     *
     */
    public void remove(){
	}
}
