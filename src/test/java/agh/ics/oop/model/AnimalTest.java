package agh.ics.oop.model;

import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    final RectangularMap map = new RectangularMap(4, 4);


    @Test
    void shouldStartAtCorrectPosition() {
        Animal animal = new Animal();
        assertEquals(new Vector2d(2, 2), animal.getPosition());
    }

    @Test
    void shouldMoveCorrectly() {
        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD, map);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        animal.move(MoveDirection.RIGHT, map);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        animal.move(MoveDirection.BACKWARD, map);
        assertEquals(new Vector2d(1, 3), animal.getPosition());
        animal.move(MoveDirection.LEFT, map);
        assertEquals(new Vector2d(1, 3), animal.getPosition());
        animal.move(MoveDirection.FORWARD, map);
        assertEquals(new Vector2d(1, 4), animal.getPosition());
    }

    @Test
    void shouldNotEscapeMap() {
        Animal animal = new Animal();

        for (int i = 0; i < 10; i++) {
            animal.move(MoveDirection.FORWARD, map);
        }

        assertEquals(new Vector2d(2, 4), animal.getPosition());

        animal.move(MoveDirection.RIGHT, map);

        for (int i = 0; i < 10; i++) {
            animal.move(MoveDirection.FORWARD, map);
        }

        assertEquals(new Vector2d(4, 4), animal.getPosition());
    }
}