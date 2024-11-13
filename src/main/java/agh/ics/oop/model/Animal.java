package agh.ics.oop.model;

import agh.ics.oop.model.util.MapDirection;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;

public class Animal implements WorldElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public Animal(){
    }

    public Animal(Vector2d initialPosition){
        position = initialPosition;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getDirection() {
        return this.orientation;
    }

    public String toString(){
        return orientation.toString();
    }

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator validator){
        switch (direction){
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d oldPosition = position;
                position = position.add(orientation.toUnitVector());
                if (validator.canMoveTo(position)){
                    System.out.println("Zwierzak idzie do przodu z " + oldPosition + " na " + position);
                } else {
                    position = oldPosition;
                    System.out.println("Zwierzak nie może iść do przodu z " + oldPosition + " na " + position);
                }
            }
            case BACKWARD -> {
                Vector2d oldPosition = position;
                position = position.subtract(orientation.toUnitVector());
                if (validator.canMoveTo(position)){
                    System.out.println("Zwierzak idzie do tyłu z " + oldPosition + " na " + position);
                } else {
                    position = oldPosition;
                    System.out.println("Zwierzak nie może iść do tyłu z " + oldPosition + " na " + position);
                }
            }
        }
    }
}
