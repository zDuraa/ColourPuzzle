package org.example.colourpuzzle;
import Backend.game;
import Backend.bottle;
import Backend.colour;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameController {
    @FXML
    private Stage stage;
    private Scene menuScene;

    @FXML
    private HBox glassContainer; // Enthält die 3 Flaschen (Bottle1, Bottle2, Bottle3)

    @FXML
    private VBox Bottle1; // Flasche 1
    @FXML
    private VBox Bottle2; // Flasche 2
    @FXML
    private VBox Bottle3; // Flasche 3
    @FXML
    private VBox Bottle4; // Flasche 4 (wird befüllt)

    public void setStage(Stage stage, Scene menuScene) {
        this.menuScene = menuScene;
        this.stage = stage;
        fillAllBottles();
    }

    @FXML
    protected void onTestButtonClick() {
        if (!Bottle1.getChildren().isEmpty()) {
            Node topLayer = Bottle1.getChildren().remove(Bottle1.getChildren().size() - 1); // Entferne oberste Schicht
            Bottle2.getChildren().add(0, topLayer); // Füge sie unten in Glas B hinzu
        }
    }


    private void fillVBox(VBox bottle) {
        bottle.getChildren().clear(); // Flasche leeren

        // Liste mit Farben definieren
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);

        // Zufällige Reihenfolge mischen
        Collections.shuffle(colors);

        // 4 zufällige Farben auswählen
        for (int i = 0; i < 4; i++) {
            Rectangle rect = new Rectangle(50, 50, colors.get(i)); // Rechteck mit Farbe erstellen
            bottle.getChildren().add(0, rect); // Unten einfügen (stapeln)
        }
    }

    @FXML
    private void fillAllBottles(){
        fillVBox(Bottle1);
        fillVBox(Bottle2);
        fillVBox(Bottle3);
        fillVBox(Bottle4);
    }


    @FXML
    private void returnToMenu() {
        stage.setScene(menuScene);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode().toString().equals("ESCAPE")) {
            stage.setScene(menuScene);
        }
    }
}
