package wingman.game;

import java.awt.Image;
import java.awt.Point;
import tank.TankWorld;

/* Small explosions happen whenever an enemy dies */

/**
 *
 * @author Ulises
 */
public class Explosion extends BackgroundObject {
	int timer;
	int frame;
	static Image animation[] = new Image[] {
                TankWorld.sprites.get("explosion1_1"),
		TankWorld.sprites.get("explosion1_2"),
		TankWorld.sprites.get("explosion1_3"),
		TankWorld.sprites.get("explosion1_4"),
		TankWorld.sprites.get("explosion1_5"),
		TankWorld.sprites.get("explosion1_6"),
		TankWorld.sprites.get("explosion1_7")};
	
    /**
     *
     * @param location
     */
    public Explosion(Point location) {
		super(location, animation[0]);
		timer = 0;
		frame=0;
	}
	
    /**
     *
     * @param w
     * @param h
     */
    public void update(int w, int h){
    	super.update(w, h);
    	timer++;
    	if(timer%5==0){
    		frame++;
    		if(frame<6)
    			this.img = animation[frame];
    		else
    			this.show = false;
    	}

	}
	
    /**
     *
     * @param otherObject
     * @return
     */
    public boolean collision(GameObject otherObject){
		return false;
	}
}
