/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tank;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import wingman.GameWorld;
import wingman.game.PlayerShip;
import wingman.modifiers.motions.InputController;

/**
 *
 * @author Ulises
 */
public class Tank extends PlayerShip {

    int direction;

    /**
     *
     * @param location
     * @param img
     * @param controls
     * @param name
     */
    public Tank(Point location, Image img, int[] controls, String name) {
        super(location, new Point(0, 0), img, controls, name);
        resetPoint = new Point(location);
        this.gunLocation = new Point(32, 32);

        this.name = name;
        weapon = new TankWeapon();
        motion = new InputController(this, controls, TankWorld.getInstance());
        lives = 2; //CHANGEME
        health = 100; //CHANGEME
        strength = 100;
        score = 0;
        respawnCounter = 0;
        height = 64;
        width = 64;
        direction = 180;
        this.location = new Rectangle(location.x, location.y, width, height);
    }

        public void turn(int angle) {
        this.direction = this.direction + angle;

        if (this.direction < 0) {
            this.direction = 359;
        } // end if

        if (this.direction >= 360) {
            this.direction = 0;
        } // end if
    }

        public void update(int w, int h) {
        // updates the location of the tank based on if up or down is being pressed. 
        // Multiples it by 5 to speed up the motion otherwise its really slow
        if (up == 1 || down == 1) {
            int dx = (int) (5 * Math.sin(Math.toRadians(this.direction + 90)));
            int dy = (int) (5 * Math.cos(Math.toRadians(this.direction + 90)));
            this.location.x = this.location.x + dx * (up - down);
            this.location.y = this.location.y + dy * (up - down);
        } // end if

        // updates the location of the tank if the left or right button are pressed
        // multiples it by 4 to speed up the tank
        if (left == 1 || right == 1) {
            this.turn(4 * (left - right));
        } // end if

        if (location.x < 0) {
            this.location.x = 0;
        } // end if

        if (this.location.y < 0) {
            this.location.y = 0;
        } // end if

        if (location.x > w - this.width) {
            this.location.x = w - this.width;
        } // end if

        if (this.location.y > h - this.height) {
            this.location.y = h - this.height;
        } // end if

        if (isFiring) {
            int bulletFrame = TankWorld.getInstance().getFrameNumber();

            if (bulletFrame >= lastFired + weapon.reload) {
                fire();
                lastFired = bulletFrame;
            } // end if
        } // end if
    }

    public void draw(Graphics g, ImageObserver obs) {
        if (respawnCounter <= 0) {
            g.drawImage(img, //the image
                    location.x, location.y, //destination top left
                    location.x + this.getSizeX(), location.y + this.getSizeY(), //destination lower right
                    (direction / 6) * this.getSizeX(), 0, //source top left
                    ((direction / 6) * this.getSizeX()) + this.getSizeX(), this.getSizeY(), //source lower right
                    obs);
        } else if (respawnCounter == 80) {
            TankWorld.getInstance().addClockObserver(this.motion);
            respawnCounter -= 1;
            System.out.println(Integer.toString(respawnCounter));
        } else if (respawnCounter < 80) {
            if (respawnCounter % 2 == 0) {
                g.drawImage(img, //the image
                        location.x, location.y, //destination top left
                        location.x + this.getSizeX(), location.y + this.getSizeY(), //destination lower right
                        (direction / 6) * this.getSizeX(), 0, //source top left
                        ((direction / 6) * this.getSizeX()) + this.getSizeX(), this.getSizeY(), //source lower right
                        obs);
            }
            respawnCounter -= 1;
        } else {
            respawnCounter -= 1;
        }
    }

    public void die() {
        this.show = false;
        GameWorld.setSpeed(new Point(0, 0));
        lives -= 1;
        if (lives >= 0) {
            TankWorld.getInstance().removeClockObserver(this.motion);
            reset();
        } else {
            this.motion.delete(this);
        }
    }

    public void reset() {
        this.setLocation(resetPoint);
        health = strength;
        respawnCounter = 160;
        this.weapon = new TankWeapon();
    }

}
