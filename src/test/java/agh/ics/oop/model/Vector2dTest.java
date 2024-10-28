package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(2, 2)));
        assertTrue(new Vector2d(2, 2).precedes(new Vector2d(2, 2)));

        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(0, 0)));
    }

    @Test
    void follows() {
        assertTrue(new Vector2d(2, 2).follows(new Vector2d(1, 1)));
        assertTrue(new Vector2d(2, 2).follows(new Vector2d(2, 2)));

        assertFalse(new Vector2d(0, 0).follows(new Vector2d(1, 1)));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(1, 1), new Vector2d(0, 0).add(new Vector2d(1, 1)));
        assertEquals(new Vector2d(2, 2), new Vector2d(1, 1).add(new Vector2d(1, 1)));
    }

    @Test
    void subtract() {
        assertEquals(new Vector2d(1, 1), new Vector2d(2, 2).subtract(new Vector2d(1, 1)));
        assertEquals(new Vector2d(2, 0), new Vector2d(3, 1).subtract(new Vector2d(1, 1)));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(2, 2), new Vector2d(1, 1).upperRight(new Vector2d(2, 2)));
        assertEquals(new Vector2d(3, 3), new Vector2d(3, 3).upperRight(new Vector2d(2, 2)));

        assertNotEquals(new Vector2d(1, 1), new Vector2d(1, 1).upperRight(new Vector2d(2, 2)));
        assertNotEquals(new Vector2d(2, 2), new Vector2d(3, 3).upperRight(new Vector2d(2, 2)));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(1, 1), new Vector2d(1, 1).lowerLeft(new Vector2d(2, 2)));
        assertEquals(new Vector2d(2, 2), new Vector2d(3, 3).lowerLeft(new Vector2d(2, 2)));

        assertNotEquals(new Vector2d(2, 2), new Vector2d(1, 1).lowerLeft(new Vector2d(2, 2)));
        assertNotEquals(new Vector2d(3, 3), new Vector2d(3, 3).lowerLeft(new Vector2d(2, 2)));
    }

    @Test
    void opposite() {
        assertNotEquals(new Vector2d(1, 1), new Vector2d(1, 1).opposite());
        assertEquals(new Vector2d(-1, -1), new Vector2d(1, 1).opposite());
    }

    @Test
    void testToString() {
        assertEquals("(2,1)", new Vector2d(2, 1).toString());
        assertEquals("(0,0)", new Vector2d(0, 0).toString());

    }

    @Test
    void testEquals() {
        assertTrue(new Vector2d(1, 1).equals(new Vector2d(1, 1)));
        assertFalse(new Vector2d(1, 1).equals(new Vector2d(2, 2)));

        assertFalse(new Vector2d(1, 1).equals(null));
        assertFalse(new Vector2d(1, 1).equals(MapDirection.EAST));
    }
}