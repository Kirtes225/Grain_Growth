package com.company.controllers;

import com.company.Engine;
import com.company.Generation;
import com.company.Grain;
import com.company.enums.NeighborhoodType;
import com.company.enums.NucleonsArrangementType;
import com.company.interfaces.Rule;
import com.company.models.Moore;
import com.company.models.VonNeumann;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public ChoiceBox<NeighborhoodType> neighborhoodTypeChoiceBox;
    @FXML
    public ChoiceBox<NucleonsArrangementType> nucleonsArrangementTypeChoiceBox;
    @FXML
    public Canvas canvas;
    @FXML
    public Button startStopButton;
    @FXML
    public TextField nucleonsTextField;
    @FXML
    public TextField dimensionXTextField;
    @FXML
    public TextField dimensionYTextField;

    private int dimensionXFromUser, dimensionYFromUser, grainsNumberFromUser;
    private double sizeXOfOneCell = 0, sizeYOfOneCell = 0;

    private GraphicsContext graphicsContext;
    private boolean isRunning = false;

    private Engine engine;
    private Generation generation;
    private Rule rule;
    private NeighborhoodType neighborhoodType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        neighborhoodTypeChoiceBox.getItems().addAll(NeighborhoodType.values());
        neighborhoodTypeChoiceBox.getSelectionModel().selectFirst();

        nucleonsArrangementTypeChoiceBox.getItems().addAll(NucleonsArrangementType.values());
        nucleonsArrangementTypeChoiceBox.getSelectionModel().selectFirst();

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.FIREBRICK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void createSpaceButtonOnAction(ActionEvent actionEvent) {
        System.out.println("CANVAS: " + canvas.getWidth() + " " + canvas.getHeight());
        if (checkIfStringIsAPositiveInteger(dimensionXTextField.getText())
                && checkIfStringIsAPositiveInteger(dimensionYTextField.getText())
                && checkIfStringIsAPositiveInteger(nucleonsTextField.getText())) {

            startStopButton.setDisable(false);
            isRunning = true;

            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            dimensionXFromUser = Integer.parseInt(dimensionXTextField.getText());
            dimensionYFromUser = Integer.parseInt(dimensionYTextField.getText());
            grainsNumberFromUser = Integer.parseInt(nucleonsTextField.getText());

            if (dimensionXFromUser * dimensionYFromUser >= grainsNumberFromUser) {

                sizeXOfOneCell = canvas.getWidth() / dimensionXFromUser;
                sizeYOfOneCell = canvas.getHeight() / dimensionYFromUser;

                Grain.setCounter(0);
                generation = new Generation(new Grain[dimensionYFromUser][dimensionXFromUser]);

                neighborhoodType = neighborhoodTypeChoiceBox.getValue();

                switch (neighborhoodType) {
                    case MOORE:
                        rule = new Moore();
                        break;
                    case VON_NEUMANN:
                        rule = new VonNeumann();
                        break;
                }

                engine = new Engine(rule, generation);

                generateRandomGrains();

                for (int i = 0; i < dimensionXFromUser; i++) {
                    for (int j = 0; j < dimensionYFromUser; j++) {
                        System.out.print("GRAIN: " + engine.getGeneration().getSingleGrain(i, j) + " ");
                    }
                    System.out.println();
                }

                new Thread(() -> {
                    for (; ; ) {
                        if (isRunning) {
                            engine.nextGeneration();
                            Platform.runLater(this::printWholeStructure);
                        } else
                            Thread.yield();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        }
    }

    private void generateRandomGrains() {
        int temp = 0;
        Random random = new Random();

        do {
            int tempWidth = random.nextInt(dimensionXFromUser);
            int tempHeight = random.nextInt(dimensionYFromUser);
            if (engine.getGeneration().getSingleGrain(tempHeight, tempWidth) == null) {
                engine.getGeneration().setSingleGrain(tempHeight, tempWidth);
                temp++;
                System.out.println("X: " + (tempWidth + 1) + ", Y: " + (tempHeight + 1) + " TEMP: " + temp);
            }
        } while (temp < grainsNumberFromUser);
    }

    private void drawMesh() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (dimensionXFromUser <= canvas.getWidth() / 2 && dimensionYFromUser <= canvas.getHeight() / 2) {

            graphicsContext.setFill(Color.LIGHTGRAY);

            graphicsContext.fillRect(0, 0, canvas.getWidth(), 1);

            for (int i = 0; i <= dimensionXFromUser; i++) {
                graphicsContext.fillRect(i * sizeXOfOneCell - 1, 0, 1, canvas.getHeight());
            }

            for (int i = 0; i <= dimensionYFromUser; i++) {
                graphicsContext.fillRect(0, i * sizeYOfOneCell - 1, canvas.getWidth(), 1);
            }
        }
    }

    private void printWholeStructure() {
        drawMesh();

        for (int i = 0; i < engine.getGeneration().getSizeX(); i++) {
            for (int j = 0; j < engine.getGeneration().getSizeY(); j++)
                if (engine.getGeneration().getSingleGrain(i, j) != null) {
                    graphicsContext.setFill(engine.getGeneration().getSingleGrain(i, j).getColor());
                    graphicsContext.fillRect(j * sizeXOfOneCell, i * sizeYOfOneCell, sizeXOfOneCell, sizeYOfOneCell);
                }
        }
    }

    @Contract(pure = true)
    private boolean checkIfStringIsAPositiveInteger(String text) {
        if (text.matches("^[1-9][0-9]*$"))
            return true;
        else
            return false;
    }

    public void startStopButtonOnAction(ActionEvent actionEvent) {
        if (isRunning) {
            isRunning = false;
            startStopButton.setText("START");
        } else {
            isRunning = true;
            startStopButton.setText("STOP");
        }
    }


    public void canvasOnMouseClicked(MouseEvent mouseEvent) {

        double x = mouseEvent.getSceneX() - 200;
        double y = mouseEvent.getSceneY() - 25;

        int xx = (int) (x / sizeXOfOneCell);
        int yy = (int) (y / sizeYOfOneCell);

        Grain tmp = engine.getGeneration().getSingleGrain(yy, xx);
        System.out.println(tmp);

        if (isRunning && tmp == null) {
            engine.getGeneration().setSingleGrain(yy, xx);
            graphicsContext.setFill(engine.getGeneration().getSingleGrain(yy, xx).getColor());
            graphicsContext.fillRect(xx * sizeXOfOneCell, yy * sizeYOfOneCell, sizeXOfOneCell, sizeYOfOneCell);

            System.out.println("X: " + x + " Y: " + y + "[xx: " + xx + ", yy: " + yy + "] GRAIN: " + engine.getGeneration().getSingleGrain(yy, xx));
        }
    }

    public void aboutAuthorOnMouseClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Made by WIMiIPek (Tomasz Białek)", ButtonType.OK);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("../assets/Hedgehog.jpg")));
        alert.setTitle("Tuptający jeż");
        alert.setHeaderText("Tup, tup, tup");
        alert.show();
    }
}
