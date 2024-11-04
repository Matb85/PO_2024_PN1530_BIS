package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves) {

        this.moves = moves;

        for(Vector2d position: initialPositions) {
            animals.add(new Animal(position));
        }
    }

    public void run() {
        final var numberOfAnimals = animals.size();

        for(int i = 0; i < moves.size(); i++) {
            animals.get(i % numberOfAnimals).move(moves.get(i));
            System.out.println("Zwierzę " + (i % numberOfAnimals) + ": " + animals.get(i % animals.size()).getPosition());
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
