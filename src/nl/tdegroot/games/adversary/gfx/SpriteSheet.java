package nl.tdegroot.games.adversary.gfx;

public class SpriteSheet {

    public Sprite[] sprites;

    public SpriteSheet(String ref, int spriteWidth, int spriteHeight) {
        Sprite sheet = new Sprite(ref);
        int spritesAcross = sheet.width / spriteWidth;
        int rows = sheet.height / spriteHeight;
        sprites = new Sprite[spritesAcross * rows];
        for (int y = 0; y < rows; y++) {
            int row = y * spriteHeight;

            for (int x = 0; x < spritesAcross; x++) {

                int column = x * spriteWidth;
                int[] pixels = new int[spriteWidth * spriteHeight];

                for (int yy = 0; yy < row + spriteHeight; yy++) {

                    int ya = row + yy;
                    if (ya >= sheet.height) continue;

                    for (int xx = 0; xx < column + spriteWidth; xx++) {

                        int xa = column + xx;
                        if (xa >= sheet.width) continue;
                        int col = sheet.pixels[xa + ya * sheet.width];
                        pixels[xx + yy * spriteWidth] = col;

                    }
                }
                sprites[column + row * spritesAcross] = new Sprite(pixels);
            }
        }
    }

}