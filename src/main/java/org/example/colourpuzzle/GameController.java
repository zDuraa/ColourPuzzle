package org.example.colourpuzzle;

import Backend.game;
import Backend.GameColor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameController {
    public Label timeLabel;
    @FXML
    private Stage stage;
    private Scene menuScene;

    private game game;

    @FXML
    private VBox Bottle1; // Flasche 1
    @FXML
    private VBox Bottle2; // Flasche 2
    @FXML
    private VBox Bottle3; // Flasche 3
    @FXML
    private VBox Bottle4; // Flasche 4 (wird befüllt)

    @FXML
    private VBox Bottle5; // Flasche 5 (wird befüllt)


    private VBox sourceBox = null;
    private VBox targetBox = null;
    private Thread timerThread;
    private boolean timerRunning;

    private List<VBox> VBoxList = new ArrayList<>();
    MenuController mC = new MenuController();

    public void setStage(Stage stage, Scene menuScene) {
        this.menuScene = menuScene;
        this.stage = stage;
    }

    GameColor col = new GameColor();

    @FXML
    private void openMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();

            Stage menuStage = new Stage(); // Aktuelles Fenster holen

            boolean temp = game.winCon();
            if(temp){
                MenuController menuController = loader.getController();
                menuController.setWinText("You won!");
                menuController.setTimeText(""+mC.getElapsedSeconds());

            }
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
        game = new game(num);
        System.out.println("state: jug is created with diff: "+1);
        game.fillJug();

        game.checkJug();
        game.getJug()[0].setColourId(0,4);


        game.initialize();

        System.out.println();
        game.checkJug();

        VBoxList.add(Bottle1);
        VBoxList.add(Bottle2);
        VBoxList.add(Bottle3);
        VBoxList.add(Bottle4);
        VBoxList.add(Bottle5);

        printArr(game);


        startThread();
    }

    public void printArr(game game)
    {
        for (int i = 0; i < game.getJug().length; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                addLiquid(VBoxList.get(i), col.getColor(game.getJug()[i].getColourId(j)));
            }
        }
    }

    public void removeArr(game game)
    {
        for (int i = 0; i < game.getJug().length; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                VBoxList.get(i).getChildren().remove(0,1);
            }
        }
    }

    /**
     * Erzeugt ein quadrat mit der übergebenen Farbe
     * fügt die Farbe an die erste Stelle des Behälters
     */
    private void addLiquid(VBox bottle, Color color) {
        Rectangle r = new Rectangle(100, 50, color);
        bottle.getChildren().add(0,r); // FIFO
    }

    /**
     *  Wenn auf eine Vbox geklickt wird, so markiere es als Quelle und änder dessen border, wird nun auf ein weiteres geklickt,
     *  wird dieses zum Ziel. Erfolgt alles ohne Probleme, so werden die Farben getauscht
     */
    @FXML
    public void handleVBoxClick(MouseEvent event) {
        VBox clicked = (VBox) event.getSource();

        if (sourceBox == null) {
            sourceBox = clicked;
            sourceBox.getStyleClass().removeAll("vbox-border");
            sourceBox.getStyleClass().add("vbox-selected");
        } else {
            targetBox = clicked;

            if (targetBox != sourceBox) {
                game.moveColour(game.getJug()[VBoxList.indexOf(sourceBox)], game.getJug()[VBoxList.indexOf(targetBox)]);
                removeArr(game);
                printArr(game);
                game.checkJug();
            }

            // Reset Styles
            sourceBox.getStyleClass().removeAll("vbox-selected");
            sourceBox.getStyleClass().add("vbox-border");
            targetBox.getStyleClass().removeAll("vbox-selected");
            targetBox.getStyleClass().add("vbox-border");

            sourceBox = null;


            boolean temp = game.winCon();
            if(temp){
                //timer stoppen
                openMenu();
                timerRunning = false;

            }
        }
    }

    public void startThread()
    {
        mC.startTime();
        timerRunning = true;
        timerThread = new Thread(() -> {
            while (timerRunning){
                Platform.runLater(() -> {
                            timeLabel.setText(""+ mC.getElapsedSeconds());
                });

                try {
                    Thread.sleep(100); // 100 ms Pause – 10 Updates pro Sekunde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        });

        timerThread.setDaemon(true);
        timerThread.start();
    }



}
