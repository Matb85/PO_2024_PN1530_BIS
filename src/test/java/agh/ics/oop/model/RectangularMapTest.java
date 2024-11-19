package agh.ics.oop.model;

import agh.ics.oop.model.util.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void shouldAllowMovementWithinBounds() {
        RectangularMap map = new RectangularMap(4, 4);
        Vector2d position = new Vector2d(2, 2);

        assertTrue(map.canMoveTo(position));
    }

    @Test
    void shouldPreventMovementOutsideBounds() {
        RectangularMap map = new RectangularMap(4, 4);
        Vector2d position = new Vector2d(5, 5);

        assertFalse(map.canMoveTo(position));
    }

    @Test
    void shouldReturnAllElements() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(0,0));
        Animal animal2 = new Animal(new Vector2d(2,2));
        Animal animal3 = new Animal(new Vector2d(2,2));

        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
        assertFalse(map.place(animal3));

        List<WorldElement> elements = map.getElements();

        assertTrue(elements.contains(animal1));
        assertTrue(elements.contains(animal2));
        assertEquals(2, elements.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoElements() {
        RectangularMap map = new RectangularMap(4, 4);

        List<WorldElement> elements = map.getElements();

        assertTrue(elements.isEmpty());
    }
}