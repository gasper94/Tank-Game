package wingman.game;

import java.awt.Point;
import tank.TankWorld;

//import wingman.WingmanWorld;
import wingman.modifiers.motions.MotionController;

/*Bullets fired by player and enemy weapons*/

/**
 *
 * @author Ulises
 */
public class Bullet extends MoveableObject {

    /**
     *
     */
    protected PlayerShip owner;
	boolean friendly;
	
    /**
     *
     * @param location
     * @param speed
     * @param strength
     * @param motion
     * @param owner
     */
    public Bullet(Point location, Point speed, int strength, MotionController motion, GameObject owner){
		super(location, speed, TankWorld.sprites.get("bullet"));
		this.strength=strength;
		if(owner instanceof PlayerShip){
			this.owner = (PlayerShip) owner;
			this.friendly=true;
			this.setImage(TankWorld.sprites.get("bullet"));
		}
		this.motion = motion;
		motion.addObserver(this);
	}
	
    /**
     *
     * @return
     */
    public PlayerShip getOwner(){
		return owner;
	}
	
    /**
     *
     * @return
     */
    public boolean isFriendly(){
		if(friendly){
			return true;
		}
		return false;
	}
}
