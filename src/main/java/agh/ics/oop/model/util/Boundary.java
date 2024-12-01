package agh.ics.oop.model.util;

public record Boundary(Vector2d lowerLeft, Vector2d upperRight) {
    public boolean contains(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft);
    }
}
