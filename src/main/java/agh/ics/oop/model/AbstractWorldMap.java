package agh.ics.oop.model;

import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    public boolean canMoveTo(Vector2d position) {
        return isOccupied(position);
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
        }
    }

    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            return false;
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }
}
