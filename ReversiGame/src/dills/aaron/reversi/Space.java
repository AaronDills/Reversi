package dills.aaron.reversi;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.Rectangle;
/**
 * The class Space is a space on the gameboard
 * @author aarondills
 */
public class Space {
    /**
     * boxHeight is the height of the space
     */
    private final int boxHeight = 50;
    /**
     * boxWidth is the width of the space
     */
    private final int boxWidth = 50;
    /**
     * boxLeftSide is the pixel location of the space
     */
    private final int boxLeftSide;
    /**
     * boxRightSide is the pixel location of the space
     */
    private final int boxRightSide;
    /**
     *  boxTopSide is the pixel location of the space
     */
    private final int boxTopSide;
    /**
     * boxBottomSide is the the pixel location of the space
     */
    private final int boxBottomSide;
    /**
     * hasDisc is a boolean value that is true if the space has a disc object
     */
    private boolean hasDisc;
    /*
    box is a rectangle made from the box side integers
    */
    private final Rectangle2D box;
    /**
     * playerDisc is a disc object that a space contains
     */
    private Disc playerDisc = null;
    /**
     * spaceColor is the color of a space object
     */
    private Color spaceColor = Color.WHITE;

    public Space(int x, int y) {
        this.hasDisc = false;
        this.boxLeftSide = x;
        this.boxTopSide = y;
        this.boxBottomSide = boxTopSide + boxHeight;
        this.boxRightSide = boxLeftSide + boxWidth;
        this.box = new Rectangle2D.Double(boxLeftSide, boxTopSide, boxWidth, boxHeight);

    }
/**
 * AddDisc adds a disc object to the space
 * @param color is the player color 
 */
    public void addDisc(String color) {
        this.hasDisc = true;
        if (color.equals("Blue")) {
            this.playerDisc = new Disc(color);
        } else {
            this.playerDisc = new Disc(color);
        }
    }
/**
 * testsDimension takes as input the current coordinates of the mouse and returns true
 * if the mouse is within the space object
 * @param x is the x coordinate of the mouse
 * @param y is the y coordinate of the mouse
 * @return a boolean value is returned
 */
    public boolean testsDimension(int x, int y) {
        if (x > this.boxLeftSide + 5 && x < this.boxRightSide - 5) {
            if (y > this.boxTopSide + 5 && y < this.boxBottomSide - 5) {
                return true;
            }
        }
        return false;

    }
/*
    setColor sets the color of a space
    */
    public void setColor(Color c) {
        this.spaceColor = c;
    }
/**
 * getDiscColor returns the color of the disc on a space
 * @return 
 */
    public String getDiscColor() {
        return this.playerDisc.getColor();
    }
/**
 * hasDisc returns a boolean value that states whether there is a disc on the space
 * @return 
 */
    public boolean hasDisc() {
        return this.hasDisc;
    }

    public void draw(Graphics2D g) {

        if (!hasDisc) {
            g.setColor(Color.BLACK);
            g.draw(this.box);
            g.setColor(spaceColor);
            g.fill(this.box);
        }
        if (hasDisc) {
            g.setColor(Color.BLACK);
            g.draw(this.box);
            g.setColor(Color.WHITE);
            g.fill(this.box);
            playerDisc.draw(g, boxLeftSide, boxTopSide, boxWidth, boxHeight);

        }
    }
}
