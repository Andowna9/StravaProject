package com.moma.fans;

import com.moma.fans.gui.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Añadimos todas las pantallas con sus nombres
        ScreenController.getInstance().addScreen("Inicio de sesión", new LoginView());
        ScreenController.getInstance().addScreen("Registro", new RegisterView());
        ScreenController.getInstance().addScreen("Creación de perfil", new ProfileCreationView());
        ScreenController.getInstance().addScreen("Creación de reto", new CreateChallengeView());
        ScreenController.getInstance().addScreen("Home", new HomeView());

        ScreenController.getInstance().setStage(stage, 500, 400);
        ScreenController.getInstance().setScreen("Inicio de sesión");
        stage.setResizable(true);
        stage.show();

    }

    public static void main(String [] args) {

        launch();
    }
}
