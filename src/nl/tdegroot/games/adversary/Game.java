package nl.tdegroot.games.adversary;

import nl.tdegroot.games.adversary.gfx.Screen;
import nl.tdegroot.games.adversary.gfx.Sprite;
import nl.tdegroot.games.adversary.gfx.Window;

public class Game implements Runnable {

    private int width;
    private int height;
    private int scale;

    private boolean running = false;
    private int frames;
    private int ticks;

    private Thread thread;
    private Window window;
    private Sprite sprite = new Sprite("/sprites/yorrvak.png");

    public Game(int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public void init() {
        window = new Window("Adversary", width, height, scale);
    }

    public void run() {
        double nsPerTick = 1000000000.0D / 60;
        double unprocessed = 0;
        long lastTime = System.nanoTime(), now;
        long lastTimer1 = System.currentTimeMillis();
        window.requestFocus();
        while (running) {
            now = System.nanoTime();
            int delta = (int) (now - lastTime) / 1000;
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
                render();
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

    private void tick(int delta) {

    }

    private void render() {
        Screen screen = window.getScreen();
        screen.clear();
        screen.render(10, 10, sprite);
        window.draw();
    }

    public void start() {
        window.create();
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public static void main(String[] args) {
        Game game = new Game(1280, 720, 4);
        game.init();
        game.start();
    }
}
