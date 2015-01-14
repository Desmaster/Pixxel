package nl.tdegroot.games.pixxel.gfx;

import nl.tdegroot.games.pixxel.util.Log;
import nl.tdegroot.games.pixxel.util.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    public int width;
    public int height;
    public int[] pixels;
    private int[] sourcePixels;

    private int[][] rotation;
    private int currentRotation = 0;

    Color transparency = new Color(0xFFFF00FF);

    public Sprite(String ref) {
        this(ref, false, new Color(0xFFFF00FF));
    }

    public Sprite(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        rotation = new int[4][];
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = pixels[i];
        }
        sourcePixels = pixels.clone();
    }

    public Sprite(String ref, boolean b, Color transparency) {
        try {
            Log.info("Trying to load: " + ref + "...", false);
            BufferedImage image = ImageIO.read(ResourceLoader.getResourceAsStream(ref));
            System.out.println(" succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            rotation = new int[4][];
            image.getRGB(0, 0, width, height, pixels, 0, width);
            sourcePixels = pixels.clone();
            this.transparency = transparency;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(" failed");
        } catch (Exception e) {
            System.err.println(" failed");
        }
    }

    public void rotate(int angle) {
        int rotationCount = angle / 90;
        if (rotation[currentRotation] == null) rotation[currentRotation] = pixels.clone();
        if (rotation[rotationCount] == null) {
            if (angle == 360 || angle == 0) return;
            int[][] newPixels = new int[height][width];
            int[] rotationPixels = sourcePixels.clone();
            for (int i = 0; i < angle / 90; i++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        newPixels[y][x] = rotationPixels[x + y * width];
                    }
                }
                rotationPixels = rotate90CW(newPixels);
            }
            pixels = rotationPixels.clone();
            rotation[rotationCount] = pixels.clone();
        } else {
            pixels = rotation[rotationCount];
        }
        this.currentRotation = rotationCount;
    }

    private int[] rotate90CW(int[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        int[][] ret = new int[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M - 1 - r] = mat[r][c];
            }
        }
        return createSingleArray(ret, M * N);
    }

    private int[] createSingleArray(int[][] matrix, int totalNumber) {
        int a = 0;
        int[] intArray = new int[totalNumber];
        for (int b = 0; b < height; b++) {
            for (int c = 0; c < width; c++) {
                intArray[a++] = matrix[b][c];
            }
        }
        return intArray;
    }

}
