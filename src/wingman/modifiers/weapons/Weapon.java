package wingman.modifiers.weapons;

import java.awt.Point;

import wingman.game.Bullet;
import wingman.game.Ship;
import wingman.modifiers.motions.SimpleMotion;

/**
 *
 * @author Ulises
 */
public class Weapon extends AbstractWeapon {
	int strength;
	
    /**
     *
     */
    public Weapon(){
		this(5,10);
	}
	
    /**
     *
     * @param reload
     */
    public Weapon(int reload){
		this(5,reload);
	}
	
    /**
     *
     * @param strength
     * @param reload
     */
    public Weapon(int strength, int reload){
		super();
		this.reload = reload;
		this.strength = strength;
	}
	
    /**
     *
     * @param theShip
     */
    @Override
	public void fireWeapon(Ship theShip) {
		super.fireWeapon(theShip);
		Point location = theShip.getLocationPoint();
		Point offset = theShip.getGunLocation();
		location.x+=offset.x;
		location.y+=offset.y;
		Point speed = new Point(0,-15*direction);
		
		Bullet bullet = new Bullet(location, speed, strength, new SimpleMotion(), theShip);
		bullets = new Bullet[1];
		bullets[0] = bullet;
				
		this.setChanged();
		
		this.notifyObservers();
	}

}
