package nl.tdegroot.games.pixxel.gfx;

import java.util.ArrayList;
import java.util.HashMap;

public class Animation {
    SpriteSheet spriteSheet;

    ArrayList<int[]> frames = new ArrayList<int[]>();
    HashMap<Integer, Float> frameDurations = new HashMap<Integer, Float>();

    float defaultDuration;
    long lastUpdate = 0;

    int currentFrame = 0;


    public Animation(SpriteSheet spriteSheet, float defaultDuration) {
        this.spriteSheet = spriteSheet;
        this.defaultDuration = defaultDuration;
    }

    public Animation(SpriteSheet spriteSheet) {
        this(spriteSheet, 125f);
    }

    public void setFrameDurations(float duration) {
        if (frames.size() == 0) return;

        for(int i = 0; i < frames.size(); i++) {
            frameDurations.put(i, duration);
        }
    }

    public void addFrame(int x, int y, float duration) {
        frames.add(new int[] {x, y});
        frameDurations.put(frames.size() - 1, duration);
    }

    public void addFrame(int x, int y) {
        addFrame(x, y, defaultDuration);
    }

    public void setFrameStrip(int begin, int end) {
        for (int i = begin; i < end; i++) {
            addFrame(i % spriteSheet.getSpritesAcross(), (int) Math.floor(i / spriteSheet.getSpritesAcross()));
        }
    }

    public void render(int x, int y, Screen screen) {
        long currentTime = System.currentTimeMillis();

        if (lastUpdate == 0) {
            lastUpdate = currentTime;
        }

        if (currentTime - lastUpdate > frameDurations.get(currentFrame)) {
            currentFrame++;
            lastUpdate = currentTime;

            if (currentFrame == frames.size()) {
                currentFrame = 0;
            }
        }

        spriteSheet.render(x, y, frames.get(currentFrame)[0], frames.get(currentFrame)[1], screen);
    }
}