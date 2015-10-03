package nl.tdegroot.games.pixxel;

import nl.tdegroot.games.pixxel.gfx.Color;
import nl.tdegroot.games.pixxel.gfx.Screen;

public abstract class PixxelGame implements Runnable {

    private boolean running = false;
    private int frames;
    private int ticks;
    private int time;
    private long lastFrame;

    private Display display;
    private Thread thread;

    private boolean logFps = false;

    public PixxelGame(String title, int width, int height, int scale) {
        display = new Display(title, width, height, scale);
        lastFrame = getRealTime();
        Keyboard.getInstance().register(display);
    }

    public abstract void init(Display display) throws GameException;

    public void run() {
        double nsPerTick = 1000000000.0D / 60;
        double unprocessed = 0;
        long lastTime = System.nanoTime(), now;
        long lastTimer1 = getRealTime();
        display.requestFocus();
        while (running) {
            now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = true;

            while (unprocessed >= 1) {
                ticks++;
                time++;
                tick();
                unprocessed--;
                shouldRender = true;
            }

            if (shouldRender) {
                preRender();
                render(display.getScreen());
                postRender();
                frames++;
            }

            if (getRealTime() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                if (logFps)
                    System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }

        }
    }

    public int getDelta() {
        long time = getRealTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    private void preRender() {
        Screen screen = display.getScreen();
        screen.clear();

        display.preDraw();
    }

    public abstract void tick();

    public abstract void render(Screen screen);

    private void postRender() {
        display.getScreen().setColor(new Color(0xFFFF00FF));
        display.getScreen().drawString(0, 0, "" + frames);
        display.draw();
    }


    public synchronized void start() {
        try {
            init(display);
        } catch (GameException e) {
            e.printStackTrace();
        }

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

    public long getRealTime() {
        return System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }
}
