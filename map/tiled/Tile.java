package nl.tdegroot.games.pixxel.map.tiled;

public class Tile {

    public int tileID;
    public int firstGID,lastGID;

    public Tile(int tileID, int firstGID, int lastGID) {
        this.tileID = tileID;
        this.firstGID = firstGID;
        this.lastGID = lastGID;
    }

}
