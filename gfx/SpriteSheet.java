package nl.tdegroot.games.pixxel.gfx;

public class SpriteSheet {

    public Sprite[] sprites;
    public int tilesAcross;
    public int tilesDown;

    public SpriteSheet(String ref, int spriteWidth, int spriteHeight) {
        load(new Sprite(ref), spriteWidth, spriteHeight);
    }

    public SpriteSheet(Sprite sheet, int spriteWidth, int spriteHeight, int tileSpacing, int tileMargin) {
        load(sheet, spriteWidth, spriteHeight);
    }

    private void load(Sprite sheet, int spriteWidth, int spriteHeight) {
        int amount = (sheet.width * sheet.height) / (spriteWidth * spriteHeight);
        sprites = new Sprite[amount];
        int[] pixels = new int[spriteWidth * spriteHeight];
        int current = 0;
        tilesAcross = sheet.width / spriteWidth;
        tilesDown = sheet.height / spriteHeight;
        for (int yp = 0; yp < sheet.height / spriteHeight; yp++) {
            for (int xp = 0; xp < sheet.width / spriteWidth; xp++) {
                for (int y = 0; y < spriteHeight; y++) {
                    for (int x = 0; x < spriteWidth; x++) {
                        int xo = x + (xp * spriteWidth);
                        int yo = y + (yp * spriteHeight);
                        int col = sheet.pixels[xo + yo * sheet.width];
                        pixels[x + y * spriteWidth] = col;
                    }
                }
                sprites[current++] = new Sprite(pixels, spriteWidth, spriteHeight);
            }
        }
    }

    public void render(int x, int y, int sheetX, int sheetY, Screen screen) {
        Sprite sprite = sprites[sheetX + sheetY * tilesAcross];
        screen.render(x, y, sprite);
    }
}