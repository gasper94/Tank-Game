/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.awt.Point;

import wingman.game.Bullet;
import wingman.game.Ship;
import wingman.modifiers.weapons.AbstractWeapon;

/**
 *
 * @author Ulises
 */
public class TankDoubleShot extends AbstractWeapon {

    public TankDoubleShot(){
		super(TankWorld.getInstance());
	}
    
    /**
     *
     * @param theTank
     */
    public void fireWeapon(Ship theTank) {
		super.fireWeapon(theTank);
		Point location = theTank.getLocationPoint();
		Point offset = theTank.getGunLocation();
		location.x+=offset.x;
		location.y+=offset.y;
		Point speed = new Point(0,-15*direction);
		int strength = 7;
		reload = 15;
		
		bullets = new Bullet[2];
		bullets[0] = new TankBullet(location, speed, strength, -5, (Tank) theTank);
		bullets[1] = new TankBullet(location, speed, strength, 5, (Tank) theTank);		
		this.setChanged();
		this.notifyObservers();
	}
}
