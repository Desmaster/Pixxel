package nl.tdegroot.games.adversary.gfx;

public class Screen {

    private int width;
    private int height;
    protected int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        clear();
    }

    public void clear() {
        clear(0xFF000000);
    }

    public void clear(int col) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = col;
        }
    }

    public void render(int xp, int yp, Sprite sprite) {
        for (int y = 0; y < sprite.height; y++) {
            int ya = yp + y;
            for (int x = 0; x < sprite.width; x++) {
                int xa = xp + x;
                int col = sprite.pixels[x + y * sprite.width];
                if (col == 0xFFFF00FF) continue;
                pixels[xa + ya * width] = col;
            }
        }

    }

}
