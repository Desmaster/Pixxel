package nl.tdegroot.games.pixxel.gfx;

public class Color {

    public int hex = 0;

    /**
     * The red component of the colour
     */
    public float r;

    /**
     * The green component of the colour
     */
    public float g;

    /**
     * The blue component of the colour
     */
    public float b;

    /**
     * The alpha component of the colour
     */
    public float a = 1.0f;

    /**
     * Create a colour from an evil integer packed 0xAARRGGBB. If AA
     * is specified as zero then it will be interpreted as unspecified
     * and hence a value of 255 will be recorded.
     *
     * @param hex The value to interpret for the colour
     */
    public Color(int hex) {
        int r = (hex & 0x00FF0000) >> 16;
        int g = (hex & 0x0000FF00) >> 8;
        int b = (hex & 0x000000FF);
        int a = (hex & 0xFF000000) >> 24;

        if (a < 0) {
            a += 256;
        }
        if (a == 0) {
            a = 255;
        }

        this.r = r / 255.0f;
        this.g = g / 255.0f;
        this.b = b / 255.0f;
        this.a = a / 255.0f;
    }

}
