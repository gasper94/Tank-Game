/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import wingman.game.MoveableObject;
import wingman.modifiers.motions.MotionController;

/**
 *
 * @author Ulises
 */
public class Simple2DMotion extends MotionController {
	int dx,dy;

    /**
     *
     * @param direction
     */
    public Simple2DMotion(int direction) {
		super(TankWorld.getInstance());
    	dy = (int)(10*(double)Math.cos(Math.toRadians(direction+90)));
    	dx = (int)(10*(double)Math.sin(Math.toRadians(direction+90)));
    }
	
    /**
     *
     * @param theObject
     */
    public void read(Object theObject){
		MoveableObject object = (MoveableObject) theObject;
		object.move(dx, dy);
	}
}
