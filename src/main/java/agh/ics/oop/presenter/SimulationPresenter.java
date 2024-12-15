package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SimulationPresenter {
    @FXML
    private Label infoLabel;
    @FXML
    private Canvas mapCanvas;
    @FXML
    private TextField stepsInput;
    @FXML
    private Button startButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resumeButton;

    private Simulation simulation;
    private RectangularMap map;


    public void setWorldMap(RectangularMap map) {
        this.map = map;
    }

    @FXML
    private void handleStart() {
        List<MoveDirection> directions = OptionsParser.parseStringArray(stepsInput.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        simulation = new Simulation(positions, directions, map);
        simulation.run();
        drawMap();
    }

    @FXML
    private void handleReset() {
        stepsInput.clear();
        map = new RectangularMap(10, 5);
        drawMap();
    }

    @FXML
    private void handlePause() {
        if (simulation != null) {
            simulation.pause();
        }
    }

    @FXML
    private void handleResume() {
        if (simulation != null) {
            simulation.resume();
        }
    }

    public void drawMap() {
        // Implement drawing logic here
    }
}