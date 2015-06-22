package nl.tdegroot.games.pixxel.math;

public class Vector2i {

    private int x, y;
    private double angle;

    public Vector2i() {
        this(0, 0);
    }

    public Vector2i(double angle) {
        x = (int) Math.cos(angle * Math.PI / 180);
        y = (int) Math.sin(angle * Math.PI / 180);
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i add(Vector2i other) {
        x += other.x;
        y += other.y;
        return this;
    }

    public Vector2i subtract(Vector2i other) {
        x -= other.x;
        y -= other.y;
        return this;
    }

    public Vector2i multiply(Vector2i other) {
        x *= other.x;
        y *= other.y;
        return this;
    }

    public Vector2i divide(Vector2i other) {
        x /= other.x;
        y /= other.y;
        return this;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
