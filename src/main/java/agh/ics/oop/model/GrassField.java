package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;

import java.util.HashMap;
import java.util.Map;

public class GrassField implements WorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();
    Map<Vector2d, Grass> grasses = new HashMap<>();
    private final int grassCount;

    public GrassField(int grassFields) {
        this.grassCount = grassFields;
        generateGrass();
    }

    private void generateGrass() {
        final int maxDimension = (int) (Math.sqrt(grassCount));

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxDimension, maxDimension, grassCount);

        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            return false;
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }

    public void move(Animal animal, MoveDirection direction) {
        final Vector2d oldPos = animal.getPosition();
        if (animals.containsKey(oldPos) && this.canMoveTo(oldPos)) {
            animals.remove(oldPos);
            animal.move(direction, this);
            animals.put(oldPos, animal);
        }
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d upperRight = new Vector2d((int) Math.sqrt(grassCount), (int) Math.sqrt(grassCount));

        for (Animal animal : animals.values()) {
            upperRight = upperRight.upperRight(animal.getPosition());
        }

        return visualizer.draw(new Vector2d(0, 0), upperRight);
    }

}
