package nl.tdegroot.games.pixxel;

import nl.tdegroot.games.pixxel.gfx.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Display extends Canvas {

    private String title;
    private int width;
    private int height;
    private int scale;
    private int[] pixels;

    BufferStrategy bs;
    Graphics g;

    private BufferedImage image;
    private JFrame frame;
    private Screen screen;

    public Display(String title, int width, int height, int scale) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.scale = scale;

        screen = new Screen(width / scale, height / scale);
    }

    private void init() {
        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        image = new BufferedImage(width / scale, height / scale, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
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

    public void preDraw() {
        if (bs == null) {
            createBufferStrategy(3);
        }

        bs = getBufferStrategy();
        g = bs.getDrawGraphics();

        screen.setGraphics(g);
        screen.setColor(screen.getDefaultColor());
        g.setFont(screen.getDefaultFont());
    }

    public void draw() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        //TODO: Is this the correct way to go?
        /**
         * Screen.getStrings(), can only be called once.
         * Screen.getStrings() contains al strings that need to be renderer.
         * string[0] = the string to be rendered
         * string[1] = x coordinate
         * string[2] = y coordinate
         */

        for(Object[] string : screen.getStrings()) {
            g.drawString((String) string[0], (int) string[1] * scale, (int) string[2] * scale);
        }
        screen.getStrings().clear();

        g.dispose();
        bs.show();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public int getScaledWidth() {
        return width/scale;
    }

    public int getScaledHeight() {
        return height/scale;
    }

    public int getScale() {
        return scale;
    }

    public Screen getScreen() {
        return screen;
    }
}