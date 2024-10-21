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
}
