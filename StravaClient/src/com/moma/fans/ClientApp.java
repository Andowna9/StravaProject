package com.moma.fans;

import com.moma.fans.views.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Añadimos todas las pantallas con sus nombres
        ScreenController.getInstance().addScreen(ScreenController.State.LOG_IN, new LoginView());
        ScreenController.getInstance().addScreen(ScreenController.State.REGISTER, new RegisterView());
        ScreenController.getInstance().addScreen(ScreenController.State.PROFILE_CREATION, new ProfileCreationView());
        ScreenController.getInstance().addScreen(ScreenController.State.CHALLENGE_CREATION, new CreateChallengeView());
        ScreenController.getInstance().addScreen(ScreenController.State.TRAINING_SESSION_CREATION, new CreateTrainingSessionView());
        ScreenController.getInstance().addScreen(ScreenController.State.HOME, new HomeView());

        ScreenController.getInstance().setStage(stage, 600, 400);
        ScreenController.getInstance().setScreen(ScreenController.State.LOG_IN);

        stage.setResizable(false);
        stage.show();

    }

    public static void main(String [] args) {

        launch();
    }
}
