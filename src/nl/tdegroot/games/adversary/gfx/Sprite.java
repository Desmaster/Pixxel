package nl.tdegroot.games.adversary.gfx;

import nl.tdegroot.games.adversary.Game;
import nl.tdegroot.games.adversary.util.Log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    public int width;
    public int height;
    public int[] pixels;

    public Sprite(String ref) {
        try {
            Log.error("Trying to load: " + ref + "...");
            BufferedImage image = ImageIO.read(Game.class.getResourceAsStream(ref));
            System.out.println(" succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(" failed");
        }
    }

    public Sprite(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = pixels[i];
        }
    }

    public Sprite(String ref, boolean b, Color trans) {

    }
}
