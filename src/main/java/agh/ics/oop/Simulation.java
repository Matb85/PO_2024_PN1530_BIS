package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves, WorldMap map) {
        this.moves = moves;
        this.map = map;

        for(Vector2d position: initialPositions) {
            final var a = new Animal(position);
            if(map.canMoveTo(position)) {
                map.place(a);
                animals.add(a);
            }
        }
    }

    public void run() {
        final var numberOfAnimals = animals.size();

        for(int i = 0; i < moves.size(); i++) {
            animals.get(i % numberOfAnimals).move(moves.get(i), map);
            System.out.println(map);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
