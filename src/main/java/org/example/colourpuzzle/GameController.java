package org.example.colourpuzzle;
import Backend.game;
import Backend.bottle;
import Backend.colour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
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

    private VBox sourceBox = null;

    private VBox[] VBoxArray = null;

    public void setStage(Stage stage, Scene menuScene) {
        this.menuScene = menuScene;
        this.stage = stage;
    }

    @FXML
    private void openMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();

            Stage menuStage = new Stage(); // Aktuelles Fenster holen
            menuStage.setTitle("Menu");
            menuStage.setScene(new Scene(root)); // Szene auf das Menü wechseln
            menuStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Flüssigkeit hinzufügen (Demo)

        // Liste mit Farben definieren
        List<Color> colors = new ArrayList<>();
        VBoxArray = new VBox[]{Bottle1, Bottle2, Bottle3};
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);


        for (int i = 0; i < 3; i++) {

            Collections.shuffle(colors);

            addLiquid(VBoxArray[i], colors.get(0));
            addLiquid(VBoxArray[i], colors.get(1));
            addLiquid(VBoxArray[i], colors.get(2));

        }

    }

    private void addLiquid(VBox bottle, Color color) {
        Rectangle r = new Rectangle(100, 50, color);
        bottle.getChildren().addFirst(r); // FIFO
    }

    @FXML
    public void handleVBoxClick(MouseEvent event) {
        VBox clicked = (VBox) event.getSource();

        if (sourceBox == null) {
            sourceBox = clicked;
            sourceBox.getStyleClass().removeAll("vbox-border");
            sourceBox.getStyleClass().add("vbox-selected");
        } else {
            VBox targetBox = clicked;

            if (targetBox != sourceBox) {
                transferLiquid(sourceBox, targetBox);
            }

            // Reset Styles
            sourceBox.getStyleClass().removeAll("vbox-selected");
            sourceBox.getStyleClass().add("vbox-border");
            targetBox.getStyleClass().removeAll("vbox-selected");
            targetBox.getStyleClass().add("vbox-border");

            sourceBox = null;
        }
    }

    private void transferLiquid(VBox source, VBox target) {
        if (source.getChildren().isEmpty()) return;
        if (target.getChildren().size() >= 4) return;


        if (!target.getChildren().isEmpty()) {
            Rectangle sourceTop = (Rectangle) source.getChildren().getFirst();
            Rectangle targetTop = (Rectangle) target.getChildren().getFirst();

            // Vergleiche die Farben
            if (!((Color) sourceTop.getFill()).equals((Color) targetTop.getFill())) return;
        }



        Node top = source.getChildren().getFirst();



        source.getChildren().remove(top);
        target.getChildren().addFirst(top);
    }
}
