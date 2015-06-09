package nl.tdegroot.games.pixxel.gfx;

public class Screen {

    private int width;
    private int height;
    public int[] pixels;
    Color color = Color.WHITE;

    int translateX = 0, translateY = 0;

    int[] matrix;


    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        matrix = new int[] {0, 0, width, height};

        clear();
    }

    public void setProjectionMatrix(int[] matrix) {
        this.matrix = matrix;
    }

    public void translate(int x, int y) {
        translateX = x;
        translateY = y;
    }

    public void clear() {
        clear(0xFF000000);
    }

    public void clear(int col) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = col;
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawPoint(int x, int y) {
        if (x > matrix[0] && x < matrix[0] + matrix[2] && y > matrix[1] && y < matrix[1] + matrix[3])
            pixels[(x - translateX) + (y - translateY) * width] = color.hex;
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;

        while (true) {
            drawPoint(x1, y1);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = err * 2;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public void drawRect(int x, int y, int w, int h) {
        drawLine(x, y, x+w, y);
        drawLine(x+w, y, x + w, y + h);
        drawLine(x, y+h, x+w, y+h);
        drawLine(x, y, x, y+h);
    }

    public void fillRect(int x, int y, int w, int h) {
        for (int xx = x; xx < x + w; xx++) {
            for (int yy = y; yy < y + h; yy++) {
                drawPoint(xx, yy);
            }
        }
    }

    /*
    Source: http://en.wikipedia.org/wiki/Midpoint_circle_algorithm
     */
    public void drawCircle(int x0, int y0, int r) {
        int x = r;
        int y = 0;
        int decisionOver2 = 1 - x;

        while(x >= y)
        {
            drawPoint( x + x0,  y + y0);
            drawPoint( y + x0,  x + y0);
            drawPoint(-x + x0,  y + y0);
            drawPoint(-y + x0,  x + y0);
            drawPoint(-x + x0, -y + y0);
            drawPoint(-y + x0, -x + y0);
            drawPoint( x + x0, -y + y0);
            drawPoint( y + x0, -x + y0);
            y++;
            if (decisionOver2<=0)
            {
                decisionOver2 += 2 * y + 1;   // Change in decision criterion for y -> y+1
            }
            else
            {
                x--;
                decisionOver2 += 2 * (y - x) + 1;   // Change for y -> y+1, x -> x-1
            }
        }
    }

    public void fillCircle(int x, int y, int r) {
        for (int yy = -r; yy < r; yy++) {
            for (int xx = -r; xx < r; xx++) {
                if (xx*xx+yy*yy <= (r*2 + r*2) * 0.8f)
                    drawPoint(x + xx, y + yy);
            }
        }
    }

    public void render(int xp, int yp, Sprite sprite) {
        render(xp, yp, sprite, 0);
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

                int col = sprite.pixels[xs + ys * sprite.width];
                if (col == 0xFFFF00FF) continue;
                if (col != color.hex) color.hex = col;
                drawPoint(xa, ya);
            }
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}