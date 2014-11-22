package nl.tdegroot.games.adversary.gfx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window extends Canvas {

    public String title;
    public int width;
    public int height;
    public int scale;
    private int[] pixels;

    private BufferedImage image;
    private JFrame frame;
    private Screen screen;

    public Window(String title, int width, int height, int scale) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    private void init() {
        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        image = new BufferedImage(width / scale, height / scale, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        screen = new Screen(width / scale, height / scale);
    }

    public void create() {
        init();

        frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.black);

        g.dispose();
        bs.show();
    }

    public Screen getScreen() {
        return screen;
    }

}