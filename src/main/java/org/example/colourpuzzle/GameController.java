package org.example.colourpuzzle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameController {
    @FXML
    private Stage stage;
    private Label welcomeText;
    private Scene menuScene;

    public void setStage(Stage stage, Scene menuScene) {

        this.menuScene = menuScene;
        this.stage = stage;
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    private void returnToMenu(){
        stage.setScene(menuScene);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode().toString().equals("ESCAPE")) {
            stage.setScene(menuScene);
        }
    }
}