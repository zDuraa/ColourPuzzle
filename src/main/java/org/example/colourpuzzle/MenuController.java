package org.example.colourpuzzle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Scene gameScene;
    private Stage stage;

    public void setStage(Stage stage, Scene gameScene) {
        this.stage = stage;
        this.gameScene = gameScene;
    }

    @FXML
    private void onPlayButtonClick() {
        stage.setScene(gameScene);
    }
}
