package wingman.modifiers.motions;

import wingman.game.*;

/**
 *
 * @author Ulises
 */
public class SimpleMotion extends MotionController {
	
    /**
     *
     */
    public SimpleMotion() {
		super();
	}
	
    /**
     *
     * @param theObject
     */
    public void read(Object theObject){
		MoveableObject object = (MoveableObject) theObject;
		object.move();
	}
}
