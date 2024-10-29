package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    @Test
    void shouldMoveCorrectly() {
        final var moves ="f b r l f f r r f f f f f f f f".split(" ");
        final List<MoveDirection> directions = OptionsParser.parseStringArray(moves);
        final List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

       final List<Animal> animals = simulation.getAnimals();

        assertEquals(new Vector2d(3, 0), animals.get(0).getPosition());
        assertEquals(new Vector2d(2, 4), animals.get(1).getPosition());
    }
}