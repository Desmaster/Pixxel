package nl.tdegroot.games.pixxel.util;

import java.security.SecureRandom;

public class Random {

    private static SecureRandom r = new SecureRandom();

    public static int nextInt(int min, int max) {
        if (max - min == 0) {
            return 0;
        }
        return r.nextInt(max - min) + min;
    }

    public static double nextDouble() {
        return (double) nextInt(0, 10) / 10;
    }

}
