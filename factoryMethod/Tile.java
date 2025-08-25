package factoryMethod;

public interface Tile {
    char getCharacter();
    String getType();
}

class SwampTile implements Tile {
    public char getCharacter() { return 'S'; }
    public String getType() { return "swamp"; }
}

class WaterTile implements Tile {
    public char getCharacter() { return 'W'; }
    public String getType() { return "water"; }
}

class RoadTile implements Tile {
    public char getCharacter() { return 'R'; }
    public String getType() { return "road"; }
}

class ForestTile implements Tile {
    public char getCharacter() { return 'F'; }
    public String getType() { return "forest"; }
}

class BuildingTile implements Tile {
    public char getCharacter() { return 'B'; }
    public String getType() { return "building"; }
}
