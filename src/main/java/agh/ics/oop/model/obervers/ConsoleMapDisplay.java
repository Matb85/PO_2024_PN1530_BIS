package agh.ics.oop.model.obervers;

import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.MapChangeListener;

public class ConsoleMapDisplay implements MapChangeListener {
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
    }
}
