package wingman.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import wingman.GameWorld;
import wingman.game.*;

/**
 *
 * @author Ulises
 */
public class InfoBar extends InterfaceObject {
	PlayerShip player;
	String name;
	
    /**
     *
     * @param player
     * @param name
     */
    public InfoBar(PlayerShip player, String name){
		this.player = player;
		this.name = name;
	}
	
    /**
     *
     * @param g2
     * @param x
     * @param y
     */
    public void draw(Graphics g2, int x, int y){
        g2.setFont(new Font("Calibri", Font.PLAIN, 24));
        if(player.getHealth()>70){
        	g2.setColor(Color.GREEN);
        }
        else if(player.getHealth()>40){
        	g2.setColor(Color.ORANGE);
        }
        else{
        	g2.setColor(Color.RED);
        }
        g2.fillRect(x+2, y-25, (int) Math.round(player.getHealth()*1.1), 20);
        
        for(int i=0;i< player.getLives();i++){
        	if(GameWorld.sprites.get("life" + name) != null)
        		g2.drawImage(GameWorld.sprites.get("life" + name), x+110 +i*30, y-30, observer);
        	else{
        		g2.setColor(Color.RED);
        		g2.fillOval(x+130 +i*30, y-25, 15, 15);
        	}
        }
        g2.setColor(Color.BLUE);
        g2.drawString(Integer.toString(player.getScore()), x+250, y-8);
	}

}
