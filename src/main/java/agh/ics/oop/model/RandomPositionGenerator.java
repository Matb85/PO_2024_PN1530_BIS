package agh.ics.oop.model;

import agh.ics.oop.model.util.Vector2d;

import java.util.Iterator;
import java.util.Random;

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

            @Override
            public Vector2d next() {
                Random random = new Random();
                int x = random.nextInt(maxWidth);
                int y = random.nextInt(maxHeight);
                generatedGrass++;
                return new Vector2d(x, y);
            }
        };
    }
}
