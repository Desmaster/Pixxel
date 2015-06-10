package nl.tdegroot.games.pixxel;

import nl.tdegroot.games.pixxel.gfx.Screen;
import nl.tdegroot.games.pixxel.state.State;

public abstract class PixxelStateGame extends PixxelGame {

    private State currentState;

    public PixxelStateGame(String title, int width, int height, int scale) {
        super(title, width, height, scale);
    }

    public void init(Display display) throws GameException {
        currentState.init(display);
    }

    public void tick() {
        currentState.tick();
    }

    public void render(Screen screen) {
        currentState.render(screen);
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

}
