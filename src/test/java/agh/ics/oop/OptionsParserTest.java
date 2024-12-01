package agh.ics.oop;

import agh.ics.oop.model.util.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseStringArray() {
        final var case1 = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals( case1, OptionsParser.parseStringArray(new String[]{"f", "b", "r", "l"}).toArray());

        assertThrows(IllegalArgumentException.class, ()-> OptionsParser.parseStringArray(new String[]{"f", "b","should not be here", "r", "l"}));

    }
}