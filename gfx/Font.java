package nl.tdegroot.games.pixxel.gfx;

/*
    Class inspired by: https://github.com/Miserlou/Minicraft/blob/master/src/com/mojang/ld22/gfx/Font.java
 */
public class Font {
    SpriteSheet spriteSheet;

    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Font(String ref, int charWidth, int charHeight) {
        spriteSheet = new SpriteSheet(ref, charWidth, charHeight);
    }

    public void render(int x, int y, String string, Screen screen) {
        string = string.toUpperCase();

        for (int i = 0; i < string.length(); i++) {
            int charIndex = chars.indexOf(string.charAt(i));
            spriteSheet.render(x + i * spriteSheet.getSpriteWidth(), y, (int) Math.floor(charIndex % spriteSheet.getSpritesAcross()), charIndex / spriteSheet.getSpritesAcross(), screen);
        }
    }
}
