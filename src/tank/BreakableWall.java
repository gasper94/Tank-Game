/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import wingman.game.BackgroundObject;
import wingman.game.GameObject;

/**
 *
 * @author Ulises
 */
public class BreakableWall extends BackgroundObject {

    int timer = 400;

    /**
     *
     * @param x
     * @param y
     */
    public BreakableWall(int x, int y) {
        super(new Point(x * 32, y * 32), new Point(0, 0), TankWorld.sprites.get("wall2"));
    }	

    /**
     *
     * @param otherObject
     * @return
     */
    
    @Override
    public boolean collision(GameObject otherObject) {
        if (location.intersects(otherObject.getLocation())) {
            if (otherObject instanceof TankBullet) {
                this.show = false;
            } // end if
            return true;
        } // end if
        return false;
    } // end collision
}
