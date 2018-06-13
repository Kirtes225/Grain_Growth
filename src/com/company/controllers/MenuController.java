package com.company.controllers;

import com.company.Engine;
import com.company.Generation;
import com.company.Grain;
import com.company.enums.ModelsType;
import com.company.enums.NucleonsArrangementType;
import com.company.interfaces.Arrangement;
import com.company.interfaces.Rule;
import com.company.models.arrangements.ContinuousRandom;
import com.company.models.arrangements.Evenly;
import com.company.models.arrangements.OnlyByClicking;
import com.company.models.arrangements.RandomWithRadius;
import com.company.models.rules.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public ChoiceBox<ModelsType> neighborhoodTypeChoiceBox;
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
    @FXML
    public CheckBox pbcCheckBox;
    @FXML
    public TextField radiusTextField;
    @FXML
    public CheckBox monteCarleCheckbox;
    @FXML
    public TextField iterationTextField;
    @FXML
    public Button moreRandomGrains;

    private boolean monteCarloEnabled = false;
    private int dimensionXFromUser, dimensionYFromUser, grainsNumberFromUser;
    private double sizeXOfOneCell = 0, sizeYOfOneCell = 0;
    private GraphicsContext graphicsContext;
    private boolean isRunning = false;
    private Engine engine;
    private Generation generation;
    private Rule rule;
    private ModelsType neighborhoodType;
    private boolean periodic;
    private NucleonsArrangementType nucleonsArrangementType;
    private Arrangement arrangement;
    private boolean moreRandomGrainsChecker = false;

//    private int radius;
//    private int circleNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        neighborhoodTypeChoiceBox.getItems().addAll(ModelsType.values());
        neighborhoodTypeChoiceBox.getSelectionModel().selectFirst();

        nucleonsArrangementTypeChoiceBox.getItems().addAll(NucleonsArrangementType.values());
        nucleonsArrangementTypeChoiceBox.getSelectionModel().selectFirst();

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.FIREBRICK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        monteCarleCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (engine == null)
                monteCarloEnabled = !monteCarloEnabled;
            else
                engine.setMonteCarlo(!engine.isMonteCarlo());
        });

        moreRandomGrains.setOnAction(event -> moreRandomGrainsChecker = !moreRandomGrainsChecker);
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
                generation = new Generation(new Grain[dimensionXFromUser][dimensionYFromUser]);

                neighborhoodType = neighborhoodTypeChoiceBox.getValue();

                switch (neighborhoodType) {
                    case MOORE:
                        rule = new Moore();
                        break;
                    case VON_NEUMANN:
                        rule = new VonNeumann();
                        break;
                    case HEXAGONAL_LEFT:
                        rule = new HexagonalLeft();
                        break;
                    case HEXAGONAL_RIGHT:
                        rule = new HexagonalRight();
                        break;
                    case HEXAGONAL_RANDOM:
                        rule = new HexagonalRandom();
                        break;
                    case PENTAGONAL_RANDOM:
                        rule = new PentagonalRandom();
                        break;
                }

                nucleonsArrangementType = nucleonsArrangementTypeChoiceBox.getValue();

                switch (nucleonsArrangementType) {
                    case LOSOWE:
                        arrangement = new com.company.models.arrangements.Random();
                        break;
                    case ROWNOMIERNE:
                        arrangement = new Evenly();
                        break;
                    case LOSOWE_Z_PROMIENIEM_R:
                        if (checkIfStringIsAPositiveInteger(radiusTextField.getText()))
                            arrangement = new RandomWithRadius(Integer.parseInt(radiusTextField.getText()));
                        else
                            arrangement = new RandomWithRadius(0);
                        break;
                    case PRZEZ_KLIKANIE:
                        arrangement = new OnlyByClicking();
                        break;
                    case CIAGLE_LOSOWANIE:
                        arrangement = new ContinuousRandom();
                }

                periodic = pbcCheckBox.isSelected();

                engine = new Engine(rule, generation, periodic, arrangement, grainsNumberFromUser);

                engine.arrangementOfGeneration();

                //generateRandomGrains();

//                for (int i = 0; i < dimensionXFromUser; i++) {
//                    for (int j = 0; j < dimensionYFromUser; j++) {
//                        System.out.print("GRAIN: " + engine.getGeneration().getSingleGrain(i, j) + " ");
//                    }
//                    System.out.println();
//                }

                new Thread(() -> {
                    for (; ; ) {
                        if (isRunning) {
                            engine.nextGeneration();
                            Platform.runLater(this::printWholeStructure);
                            if (engine.getArrangement() instanceof ContinuousRandom)
                                engine.arrangementOfGeneration();
                            if (checkIfStringIsAPositiveInteger(nucleonsTextField.getText()) && moreRandomGrainsChecker) {
                                moreRandomGrainsInTheSpace(Integer.parseInt(nucleonsTextField.getText()));
                                moreRandomGrainsChecker = !moreRandomGrainsChecker;
                            }
                        } else
                            Thread.yield();
                        if (engine.getArrangement() instanceof ContinuousRandom)
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        else

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

    private void moreRandomGrainsInTheSpace(int toAddNucleons) {
        int temp = 0;
        int width = generation.getSizeX();
        int height = generation.getSizeY();

        System.out.println("W: " + width + ", H: " + height);

        List<Integer> list = new ArrayList<>();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < width * height; i++) {
            list.add(i);
        }

        while (temp < toAddNucleons) {
            if (list.isEmpty())
                break;
            int coordinate = list.remove(random.nextInt(list.size()));
            int y = coordinate % width;
            int x = coordinate / width;
            System.out.println("COR: " + coordinate + "=> (" + x + ", " + y + ")");
            Grain tmp = engine.getGeneration().getSingleGrain(y, x);
            if (tmp == null) {
                engine.getGeneration().setSingleGrain(y, x);
                temp++;
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

        if (engine != null) {
            Grain tmp = engine.getGeneration().getSingleGrain(yy, xx);
            //System.out.println(tmp);

            if (isRunning) {
                if (tmp == null) {
                    engine.getGeneration().setSingleGrain(yy, xx);
                    graphicsContext.setFill(engine.getGeneration().getSingleGrain(yy, xx).getColor());
                    graphicsContext.fillRect(xx * sizeXOfOneCell, yy * sizeYOfOneCell, sizeXOfOneCell, sizeYOfOneCell);

                    System.out.println("Added: X: " + x + " Y: " + y + "[xx: " + xx + ", yy: " + yy + "] GRAIN: " + engine.getGeneration().getSingleGrain(yy, xx));
                } else {
                    System.out.println("THIS IS: " + tmp + " at: (" + xx + ", " + yy + ")");
//                    int ID = tmp.getID();
//                    engine.getGeneration().setSingleGrain(yy, xx, null);
//                    for (int i = 0; i < engine.getGeneration().getSizeX(); i++) {
//                        for (int j = 0; j < engine.getGeneration().getSizeY(); j++) {
//                            Grain grainToCheck = engine.getGeneration().getSingleGrain(i, j);
//                            if (grainToCheck != null) {
//                                if (grainToCheck.getID() == ID) {
//                                    engine.getGeneration().setSingleGrain(j, i, null);
//                                    System.out.println("X " + j + " Y " + i);
//                                    graphicsContext.setFill(Color.GOLDENROD);
//                                    graphicsContext.fillRect(j * sizeXOfOneCell, i * sizeYOfOneCell, sizeXOfOneCell, sizeYOfOneCell);
//                                }
//                            }
//                        }
//                    }
//                    System.out.println("Removed: X: " + x + " Y: " + y + "[xx: " + xx + ", yy: " + yy + "]");
                }
            }
        }
    }

    public void aboutAuthorOnMouseClicked(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Made by WIMiIPek (Tomasz Białek)", ButtonType.OK);
        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("../assets/Hedgehog.jpg")));
        alert.setTitle("Tuptający jeż");
        alert.setHeaderText("Tup, tup, tup");
        alert.show();
    }

    public void createFilledSpaceButtonOnAction(ActionEvent actionEvent) {
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
                generation = new Generation(new Grain[dimensionXFromUser][dimensionYFromUser]);

                neighborhoodType = neighborhoodTypeChoiceBox.getValue();

                switch (neighborhoodType) {
                    case MOORE:
                        rule = new Moore();
                        break;
                    case VON_NEUMANN:
                        rule = new VonNeumann();
                        break;
                    default:
                        System.out.println("DEFAULT IN SWITCH - MOORE");
                        rule = new Moore();
                        break;
                }
            }

            periodic = pbcCheckBox.isSelected();

            engine = new Engine(rule, generation, periodic, grainsNumberFromUser, monteCarloEnabled);

            engine.arrangementOfGeneration();

            int iterationNumber = 0;
            boolean validIteration = checkIfStringIsAPositiveInteger(iterationTextField.getText());

            if (validIteration)
                iterationNumber = Integer.parseInt(iterationTextField.getText());

            int finalIterationNumber = iterationNumber;

            new Thread(() -> {
                for (int i = 0; ; i++) {
                    if (validIteration && i == finalIterationNumber) {
                        isRunning = false;
                        Platform.runLater(() -> startStopButton.setText("START"));
                    }
                    if (isRunning) {
                        engine.nextGeneration();
                        Platform.runLater(this::printWholeStructure);
                    } else
                        Thread.yield();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
