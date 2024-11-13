package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();
    final private Vector2d lowerLeft;
    final private Vector2d upperRight;

    public RectangularMap(int width, int height){
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width, height);
    }

    public boolean place(Animal animal){
        if (this.canMoveTo(animal.getPosition())){
            return false;
        }
        animals.put(animal.getPosition(), animal);
        return true;
    }

    public void move(Animal animal, MoveDirection direction){
        final Vector2d oldPos = animal.getPosition();
        if (animals.containsKey(oldPos) && this.canMoveTo(oldPos)){
            animals.remove(oldPos);
            animal.move(direction, this);
            animals.put(oldPos, animal);
        }
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }

    public boolean canMoveTo(Vector2d position){
        return position.precedes(upperRight) && position.follows(lowerLeft) && !isOccupied(position);
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }
}
