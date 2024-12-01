package agh.ics.oop.model.util;

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(Vector2d position) {
        super("Pozycja " + position + " jest niepoprawna");
    }
}
