package agh.ics.oop.model.obervers;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.MapChangeListener;

import java.util.concurrent.SynchronousQueue;

public class ConsoleMapDisplay implements MapChangeListener {
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {
            System.out.println(message);
            System.out.println(worldMap.getId());
            System.out.println(worldMap);
        }
    }
}
