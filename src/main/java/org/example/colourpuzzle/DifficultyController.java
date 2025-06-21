package org.example.colourpuzzle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class DifficultyController {

    @FXML
    Slider diffecultySlider;

    @FXML
    private void onBackClick(ActionEvent event)
    {
        Stage diffecultyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        diffecultyStage.close();
    }

    public void setDifficulty(double difficultySlider) {
        int difficulty = (int) difficultySlider;
        System.out.println("Changing diff to: "+ difficulty);
    }
    @FXML
    private void onTestklick()
    {
        setDifficulty(diffecultySlider.getValue());
    }
}
