package nl.tdegroot.games.adversary.gfx;

import nl.tdegroot.games.adversary.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    public int width;
    public int heiight;
    public int[] pixels;

    public Sprite(String ref) {
        try {
            BufferedImage image = ImageIO.read(Game.class.getResourceAsStream(ref));
            width = image.getWidth();
            heiight = image.getHeight();
            pixels = new int[width * heiight];
            image.getRGB(0, 0, width, heiight, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
