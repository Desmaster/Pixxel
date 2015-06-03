package nl.tdegroot.games.pixxel.util;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;

public class FileUtils {

    /**
     * Returns the user's home directory
     * Inspired by https://stackoverflow.com/questions/585534/what-is-the-best-way-to-find-the-users-home-directory-in-java
     *
     * @return String
     */
    public static String getHomeDirectory() {
        String home = System.getProperty("user.home");
        File boteDirectory = new File(home + File.separator + "/" + "bote");
        if (!boteDirectory.exists()) {
            try {
                Files.createDirectory(boteDirectory.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return boteDirectory.toPath().toString() + File.separator;
    }

    public static void writeFile(String path, String contents) {

    }

    public static void writeImage(String path, BufferedImage image) {
        try {
            File outputfile = new File(path);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage createImageFromPixels(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixels[x + y * width]);
            }
        }
        return image;
    }

}
