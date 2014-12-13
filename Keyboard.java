package nl.tdegroot.games.pixxel;

import nl.tdegroot.games.pixxel.gfx.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public static final int KEY_BACK            = 8; /* backspace */
    public static final int KEY_TAB             = 9;
    public static final int KEY_RETURN          = 13; /* Enter on main keyboard */
    public static final int KEY_SHIFT           = 16;
    public static final int KEY_CONTROL         = 17;
    public static final int KEY_MENU            = 18; /* left Alt */
    public static final int KEY_PAUSE           = 19; /* Pause */
    public static final int KEY_CAPITAL         = 20;
    public static final int KEY_ESCAPE          = 27;
    public static final int KEY_SPACE           = 32;
    public static final int KEY_PRIOR           = 33; /* PgUp on arrow keypad */
    public static final int KEY_NEXT            = 34; /* PgDn on arrow keypad */
    public static final int KEY_END             = 35; /* End on arrow keypad */
    public static final int KEY_HOME            = 36; /* Home on arrow keypad */
    public static final int KEY_LEFT            = 37; /* LeftArrow on arrow keypad */
    public static final int KEY_UP              = 38; /* UpArrow on arrow keypad */
    public static final int KEY_RIGHT           = 39; /* RightArrow on arrow keypad */
    public static final int KEY_DOWN            = 40; /* DownArrow on arrow keypad */
    public static final int KEY_INSERT          = 45; /* Insert on arrow keypad */
    public static final int KEY_DELETE          = 46; /* Delete on arrow keypad */
    public static final int KEY_0               = 48;
    public static final int KEY_1               = 49;
    public static final int KEY_2               = 50;
    public static final int KEY_3               = 51;
    public static final int KEY_4               = 52;
    public static final int KEY_5               = 53;
    public static final int KEY_6               = 54;
    public static final int KEY_7               = 55;
    public static final int KEY_8               = 56;
    public static final int KEY_9               = 57;
    public static final int KEY_A               = 65;
    public static final int KEY_B               = 66;
    public static final int KEY_C               = 67;
    public static final int KEY_D               = 68;
    public static final int KEY_E               = 69;
    public static final int KEY_F               = 70;
    public static final int KEY_G               = 71;
    public static final int KEY_H               = 72;
    public static final int KEY_I               = 73;
    public static final int KEY_J               = 74;
    public static final int KEY_K               = 75;
    public static final int KEY_L               = 76;
    public static final int KEY_O               = 79;
    public static final int KEY_M               = 77;
    public static final int KEY_N               = 78;
    public static final int KEY_P               = 80;
    public static final int KEY_Q               = 81;
    public static final int KEY_R               = 82;
    public static final int KEY_S               = 83;
    public static final int KEY_T               = 84;
    public static final int KEY_U               = 85;
    public static final int KEY_V               = 86;
    public static final int KEY_W               = 87;
    public static final int KEY_X               = 88;
    public static final int KEY_Y               = 89;
    public static final int KEY_Z               = 90;
    public static final int KEY_NUMPAD0         = 96;
    public static final int KEY_NUMPAD1         = 97;
    public static final int KEY_NUMPAD2         = 98;
    public static final int KEY_NUMPAD3         = 99;
    public static final int KEY_NUMPAD4         = 100;
    public static final int KEY_NUMPAD5         = 101;
    public static final int KEY_NUMPAD6         = 102;
    public static final int KEY_NUMPAD7         = 103;
    public static final int KEY_NUMPAD8         = 104;
    public static final int KEY_NUMPAD9         = 105;
    public static final int KEY_MULTIPLY        = 106; /* * on numeric keypad */
    public static final int KEY_ADD             = 107; /* + on numeric keypad */
    public static final int KEY_SUBTRACT        = 109; /* - on numeric keypad */
    public static final int KEY_DECIMAL         = 110; /* . on numeric keypad */
    public static final int KEY_DIVIDE          = 111; /* / on numeric keypad */
    public static final int KEY_F1              = 112;
    public static final int KEY_F2              = 113;
    public static final int KEY_F3              = 114;
    public static final int KEY_F4              = 115;
    public static final int KEY_F5              = 116;
    public static final int KEY_F6              = 117;
    public static final int KEY_F7              = 118;
    public static final int KEY_F8              = 119;
    public static final int KEY_F9              = 120;
    public static final int KEY_F10             = 121;
    public static final int KEY_F11             = 122;
    public static final int KEY_F12             = 123;
    public static final int KEY_NUMLOCK         = 144;
    public static final int KEY_SCROLL          = 145; /* Scroll Lock */
    public static final int KEY_SEMICOLON       = 186;
    public static final int KEY_EQUALS          = 187;
    public static final int KEY_COMMA           = 188;
    public static final int KEY_PERIOD          = 190; /* . on main keyboard */
    public static final int KEY_SLASH           = 191; /* / on main keyboard */
    public static final int KEY_GRAVE           = 192; /* accent grave */
    public static final int KEY_MINUS           = 189; /* - on main keyboard */
    public static final int KEY_LBRACKET        = 219;
    public static final int KEY_BACKSLASH       = 220;
    public static final int KEY_RBRACKET        = 221;
    public static final int KEY_APOSTROPHE      = 222;

    private boolean[] keys = new boolean[999];

    private static Keyboard instance;

    public static Keyboard getInstance() {
        if (instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }

    public void register(Window window) {
        window.addKeyListener(this);
    }

    public static boolean isKeyDown(int keyCode) {
        return Keyboard.getInstance().keys[keyCode];
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {
    }

}
