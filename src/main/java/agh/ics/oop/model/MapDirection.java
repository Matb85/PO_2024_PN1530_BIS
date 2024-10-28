package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    @Override
    public String toString() {
        switch (this) {
            case NORTH -> { return "Północ"; }
            case EAST -> { return "Wschód"; }
            case SOUTH -> { return "Południe"; }
            case WEST -> { return "Zachód"; }
            default -> { return null; }
        }
    }


    public MapDirection next(){
        return MapDirection.values()[(this.ordinal() + 1) % 4];
    }

    public MapDirection previous(){
        return MapDirection.values()[(this.ordinal() - 1 + 4) % 4];
    }

    public Vector2d toUnitVector(){
        switch (this) {
            case NORTH -> { return new Vector2d(0, 1); }
            case EAST -> { return new Vector2d(1, 0); }
            case SOUTH -> { return new Vector2d(0, -1); }
            case WEST -> { return new Vector2d(-1, 0); }
            default -> { return null; }
        }
    }
}
