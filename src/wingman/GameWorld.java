package wingman;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;
import javax.swing.JPanel;
import wingman.game.BackgroundObject;
import wingman.game.Bullet;

/**
 *
 * @author Ulises
 */
public abstract class GameWorld extends JPanel implements Runnable, Observer {

    /**
     *
     * @param newObjects
     */
    public abstract void addBullet(Bullet...newObjects);

    /**
     *
     * @param obs
     */
    public abstract void addClockObserver(Observer obs);

    /**
     *
     */
    public static HashMap<String,Image> sprites = new HashMap<String,Image>();
	
	private static Point speed=new Point(0,0);

    /**
     *
     */
    public static final GameClock clock = new GameClock();
    
    /**
     *
     */
    protected BufferedImage bimg;

    /**
     *
     */
    protected ArrayList<BackgroundObject> background;
    
    /**
     *
     */
    abstract protected void loadSprites();
    
    /**
     *
     * @param speed
     */
    public static void setSpeed(Point speed){
    	GameWorld.speed = speed;
    }
    
    /**
     *
     * @return
     */
    public static Point getSpeed(){
    	return new Point(GameWorld.speed);
    }
    
    /**
     *
     * @param w
     * @param h
     * @return
     */
    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }
}
