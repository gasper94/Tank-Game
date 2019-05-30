package wingman.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

/* This is where the scrolling background is drawn*/

/**
 *
 * @author Ulises
 */
public class Background extends BackgroundObject {
	int move, w, h;

    /**
     *
     * @param w
     * @param h
     * @param speed
     * @param img
     */
    public Background(int w, int h, Point speed, Image img) {
		super(new Point(0,0), speed, img);
		this.img = img;
		this.w = w;
		this.h = h;
		move = 0;
	}
	
    /**
     *
     * @param w
     * @param h
     */
    public void update(int w, int h) {
    }
	
    /**
     *
     * @param g
     * @param obs
     */
    public void draw(Graphics g, ImageObserver obs) {
        int TileWidth = img.getWidth(obs);
        int TileHeight = img.getHeight(obs);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);
        
        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g.drawImage(img, j * TileWidth, i * TileHeight + (move % TileHeight), TileWidth, TileHeight, obs);
            }
        }
        move += speed.y;
    }
	
    /**
     *
     * @param otherObject
     * @return
     */
    public boolean collision(GameObject otherObject) {
        return false;
    }
}
