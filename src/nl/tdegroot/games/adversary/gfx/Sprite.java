package nl.tdegroot.games.adversary.gfx;

import nl.tdegroot.games.adversary.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    public int width;
    public int height;
    public int[] pixels;

    public Sprite(String ref) {
        try {
            BufferedImage image = ImageIO.read(Game.class.getResourceAsStream(ref));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(int[] pixels) {
        this.pixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = pixels[i];
        }
    }

}
