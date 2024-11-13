package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.util.Vector2d;

import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("system wystartował");

        final var directions1 = OptionsParser.parseStringArray(args);
        System.out.println(directions1);
        System.out.println("system zakończył działanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        final var a = new Animal();
        System.out.println(a.getPosition());

        System.out.println("Starting simulation!");

        List<MoveDirection> directions = OptionsParser.parseStringArray(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        var map = new RectangularMap(10, 5);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }

    private static void run(MoveDirection[] args){
        System.out.println("zwierzak idzie do przodu");

        for (MoveDirection arg: args){
            switch (arg){
                case FORWARD -> System.out.println("zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
                case RIGHT-> System.out.println("zwierzak skręca w prawo");
                case LEFT -> System.out.println("zwierzak skręca w lewo");
            }
        }
    }
}
