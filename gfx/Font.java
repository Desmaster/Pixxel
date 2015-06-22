package nl.tdegroot.games.pixxel.gfx;

import java.util.Objects;

/*
    Class inspired by: https://github.com/Miserlou/Minicraft/blob/master/src/com/mojang/ld22/gfx/Font.java
 */
public class Font {
    SpriteSheet spriteSheet;

    private static final String chars = "" + //
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ  " + //
            "0123456789.,!?'\"-+=/\\%()<>:;" + //
            "";

    public Font(String ref, int charWidth, int charHeight) {
        spriteSheet = new SpriteSheet(ref, charWidth, charHeight);
    }

    public void render(int x, int y, String string, Screen screen) {
        string = string.toUpperCase();

        for (int i = 0; i < string.length(); i++) {
            int charIndex = chars.indexOf(string.charAt(i));
            if (string.charAt(i) == ' ') continue;

//            System.out.println(charIndex);
//            System.out.println("character - " + string.charAt(i) + " - x: " + Math.floor(charIndex % spriteSheet.getSpritesAcross()));
//            System.out.println("character - " + string.charAt(i) + " - y: " + charIndex / spriteSheet.getSpritesAcross());
            spriteSheet.render(x + i * spriteSheet.getSpriteWidth(), y, (int) Math.floor(charIndex % spriteSheet.getSpritesAcross()), charIndex / spriteSheet.getSpritesAcross(), screen);
        }
    }
}
