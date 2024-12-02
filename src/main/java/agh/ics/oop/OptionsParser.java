package agh.ics.oop;

import agh.ics.oop.model.util.MoveDirection;

import java.util.ArrayList;
import java.util.List;


public class OptionsParser {
    private static MoveDirection parseString(String arg){
        switch (arg){
            case "f" -> { return MoveDirection.FORWARD; }
            case "b" -> { return MoveDirection.BACKWARD; }
            case "r" -> { return MoveDirection.RIGHT; }
            case "l" -> { return MoveDirection.LEFT; }
            default -> { throw new IllegalArgumentException("Niepoprawny kierunek: " + arg); }
        }
    }

    public static List<MoveDirection> parseStringArray(String[] args) throws IllegalArgumentException {
        List<MoveDirection> temp = new ArrayList<>();
        for (String arg: args){
            MoveDirection direction = parseString(arg);
            if (direction != null){
                temp.add(direction);
            }
        }
        return temp;
    }
}
