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

    WorldMap map;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    @FXML
    private void handleStart() {
        AbstractWorldMap map = new GrassField(10);
        List<Vector2d> positions = List.of(new Vector2d(1,3), new Vector2d(2,1));
        String[] movesArray = stepsInput.getText().split(" ");
        List<MoveDirection> directions;
        try {
            directions = OptionsParser.parseStringArray(movesArray);
        }
        catch (IllegalArgumentException e) {
            showIncorrectMoveAlert(e.getMessage());
            return;
        }
        setWorldMap(map);
        map.addListener(this);
        map.addListener(new ConsoleMapDisplay());
        SimulationEngine engine = new SimulationEngine(List.of(new Simulation(positions, directions, this.map)));
        engine.runAsyncWithoutWaitingForFinish();
    }

    private void clearGrid() {
        mapCanvas.getChildren().retainAll(mapCanvas.getChildren().getFirst()); // hack to retain visible grid lines
        mapCanvas.getColumnConstraints().clear();
        mapCanvas.getRowConstraints().clear();
    }

    private void setMapGridCellConstraints(int rowCount, int columnCount) {
        int maxCellWidth = (int) (mapCanvas.getMaxWidth() / columnCount);
        int maxCellHeight = (int) (mapCanvas.getMaxHeight() / rowCount);
        int cellSideLength = Math.min(maxCellWidth, maxCellHeight);
        for (int i = 0 ; i < columnCount; i++)
            mapCanvas.getColumnConstraints().add(new ColumnConstraints(cellSideLength));
        for (int i = 0 ; i < rowCount; i++)
            mapCanvas.getRowConstraints().add(new RowConstraints(cellSideLength));
    }

    private void addMapGridLabel(int colPos, int rowPos, String content) {
        Label label = new Label(content);
        mapCanvas.add(label, colPos, rowPos);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
    }

    private void setMapGridAxisDescriptions(int rowCount, int colCount, Vector2d upperLeft) {
        addMapGridLabel(0, rowCount, "y/x");
        for (int col = 0; col < colCount; col++)
            addMapGridLabel(col + 1, rowCount, String.valueOf(upperLeft.getX() + col));
        for (int row = 0; row < rowCount; row++)
            addMapGridLabel(0, row, String.valueOf(upperLeft.getY() - row));
    }

    private void setMapGridCells(int rowCount, int colCount, Vector2d upperLeft) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Vector2d mapPosition = upperLeft.add(new Vector2d(col, -row));
                String content = map.isOccupied(mapPosition) ?
                        map.objectAt(mapPosition).toString() : "";
                addMapGridLabel(col + 1, row, content);
            }
        }
    }

    private void buildGrid() {
        Boundary bounds = map.getCurrentBounds();
        Vector2d upperLeft = new Vector2d(bounds.lowerLeft().getX(), bounds.upperRight().getY());
        int rowCount = bounds.upperRight().getY() - bounds.lowerLeft().getY() + 1;
        int colCount = bounds.upperRight().getX() - bounds.lowerLeft().getX() + 1;
        setMapGridCellConstraints(rowCount + 1, colCount + 1);
        setMapGridAxisDescriptions(rowCount, colCount, upperLeft);
        setMapGridCells(rowCount, colCount, upperLeft);
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

    private void showIncorrectMoveAlert(String message) {
        Alert incorectMoveAlert = new Alert(Alert.AlertType.ERROR);
        incorectMoveAlert.setTitle("Podano błędny ruch");
        incorectMoveAlert.setHeaderText(message);
        incorectMoveAlert.show();
    }
}