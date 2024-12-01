package agh.ics.oop.model;

import agh.ics.oop.model.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    protected List<MapChangeListener> listeners = new ArrayList<>();

    public void addListener(MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MapChangeListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(String message) {
        for (MapChangeListener listener : listeners) {
            listener.mapChanged(this, message);
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public void move(Animal animal, MoveDirection direction) {
        final Vector2d oldPos = animal.getPosition();
        animal.move(direction, this);

        if (!oldPos.equals(animal.getPosition())) {
            animals.remove(oldPos);
            animals.put(animal.getPosition(), animal);
            notifyListeners("Zwierzątko ruszyło się z  " + oldPos + " do " + animal.getPosition());
        }
    }

    public void place(Animal animal) throws IncorrectPositionException {
        notify();
        if (!this.canMoveTo(animal.getPosition())) {
            throw new IncorrectPositionException(animal.getPosition());
        }
        animals.put(animal.getPosition(), animal);
    }

    abstract public Boundary getCurrentBounds();

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);

        final var boundary = getCurrentBounds();
        return visualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }
}
