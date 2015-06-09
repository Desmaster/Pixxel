package nl.tdegroot.games.pixxel.state;

import nl.tdegroot.games.pixxel.Display;
import nl.tdegroot.games.pixxel.GameException;
import nl.tdegroot.games.pixxel.PixxelGame;
import nl.tdegroot.games.pixxel.gfx.Screen;

public abstract class State {
    protected PixxelGame game;

    public State(PixxelGame game) {
        this.game = game;
    }

    public abstract void init(Display display) throws GameException;

    public abstract void tick(Display display, int delta);

    public abstract void render(Display display, Screen screen);
}
