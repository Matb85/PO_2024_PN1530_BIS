package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.obervers.ConsoleMapDisplay;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.util.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class World {

    public static void main(String[] args) {
        switch(1){
            case 1 -> SimulationApp.launch(SimulationApp.class, args);
            case 2 -> runSingleSimulation(args);
            case 3 -> runManySimulations(args);
        }
    }

    private static Simulation createSimulation(String[] args, int width, int height) {
        System.out.println("Tworzenie symulacji dla argumentów: " + String.join(", ", args));
        List<MoveDirection> directions = OptionsParser.parseStringArray(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));

        final var map = new RectangularMap(width, height);
        map.addListener(new ConsoleMapDisplay());

        return new Simulation(positions, directions, map);
    }

    private static void runManySimulations(String[] args) {
        final var numberOfSimulations = 1000;
        final List<Simulation> simulations = Stream.of(new Simulation[numberOfSimulations]).map(i -> createSimulation(args, 5, 5)).toList();

        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsync();
        System.out.println("Zakończono uruchamianie symulacji");
        engine.awaitSimulationsEnd();
        System.out.println("Zakończono wszystkie symulacje");
    }

    private static void runSingleSimulation(String[] args) {
        Simulation simulation = createSimulation(args, 5, 5);
        simulation.run();
    }
}
