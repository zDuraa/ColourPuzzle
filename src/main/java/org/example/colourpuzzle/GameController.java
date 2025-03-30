package org.example.colourpuzzle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    }

    @FXML
    protected void onTestButtonClick() {
        if (!Bottle1.getChildren().isEmpty()) {
            Node topLayer = Bottle1.getChildren().remove(Bottle1.getChildren().size() - 1); // Entferne oberste Schicht
            Bottle2.getChildren().add(0, topLayer); // Füge sie unten in Glas B hinzu
        }
    }

    @FXML
    private void fillVBox() {
        Bottle4.getChildren().clear(); // Falls bereits Elemente vorhanden sind, leeren
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW}; // Farben definieren

        for (Color color : colors) {
            Rectangle rect = new Rectangle(50, 25, color); // Rechteck mit Farbe erstellen
            Bottle4.getChildren().add(0, rect); // Unten einfügen, damit die Reihenfolge stimmt
        }
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
