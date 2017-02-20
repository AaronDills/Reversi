package dills.aaron.reversi;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * The Disc class is the image and data related to the disc pieces that are
 * placed on a space
 *
 * @author aarondills
 */
public class Disc {

    /**
     * discImage is the image related to a space object
     */
    private Image discImage = null;
    /**
     * color is a String that is used to compare discs for spaces
     */
    private String color = "";

    /**
     * The Disc constructor initializes the image and string associated with a
     * disc object
     *
     * @param color is the color of the disc
     */
    public Disc(String color) {
        if (color.equals("Blue")) {
            try {
                this.discImage = ImageIO.read(getClass().getResourceAsStream("/Images/blueDisc.png"));
            } catch (Exception e) {

            };
            this.color = "Blue";
        } else {
            try {
                this.discImage = ImageIO.read(getClass().getResourceAsStream("/Images/redDisc.png"));
            } catch (Exception e) {
            };
            this.color = "Red";
        }
    }

    /**
     *
     * @return a string relating to the color of the disc is returned
     */
    public String getColor() {
        return this.color;
    }

    public void draw(Graphics2D g, int x, int y, int width, int height) {
        try {
            g.drawImage(discImage, x, y, width, height, null);

        } catch (Exception e) {
        };
    }
}
