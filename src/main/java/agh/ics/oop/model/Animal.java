package agh.ics.oop.model;

public class Animal {
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
        return "Pozycja: " + position + ", orientacja: " + orientation;
    }

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d oldPosition = position;
                position = position.add(orientation.toUnitVector());
                if (position.precedes(new Vector2d(4,4)) && position.follows(new Vector2d(0,0))){
                    System.out.println("Zwierzak idzie do przodu z " + oldPosition + " na " + position);
                } else {
                    position = oldPosition;
                    System.out.println("Zwierzak nie może iść do przodu z " + oldPosition + " na " + position);
                }
            }
            case BACKWARD -> {
                Vector2d oldPosition = position;
                position = position.subtract(orientation.toUnitVector());
                if (position.precedes(new Vector2d(4,4)) && position.follows(new Vector2d(0,0))){
                    System.out.println("Zwierzak idzie do tyłu z " + oldPosition + " na " + position);
                } else {
                    position = oldPosition;
                    System.out.println("Zwierzak nie może iść do tyłu z " + oldPosition + " na " + position);
                }
            }
        }
    }
}
