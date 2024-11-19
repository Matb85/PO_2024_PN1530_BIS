package agh.ics.oop.model;

import agh.ics.oop.model.util.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest  {
    @Test
    void shouldReturnGrassWhenOnlyGrassAtPosition() {
        GrassField grassField = new GrassField(0);
        Vector2d position = new Vector2d(2, 2);
        Grass grass = new Grass(position);
        grassField.placeGrass(grass);

        assertEquals(grass, grassField.objectAt(position));
    }

    @Test
    void shouldReturnNullWhenNoAnimalOrGrassAtPosition() {
        GrassField grassField = new GrassField(0);
        Vector2d position = new Vector2d(2, 2);

        assertNull(grassField.objectAt(position));
    }

    @Test
    void shouldReturnAnimalWhenBothAnimalAndGrassAtPosition() {
        GrassField grassField = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal();
        grassField.animals.put(position, animal);
        grassField.placeGrass(new Grass(position));

        assertEquals(animal, grassField.objectAt(position));
    }

    @Test
    void shouldReturnAllElements() {
        GrassField grassField = new GrassField(0);
        Animal animal = new Animal();
        Grass grass = new Grass(new Vector2d(2, 2));
        grassField.animals.put(animal.getPosition(), animal);
        grassField.placeGrass(grass);

        List<WorldElement> elements = grassField.getElements();

        assertTrue(elements.contains(animal));
        assertTrue(elements.contains(grass));
        assertEquals(2, elements.size());
    }
}