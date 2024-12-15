package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;
    private boolean paused = false;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves, WorldMap map) {
        this.moves = moves;
        this.map = map;

        for(Vector2d position: initialPositions) {
            final var a = new Animal(position);
            if(map.canMoveTo(position)) {
                try {
                    map.place(a);
                } catch (IncorrectPositionException e) {
                    throw new RuntimeException(e);
                }
                animals.add(a);
            }
        }
    }

    public void run() {
        final var numberOfAnimals = animals.size();

        for(int i = 0; i < moves.size(); i++) {
            if (paused) {
                break;
            }
            map.move(animals.get(i % numberOfAnimals), moves.get(i));
            System.out.println(map);
        }
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
        run();
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}