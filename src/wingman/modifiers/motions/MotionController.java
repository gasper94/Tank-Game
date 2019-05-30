package wingman.modifiers.motions;

import java.util.Observable;
import java.util.Observer;
import wingman.GameWorld;
import wingman.game.*;
import wingman.modifiers.AbstractGameModifier;

/*MotionControllers move around objects!*/

/**
 *
 * @author Ulises
 */
public abstract class MotionController extends AbstractGameModifier implements Observer {
	int fireInterval;
	
    /**
     *
     */
    public MotionController(){
		fireInterval = -1;
	}
	
    /**
     *
     * @param world
     */
    public MotionController(GameWorld world){
		world.addClockObserver(this);
	}
	
    /**
     *
     * @param theObject
     */
    public void delete(Observer theObject){
		this.deleteObserver(theObject);
	}
	
	/*Motion Controllers observe the GameClock and fire on every clock tick
	 * The default controller doesn't do anything though*/
	public void update(Observable o, Object arg){
		this.setChanged();
		this.notifyObservers();
	}
	
    /**
     *
     * @param theObject
     */
    public void read(Object theObject){
		Ship object = (Ship) theObject;
		object.move();
	}
}
