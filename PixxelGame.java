package nl.tdegroot.games.pixxel;

import nl.tdegroot.games.pixxel.gfx.Screen;

public abstract class PixxelGame implements Runnable {

    protected String title;
    protected int width;
    protected int height;
    protected int scale;

    protected boolean running = false;
    public int frames;
    public int ticks;
    protected int time;

    protected Display display;
    private Thread thread;

    private boolean logFps = false;

    public PixxelGame(String title, int width, int height, int scale) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.scale = scale;
        preInit();
        try {
            init();
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    private void preInit() {
        display = new Display(title, width, height, scale);
        Keyboard.getInstance().register(display);
    }

    public abstract void init() throws GameException;

    public void run() {
        double nsPerTick = 1000000000.0D / 60;
        double unprocessed = 0;
        long lastTime = System.nanoTime(), now;
        long lastTimer1 = System.currentTimeMillis();
        display.requestFocus();
        while (running) {
            now = System.nanoTime();
            int delta = (int) (now - lastTime);
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = false;

            while (unprocessed >= 1) {
                ticks++;
                time++;
                tick(delta);
                unprocessed--;
                shouldRender = true;
            }

            if (shouldRender) {
                preRender();
                render(display.screen);
                postRender();
                frames++;
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                if (logFps)
                    System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }

        }
    }

    public abstract void tick(int delta);

    private void preRender() {
        Screen screen = display.screen;
        screen.clear();
    }

    public abstract void render(Screen screen);

    private void postRender() {
        display.draw();
    }

    public synchronized void start() {
        if (running) return;
        display.create();
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setLogFps(boolean logFps) {
        this.logFps = logFps;
    }
}
