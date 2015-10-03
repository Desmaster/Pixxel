package nl.tdegroot.games.pixxel.math;

public class Vector2f {

    public float x;
    public float y;
    private double angle;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(double angle) {
        x = (float) Math.cos(angle * Math.PI / 180);
        y = (float) Math.sin(angle * Math.PI / 180);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f add(Vector2f other) {
        x += other.x;
        y += other.y;
        return this;
    }

    public Vector2f subtract(Vector2f other) {
        x -= other.x;
        y -= other.y;
        return this;
    }

    public Vector2f multiply(Vector2f other) {
        x *= other.x;
        y *= other.y;
        return this;
    }

    public Vector2f divide(Vector2f other) {
        x /= other.x;
        y /= other.y;
        return this;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
