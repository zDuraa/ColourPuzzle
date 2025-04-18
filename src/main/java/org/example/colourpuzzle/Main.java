package org.example.colourpuzzle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Initialazing Menu Scene
        FXMLLoader menuLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene menuScene = new Scene(menuLoader.load());
        MenuController menuController = menuLoader.getController();

        //Initialazing Game Scene
        FXMLLoader gameLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        Scene gameScene = new Scene(gameLoader.load());

        GameController gameController = gameLoader.getController();

        //Initialazing Diffeculty Scene
        FXMLLoader diffecultyLoader = new FXMLLoader(Main.class.getResource("Diffeculty.fxml"));
        Scene diffecultyScene = new Scene(diffecultyLoader.load());
        DiffecultyController diffecultyController = diffecultyLoader.getController();

        //Defining both Scenes
        menuController.setStage(stage, gameScene, diffecultyScene);
        gameController.setStage(stage, menuScene);

        //Setting stage
        stage.setTitle("Menu");
        stage.setScene(menuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}