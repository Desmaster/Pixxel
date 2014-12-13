package nl.tdegroot.games.adversary;

public class GameException extends Exception {

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable e) {
        super(message, e);
    }

}
