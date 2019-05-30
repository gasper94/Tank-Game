package wingman.modifiers;

import java.util.Observable;

/**
 *
 * @author Ulises
 */
public abstract class AbstractGameModifier extends Observable{
	
    /**
     *
     */
    public AbstractGameModifier(){}
	
	/* read is used to send messages from game observables to game observers */

    /**
     *
     * @param theObject
     */
    
	public abstract void read(Object theObject);
}
