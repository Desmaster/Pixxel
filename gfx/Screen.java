package nl.tdegroot.games.pixxel.gfx;

public class Screen {

    private int width;
    private int height;
    public int[] pixels;

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

    public void render(int xp, int yp, Sprite sprite, int rotation) {
        render(xp, yp, sprite, rotation, false);
    }

    public void render(int xp, int yp, Sprite sprite, int rotation, boolean fixed) {
        sprite.rotate(rotation);
        for (int y = 0; y < sprite.height; y++) {
            int ya = yp + y;
            int ys = y;

            for (int x = 0; x < sprite.width; x++) {
                int xa = xp + x;
                int xs = x;

//                if (rotation == 90) xs = sprite.height - y - 1;
//                if (rotation == 180) ys = sprite.height - 1 - ys; // Vertical Flip
//                if (rotation == 180) xs = sprite.width - 1 - xs; // Horizontal Flip
//                if (rotation == 270) ys = sprite.width - x - 1;

                int col = sprite.pixels[xs + ys * sprite.width];
                if (col == 0xFFFF00FF) continue;
                pixels[xa + ya * width] = col;
            }
        }

    }

}