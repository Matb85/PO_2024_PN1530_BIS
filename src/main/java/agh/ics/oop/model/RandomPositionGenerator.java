package agh.ics.oop.model;

import agh.ics.oop.model.util.Vector2d;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int grassCount;

    public RandomPositionGenerator(int maxWidth, int maxHeight,int grassCount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.grassCount = grassCount;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<>() {
            private int generatedGrass = 0;

            @Override
            public boolean hasNext() {
                return generatedGrass < grassCount;
            }

            private final Set<Vector2d> generatedPositions = new HashSet<>();

            @Override
            public Vector2d next() {
                Random random = new Random();
                Vector2d position;
                do {
                    int x = random.nextInt(maxWidth);
                    int y = random.nextInt(maxHeight);
                    position = new Vector2d(x, y);
                } while (generatedPositions.contains(position));
                generatedPositions.add(position);
                generatedGrass++;
                return position;
            }
        };
    }
}
