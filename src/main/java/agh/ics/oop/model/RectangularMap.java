package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    final private Vector2d lowerLeft;
    final private Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft) && super.canMoveTo(position);
    }

    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeft, upperRight);
    }
}
