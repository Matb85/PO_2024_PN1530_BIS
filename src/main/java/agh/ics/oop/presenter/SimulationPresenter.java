package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.obervers.ConsoleMapDisplay;
import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapChangeListener;
import agh.ics.oop.model.util.MoveDirection;
import agh.ics.oop.model.util.Vector2d;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;
public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label infoLabel;
    @FXML
    private GridPane mapCanvas;
    @FXML
    private TextField stepsInput;

    private WorldMap map;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    @FXML
    private void handleStart() {
        AbstractWorldMap map = new GrassField(10);
        List<Vector2d> positions = List.of(new Vector2d(1, 3), new Vector2d(2, 1));
        List<MoveDirection> directions;
        try {
            directions = OptionsParser.parseStringArray(stepsInput.getText().split(" "));
        } catch (IllegalArgumentException e) {
            showAlert("Podano błędny ruch", e.getMessage());
            return;
        }
        setWorldMap(map);
        map.addListener(this);
        map.addListener(new ConsoleMapDisplay());
        new SimulationEngine(List.of(new Simulation(positions, directions, map))).runAsyncWithoutWaitingForFinish();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.show();
    }

    private void clearGrid() {
        mapCanvas.getChildren().retainAll(mapCanvas.getChildren().getFirst());
        mapCanvas.getColumnConstraints().clear();
        mapCanvas.getRowConstraints().clear();
    }

    private void setGridConstraints(int rows, int cols) {
        int cellSize = Math.min((int) (mapCanvas.getMaxWidth() / cols), (int) (mapCanvas.getMaxHeight() / rows));
        for (int i = 0; i < cols; i++) mapCanvas.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < rows; i++) mapCanvas.getRowConstraints().add(new RowConstraints(cellSize));
    }

    private void addLabel(int col, int row, String content) {
        Label label = new Label(content);
        mapCanvas.add(label, col, row);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
    }

    private void setAxisDescriptions(int rows, int cols, Vector2d upperLeft) {
        addLabel(0, rows, "y/x");
        for (int col = 0; col < cols; col++) addLabel(col + 1, rows, String.valueOf(upperLeft.getX() + col));
        for (int row = 0; row < rows; row++) addLabel(0, row, String.valueOf(upperLeft.getY() - row));
    }

    private void setGridCells(int rows, int cols, Vector2d upperLeft) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Vector2d pos = upperLeft.add(new Vector2d(col, -row));
                addLabel(col + 1, row, map.isOccupied(pos) ? map.objectAt(pos).toString() : "");
            }
        }
    }

    private void buildGrid() {
        Boundary bounds = map.getCurrentBounds();
        Vector2d upperLeft = new Vector2d(bounds.lowerLeft().getX(), bounds.upperRight().getY());
        int rows = bounds.upperRight().getY() - bounds.lowerLeft().getY() + 1;
        int cols = bounds.upperRight().getX() - bounds.lowerLeft().getX() + 1;
        setGridConstraints(rows + 1, cols + 1);
        setAxisDescriptions(rows, cols, upperLeft);
        setGridCells(rows, cols, upperLeft);
    }

    private void drawMap(String message) {
        infoLabel.setText(message);
        clearGrid();
        buildGrid();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> drawMap(message));
    }
}