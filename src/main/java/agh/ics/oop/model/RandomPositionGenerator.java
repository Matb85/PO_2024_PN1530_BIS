package agh.ics.oop.model;

import agh.ics.oop.model.util.Vector2d;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int grassCount;

    private final ArrayList<Vector2d> allPositions = new ArrayList<>();

    public RandomPositionGenerator(int maxWidth, int maxHeight,int grassCount) {
        this.grassCount = grassCount;

        for(int i = 0; i < maxWidth; i++){
            for(int j = 0; j < maxHeight; j++){
                allPositions.add(new Vector2d(i,j));
            }
        }

        Collections.shuffle(allPositions);
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<>() {
            private int generatedGrass = 0;

            @Override
            public boolean hasNext() {
                return generatedGrass < grassCount;
            }

            @Override
            public Vector2d next() {
                return allPositions.get(generatedGrass++ % allPositions.size());
            }
        };
    }
}
