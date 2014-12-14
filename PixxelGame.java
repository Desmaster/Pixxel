package nl.tdegroot.games.pixxel;

import nl.tdegroot.games.pixxel.gfx.Screen;

public abstract class PixxelGame implements Runnable{

    protected String title;
    protected int width;
    protected int height;
    protected int scale;

    protected boolean running = false;
    public int frames;
    public int ticks;

    protected Window window;
    private Thread thread;

    public PixxelGame(String title, int width, int height, int scale) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.scale = scale;
        preInit();
        init();
    }

    private void preInit() {
        window = new Window(title, width, height, scale);
        Keyboard.getInstance().register(window);
    }

    public abstract void init();

    public void run() {
        double nsPerTick = 1000000000.0D / 60;
        double unprocessed = 0;
        long lastTime = System.nanoTime(), now;
        long lastTimer1 = System.currentTimeMillis();
        window.requestFocus();
        while (running) {
            now = System.nanoTime();
            int delta = (int) (now - lastTime);
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = false;

            while (unprocessed >= 1) {
                ticks++;
                tick(delta);
                unprocessed--;
                shouldRender = true;
            }

            if (shouldRender) {
                preRender();
                render();
                postRender();
                frames++;
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }

        }
    }

    public abstract void tick(int delta);

    private void preRender() {
        Screen screen = window.screen;
        screen.clear();
    }

    public abstract void render();

    private void postRender() {
        window.draw();
    }

    public void start() {
        window.create();
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public void stop() {

    }

}
