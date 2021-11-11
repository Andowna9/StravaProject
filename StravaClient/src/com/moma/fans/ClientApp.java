package com.moma.fans;

import com.moma.fans.views.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Añadimos todas las pantallas con sus nombres
        ScreenController.getInstance().addScreen("Inicio de sesión", new LoginView());
        ScreenController.getInstance().addScreen("Registro", new RegisterView());
        ScreenController.getInstance().addScreen("Creación de perfil", new ProfileCreationView());
        ScreenController.getInstance().addScreen("Creación de reto", new CreateChallengeView());
        ScreenController.getInstance().addScreen("Creación de sesión de entrenamiento", new CreateTrainingSessionView());
        ScreenController.getInstance().addScreen("Home", new HomeView());

        ScreenController.getInstance().setStage(stage, 600, 400);
        ScreenController.getInstance().setScreen("Inicio de sesión");
        
        //stage.getIcons().add(new Image("../../resources/images/logo-strava.png"));
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String [] args) {

        launch();
    }
}
