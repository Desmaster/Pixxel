package nl.tdegroot.games.pixxel.gfx;

public class SpriteSheet {

    private Sprite[] sprites;
    private int spritesAcross;
    private int spritesDown;

    private int spriteWidth, spriteHeight;

    public SpriteSheet(String ref, int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

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
        spritesAcross = sheet.width / spriteWidth;
        spritesDown = sheet.height / spriteHeight;
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
        render(x, y, sheetX, sheetY, 0, screen);
    }

    public void render(int x, int y, int sheetX, int sheetY, int rotation, Screen screen) {
        Sprite sprite = sprites[sheetX + sheetY * spritesAcross];
        screen.render(x, y, sprite, rotation);
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public int getSpritesAcross() {
        return spritesAcross;
    }

    public int getSpritesDown() {
        return spritesDown;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }
}