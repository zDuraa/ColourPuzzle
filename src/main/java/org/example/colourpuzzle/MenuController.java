package org.example.colourpuzzle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuController {
    public Label labelFinalTime;
    @FXML
    private Label label1;
    @FXML
    private Scene gameScene;
    private Scene difficultyScene;
    private Stage stage;
    private static Stage gameStage = null;
    private static Stage diffecultyStage = new Stage();

    DifficultyController dCon = new DifficultyController();

    public void setStage(Stage stage, Scene gameScene, Scene difficultyScene) {
        this.stage = stage;
        this.gameScene = gameScene;
        this.difficultyScene = difficultyScene;
        this.label1.setText("Hello");
    }

    @FXML
    private void onPlayButtonClick(ActionEvent event) {
        try {

            if (gameStage != null) {
                gameStage.close();
            }

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

    public void setWinText(String text) {
        label1.setText(text);
    }
}
