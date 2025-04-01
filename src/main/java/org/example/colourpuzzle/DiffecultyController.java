package org.example.colourpuzzle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class DiffecultyController {

    @FXML
    Slider diffecultySlider;

    private int iDiffeculty;

    private void onBackClick(ActionEvent event)
    {
        Stage diffecultyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        diffecultyStage.close();
    }

    public int getiDiffeculty() {
        return iDiffeculty;
    }

    public void setiDiffeculty(Slider diffecultySlider) {
        this.iDiffeculty = (int)diffecultySlider.getValue();
    }
}
