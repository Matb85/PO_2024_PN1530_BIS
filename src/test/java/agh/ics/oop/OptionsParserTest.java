package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseStringArray() {
        final var case1 = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals( case1, OptionsParser.parseStringArray(new String[]{"f", "b", "r", "l"}).toArray());

        final var case2 = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals( case2, OptionsParser.parseStringArray(new String[]{"f","test", "b","should not be here", "r", "l"}).toArray());

        final var case3 = new MoveDirection[]{};
        assertArrayEquals( case3, OptionsParser.parseStringArray(new String[]{"wrong", "wrong", "wrong", "wrong", "wrong"}).toArray());
    }
}