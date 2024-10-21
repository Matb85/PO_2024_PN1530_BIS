package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Arrays;

public class World {

    public static void main(String[] args) {
        System.out.println("system wystartował");

        MoveDirection[] directions = OptionsParser.parseStringArray(args);
        System.out.println(Arrays.toString(directions));
        System.out.println("system zakończył działanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
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
