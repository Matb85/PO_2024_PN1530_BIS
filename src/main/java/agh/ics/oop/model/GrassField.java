package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap implements WorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final int grassCount;

    public GrassField(int grassFields) {
        this.grassCount = grassFields;
        generateGrass();
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        final var animal = super.objectAt(position);

        return animal != null ? animal : grasses.get(position);
    }

    private void generateGrass() {
        final int maxDimension = (int) (Math.sqrt(grassCount));

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxDimension, maxDimension, grassCount);

        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    public List<WorldElement> getElements(){
        ArrayList<WorldElement> arr = new ArrayList<>(animals.size() + grasses.size());
        arr.addAll(animals.values());
        arr.addAll(grasses.values());

        return arr;
    }

    public void placeGrass(Grass grass) {
        if(!grasses.containsKey(grass.getPosition())) {
            grasses.put(grass.getPosition(), grass);
        }
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);

        final int maxGrassReach = (int) Math.sqrt(grassCount);

        final Vector2d upperRight = animals.values().stream()
                .map(Animal::getPosition)
                .reduce(new Vector2d(maxGrassReach, maxGrassReach),
                        (max, pos) -> new Vector2d(Math.max(max.getX(), pos.getX()), Math.max(max.getY(), pos.getY())));

        final Vector2d lowerLeft = animals.values().stream()
                .map(Animal::getPosition)
                .reduce(new Vector2d(0, 0),
                        (min, pos) -> new Vector2d(Math.min(min.getX(), pos.getX()), Math.min(min.getY(), pos.getY())));

        return visualizer.draw(lowerLeft, upperRight);
    }
}
