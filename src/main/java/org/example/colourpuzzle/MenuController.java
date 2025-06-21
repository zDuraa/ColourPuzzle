package org.example.colourpuzzle;

import Backend.game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MenuController {
    @FXML
    private Scene gameScene;
    private Scene difficultyScene;
    private Stage stage;
    private static Stage gameStage = null;
    private static Stage diffecultyStage = new Stage();

    DiffecultyController dCon = new DiffecultyController();

    public void setStage(Stage stage, Scene gameScene, Scene difficultyScene) {
        this.stage = stage;
        this.gameScene = gameScene;
        this.difficultyScene = difficultyScene;
    }

    @FXML
    private void onPlayButtonClick(ActionEvent event) {
        try {

            if (gameStage != null) {
                gameStage.close();
            }

            int num = 3 + dCon.getiDiffeculty();
            game lol = new game(num);
            System.out.println("state: jug is created with diff: "+dCon.getiDiffeculty());
            lol.fillJug();
            System.out.println("state: jug is filled with bottles");
            lol.checkJug();
            lol.getJug()[0].setColourId(0,4);
            System.out.println("state: colourId in first bottle: "+lol.getJug()[0].getColourId(0));
            lol.checkJug();

            lol.initialize();

            System.out.println();
            lol.checkJug();

            lol.moveColour(lol.getJug()[0], lol.getJug()[1]);

            System.out.println();
            lol.checkJug();




            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();
            Stage newGameStage = new Stage();

            gameStage = newGameStage;
            gameStage.setScene(gameScene);
            gameStage.show();


            // Menü schließen
            Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            menuStage.close();

            newGameStage.setTitle("Colour Puzzle");
            newGameStage.setScene(new Scene(root));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Schließt das Fenster und beendet das Spiel
        gameStage.close();
    }

    @FXML
    private void onDifficultyClick(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Diffeculty.fxml"));
            Parent root = loader.load();
            //diffecultyStage.setScene(difficultyScene);
            diffecultyStage.setScene(new Scene(root));
            diffecultyStage.show();
            diffecultyStage.setTitle("Diffeculty");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
