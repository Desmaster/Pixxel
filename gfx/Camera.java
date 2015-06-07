package nl.tdegroot.games.pixxel.gfx;

//TODO: create zoom possibility
public class Camera {

    int width, height;
    int x, y;

    int scale = 1;

    public Camera(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getProjectionMatrix() {
        return new int[][] {{x, y}, {width, height}};
    }
}
