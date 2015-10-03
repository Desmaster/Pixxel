package nl.tdegroot.games.pixxel.math;

public class AABB {

    public static boolean collides(BoundingBox2i a, BoundingBox2i b) {
        if (abs(a.position.x - b.position.x) < a.size.x + b.size.x) {
            if (abs(a.position.y - b.position.y) < a.size.y + b.size.y) {
                return true;
            }
        }
        return false;
    }

    private static int abs(int i) {
        return i < 0 ? i * -1 : i;
    }

}
