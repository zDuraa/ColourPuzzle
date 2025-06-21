package org.example.colourpuzzle;

import Backend.game;
import Backend.GameColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private VBox Bottle4; // Flasche 4

    @FXML
    private VBox Bottle5; // Flasche 5 (wird befüllt)

    private VBox sourceBox = null;

    private VBox[] VBoxArray = null;

    public void setStage(Stage stage, Scene menuScene) {
        this.menuScene = menuScene;
        this.stage = stage;
    }

    GameColor col = new GameColor();

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

    /**
     * Befülle die Behälter
     */
    @FXML
    public void initialize() {
        // Flüssigkeit hinzufügen (Demo)
        int num = 3 + 1;
        game lol = new game(num);
        System.out.println("state: jug is created with diff: "+1);
        lol.fillJug();

        lol.checkJug();
        lol.getJug()[0].setColourId(0,4);


        lol.initialize();

        System.out.println();
        lol.checkJug();

        VBoxArray = new VBox[]{Bottle1, Bottle2, Bottle3, Bottle4,Bottle5};

        printArr(lol);

    }

    public void printArr(game wow)
    {
        for (int i = 0; i < wow.getJug().length; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                addLiquid(VBoxArray[i], col.getColor(wow.getJug()[i].getColourId(j)));
            }
        }
    }

    /**
     * Erzeugt ein quadrat mit der übergebenen Farbe
     * Fügt die Farbe an die erste Stelle des Behälters
     * @param bottle
     * @param color
     */
    private void addLiquid(VBox bottle, Color color) {
        Rectangle r = new Rectangle(100, 50, color);
        bottle.getChildren().add(0,r); // FIFO
    }

    /**
     *  Wenn Auf eine Vbox geklickt wird, so markiere es als Quelle und änder dessen border, wird nun auf ein weiteres geklickt,
     *  wird dieses zum Ziel. Erfolgt alles ohne Probleme, so werden die Farben getauscht
     * @param event
     */
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

    /**
     *  Zuerst gehen wir die Regeln durch was erlaubt ist.
     *  - Die Quelle von wo die Farbe kommt, darf nicht leer sein
     *  - Das Ziel in die die Farbe kommt, darf nicht voll sein
     *  - Nur Farben der gleichen Farbe, dürfen aufeinander Treffen beim überweisen
     *  Dann wird das Oberste Objekt aus der ersten Vbox removed und in das neue hinzugefügt und gemerged
     *
     * @param source
     * @param target
     */
    private void transferLiquid(VBox source, VBox target) {
        if (source.getChildren().isEmpty()) return;
        if (target.getChildren().size() >= 4) return;


        if (!target.getChildren().isEmpty()) {
            Rectangle sourceTop = (Rectangle) source.getChildren().get(0);
            Rectangle targetTop = (Rectangle) target.getChildren().get(0);

            // Vergleiche die Farben
            if (!((Color) sourceTop.getFill()).equals((Color) targetTop.getFill())) return;
        }



        Node top = source.getChildren().get(0);



        source.getChildren().remove(top);
        target.getChildren().add(top);
        tryMergeTopRectangles(target);
    }

    /**
     *  Wenn 2 Rectangle mit der gleichen Farbe aufeinander treffen, so addiere dessen größen miteinander,
     *  lösche die vorhanden 2 Rectangle und kreiere ein neues mit dessen Farbe und der neuen größe in der jeweiligen Ziel VBox
     * @param vbox
     */
    private void tryMergeTopRectangles(VBox vbox) {
        if (vbox.getChildren().size() < 2) return;

        Rectangle top1 = (Rectangle) vbox.getChildren().get(0);
        Rectangle top2 = (Rectangle) vbox.getChildren().get(1);

        Color color1 = (Color) top1.getFill();
        Color color2 = (Color) top2.getFill();

        if (color1.equals(color2)) {
            double newHeight = top1.getHeight() + top2.getHeight();
            double width = top1.getWidth();

            // Neues Rechteck erstellen mit doppelter Höhe
            Rectangle merged = new Rectangle(width, newHeight, color1);
            merged.setArcHeight(10);

            // Alte löschen
            vbox.getChildren().remove(0, 2);

            // Neues oben einfügen
            vbox.getChildren().add(merged);
        }
    }

}
